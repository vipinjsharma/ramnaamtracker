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

export function WritingCanvas() {
  const { data, recordWrite, t } = usePractice();
  const containerRef = React.useRef<HTMLDivElement>(null);
  const canvasRef = React.useRef<HTMLCanvasElement>(null);
  const isDrawingRef = React.useRef(false);
  const lastPointRef = React.useRef<{ x: number; y: number } | null>(null);
  const [confettiPieces, setConfettiPieces] = React.useState<ConfettiPiece[]>([]);

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
        .getPropertyValue("--font-devanagari")
        .trim() || "sans-serif";
    ctx.clearRect(0, 0, width, height);
    ctx.font = `bold ${Math.floor(height * 0.5)}px ${devanagariFont}`;
    ctx.fillStyle = "rgba(120, 120, 120, 0.18)";
    ctx.textAlign = "center";
    ctx.textBaseline = "middle";
    ctx.fillText(GUIDE_TEXT, width / 2, height / 2);
  }, []);

  const setupCanvas = React.useCallback(() => {
    const canvas = canvasRef.current;
    const container = containerRef.current;
    if (!canvas || !container) return;
    const ratio = window.devicePixelRatio || 1;
    const width = container.clientWidth;
    const height = 340;
    canvas.width = width * ratio;
    canvas.height = height * ratio;
    canvas.style.width = `${width}px`;
    canvas.style.height = `${height}px`;
    const ctx = canvas.getContext("2d");
    if (ctx) {
      ctx.scale(ratio, ratio);
      ctx.lineCap = "round";
      ctx.lineJoin = "round";
      ctx.lineWidth = 6;
      ctx.strokeStyle = THEMES[data.theme]?.primary ?? THEMES.ram.primary;
    }
    drawGuide(canvas);
  }, [data.theme, drawGuide]);

  React.useEffect(() => {
    setupCanvas();
    window.addEventListener("resize", setupCanvas);
    return () => window.removeEventListener("resize", setupCanvas);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [data.theme]);

  const getCanvasContext = () => canvasRef.current?.getContext("2d") ?? null;

  const getScaledPoint = (e: React.PointerEvent<HTMLCanvasElement>) => {
    const canvas = canvasRef.current;
    if (!canvas) return { x: 0, y: 0 };
    const rect = canvas.getBoundingClientRect();
    return { x: e.clientX - rect.left, y: e.clientY - rect.top };
  };

  const handlePointerDown = (e: React.PointerEvent<HTMLCanvasElement>) => {
    canvasRef.current?.setPointerCapture(e.pointerId);
    isDrawingRef.current = true;
    lastPointRef.current = getScaledPoint(e);
  };

  const handlePointerMove = (e: React.PointerEvent<HTMLCanvasElement>) => {
    if (!isDrawingRef.current) return;
    const ctx = getCanvasContext();
    const last = lastPointRef.current;
    if (!ctx || !last) return;
    const point = getScaledPoint(e);
    ctx.beginPath();
    ctx.moveTo(last.x, last.y);
    ctx.lineTo(point.x, point.y);
    ctx.stroke();
    lastPointRef.current = point;
  };

  const handlePointerUp = () => {
    isDrawingRef.current = false;
    lastPointRef.current = null;
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
    <div className="flex flex-col gap-3">
      <Confetti pieces={confettiPieces} />
      <div
        ref={containerRef}
        className="overflow-hidden rounded-xl border bg-card shadow-sm"
      >
        <canvas
          ref={canvasRef}
          onPointerDown={handlePointerDown}
          onPointerMove={handlePointerMove}
          onPointerUp={handlePointerUp}
          onPointerLeave={handlePointerUp}
          className="touch-none"
        />
      </div>
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
