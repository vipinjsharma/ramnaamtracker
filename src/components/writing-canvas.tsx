"use client";

import * as React from "react";
import { motion } from "framer-motion";
import { EraserIcon, CheckIcon } from "lucide-react";
import { toast } from "sonner";

import { usePractice } from "@/lib/practice-store";
import { THEMES } from "@/lib/themes";
import { Button } from "@/components/ui/button";
import { Confetti, createConfettiBurst, type ConfettiPiece } from "@/components/confetti";

const GUIDE_TEXT = "राम";
const CANVAS_HEIGHT = 380;

type Point = { x: number; y: number };

const midpoint = (a: Point, b: Point): Point => ({
  x: (a.x + b.x) / 2,
  y: (a.y + b.y) / 2,
});

export function WritingCanvas() {
  const { data, recordWrite, t } = usePractice();
  const containerRef = React.useRef<HTMLDivElement>(null);
  const canvasRef = React.useRef<HTMLCanvasElement>(null);
  const pointsRef = React.useRef<Point[]>([]);
  const [confettiPieces, setConfettiPieces] = React.useState<ConfettiPiece[]>([]);
  const [isDrawing, setIsDrawing] = React.useState(false);

  const drawGuide = React.useCallback((canvas: HTMLCanvasElement) => {
    const ctx = canvas.getContext("2d");
    if (!ctx) return;
    // The context is scaled by devicePixelRatio (see setupCanvas), so all
    // drawing here must use CSS-pixel dimensions (clientWidth/clientHeight),
    // not the raw canvas.width/height buffer size - otherwise positions end
    // up scaled twice on high-DPI displays.
    const width = canvas.clientWidth;
    const height = canvas.clientHeight;
    const devanagariFont =
      getComputedStyle(document.documentElement)
        .getPropertyValue("--font-devanagari-display")
        .trim() || "sans-serif";

    ctx.clearRect(0, 0, width, height);

    // A faint baseline, like ruled practice paper.
    ctx.save();
    ctx.strokeStyle = "rgba(120, 120, 120, 0.15)";
    ctx.lineWidth = 1;
    ctx.setLineDash([5, 6]);
    ctx.beginPath();
    ctx.moveTo(width * 0.08, height * 0.72);
    ctx.lineTo(width * 0.92, height * 0.72);
    ctx.stroke();
    ctx.restore();

    // Outline-only guide glyph, like a tracing sheet - not a solid fill.
    ctx.textAlign = "center";
    ctx.textBaseline = "alphabetic";

    // Size and center off the glyph's actual ink extents
    // (actualBoundingBoxLeft/Right), not TextMetrics.width (the advance
    // width, i.e. cursor movement). Canvas font resolution for a custom
    // @font-face is less reliable than regular DOM text and can silently
    // fall back to a different font per browser/OS, and Devanagari
    // conjuncts can render with ink that isn't symmetric around the
    // advance box - so sizing/centering off the advance width alone can
    // end up visibly off-center or clipped depending on which font
    // actually gets used. The ink box reflects whatever was actually
    // drawn, so this self-corrects regardless.
    let fontSize = Math.floor(height * 0.52);
    ctx.font = `${fontSize}px ${devanagariFont}`;
    let metrics = ctx.measureText(GUIDE_TEXT);
    let inkWidth = metrics.actualBoundingBoxLeft + metrics.actualBoundingBoxRight;

    const maxInkWidth = width * 0.82;
    if (inkWidth > maxInkWidth) {
      fontSize = Math.floor(fontSize * (maxInkWidth / inkWidth));
      ctx.font = `${fontSize}px ${devanagariFont}`;
      metrics = ctx.measureText(GUIDE_TEXT);
      inkWidth = metrics.actualBoundingBoxLeft + metrics.actualBoundingBoxRight;
    }

    // Shift the draw anchor so the ink itself ends up centered, rather
    // than assuming the ink is symmetric around the advance-width center.
    const anchorX = width / 2 - (metrics.actualBoundingBoxRight - metrics.actualBoundingBoxLeft) / 2;

    ctx.lineWidth = 1.4;
    ctx.strokeStyle = "rgba(120, 120, 120, 0.32)";
    ctx.strokeText(GUIDE_TEXT, anchorX, height * 0.72);
  }, []);

  const setupCanvas = React.useCallback(() => {
    const canvas = canvasRef.current;
    const container = containerRef.current;
    if (!canvas || !container) return;
    const ratio = window.devicePixelRatio || 1;
    const width = container.clientWidth;
    canvas.width = width * ratio;
    canvas.height = CANVAS_HEIGHT * ratio;
    canvas.style.width = `${width}px`;
    canvas.style.height = `${CANVAS_HEIGHT}px`;
    const ctx = canvas.getContext("2d");
    if (ctx) {
      ctx.scale(ratio, ratio);
      ctx.lineCap = "round";
      ctx.lineJoin = "round";
      ctx.lineWidth = 9;
      ctx.strokeStyle = THEMES[data.theme]?.primary ?? THEMES.ram.primary;
    }
    drawGuide(canvas);
  }, [data.theme, drawGuide]);

  React.useEffect(() => {
    setupCanvas();
    // Canvas text doesn't repaint itself when a web font finishes loading
    // the way DOM text does, so the guide glyph can get drawn against a
    // fallback font on first paint if Tiro Devanagari Hindi hasn't loaded
    // yet - redraw once the browser confirms it's actually ready.
    document.fonts?.ready.then(() => {
      const canvas = canvasRef.current;
      if (canvas) drawGuide(canvas);
    });
    window.addEventListener("resize", setupCanvas);
    return () => window.removeEventListener("resize", setupCanvas);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [data.theme]);

  const getScaledPoint = (e: React.PointerEvent<HTMLCanvasElement>): Point => {
    const canvas = canvasRef.current;
    if (!canvas) return { x: 0, y: 0 };
    const rect = canvas.getBoundingClientRect();
    return { x: e.clientX - rect.left, y: e.clientY - rect.top };
  };

  const handlePointerDown = (e: React.PointerEvent<HTMLCanvasElement>) => {
    canvasRef.current?.setPointerCapture(e.pointerId);
    setIsDrawing(true);
    pointsRef.current = [getScaledPoint(e)];
  };

  const handlePointerMove = (e: React.PointerEvent<HTMLCanvasElement>) => {
    if (pointsRef.current.length === 0) return;
    const ctx = canvasRef.current?.getContext("2d");
    if (!ctx) return;

    pointsRef.current.push(getScaledPoint(e));
    const points = pointsRef.current;
    const len = points.length;

    // Quadratic-curve smoothing through the midpoints of recent samples -
    // draws a continuous smooth stroke instead of jagged straight segments.
    if (len < 3) return;
    const [p0, p1, p2] = points.slice(len - 3);
    const start = midpoint(p0, p1);
    const end = midpoint(p1, p2);
    ctx.beginPath();
    ctx.moveTo(start.x, start.y);
    ctx.quadraticCurveTo(p1.x, p1.y, end.x, end.y);
    ctx.stroke();
  };

  const handlePointerUp = () => {
    setIsDrawing(false);
    pointsRef.current = [];
  };

  const clearCanvas = () => {
    const canvas = canvasRef.current;
    if (!canvas) return;
    drawGuide(canvas);
  };

  const handleSubmit = () => {
    const { malaCompleted, malaCount } = recordWrite();
    clearCanvas();
    if (malaCompleted) {
      setConfettiPieces(createConfettiBurst());
      toast.success(
        t("mala_completed", { count: malaCount, plural: malaCount > 1 ? "s" : "" }),
      );
    } else {
      toast(t("write_ram"), { duration: 900 });
    }
  };

  return (
    <div className="flex flex-col gap-4">
      <Confetti pieces={confettiPieces} />
      <motion.div
        ref={containerRef}
        initial={{ opacity: 0, scale: 0.98 }}
        animate={{
          opacity: 1,
          scale: isDrawing ? 1.005 : 1,
          boxShadow: isDrawing
            ? "0 8px 24px -8px rgba(0,0,0,0.18)"
            : "0 2px 10px -4px rgba(0,0,0,0.1)",
        }}
        transition={{ duration: 0.2 }}
        className="overflow-hidden rounded-2xl border bg-card"
      >
        <canvas
          ref={canvasRef}
          onPointerDown={handlePointerDown}
          onPointerMove={handlePointerMove}
          onPointerUp={handlePointerUp}
          onPointerLeave={handlePointerUp}
          className="touch-none"
        />
      </motion.div>
      <p className="text-center text-sm text-muted-foreground">{t("keep_writing")}</p>
      <div className="flex gap-3">
        <Button variant="outline" className="flex-1" onClick={clearCanvas}>
          <EraserIcon /> {t("clear")}
        </Button>
        <motion.div className="flex-1" whileTap={{ scale: 0.96 }}>
          <Button className="w-full" onClick={handleSubmit}>
            <CheckIcon /> {t("submit")}
          </Button>
        </motion.div>
      </div>
    </div>
  );
}
