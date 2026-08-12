"use client";

import { motion } from "framer-motion";

const PETAL_COLORS = ["#ff7817", "#ffb347", "#f4c95d", "#e8a87c", "#d4ac0d"];
const PIECE_COUNT = 26;

export interface ConfettiPiece {
  id: number;
  x: number;
  color: string;
  rotate: number;
  delay: number;
  scale: number;
  drift: number;
}

let nextBatchId = 1;

/** Call from an event handler (not during render) - uses Math.random. */
export function createConfettiBurst(): ConfettiPiece[] {
  const batchId = nextBatchId++;
  return Array.from({ length: PIECE_COUNT }, (_, i) => ({
    id: batchId * 1000 + i,
    x: 10 + Math.random() * 80,
    color: PETAL_COLORS[i % PETAL_COLORS.length],
    rotate: Math.random() * 360,
    delay: Math.random() * 0.35,
    scale: 0.7 + Math.random() * 0.6,
    drift: (Math.random() - 0.5) * 60,
  }));
}

export function Confetti({ pieces }: { pieces: ConfettiPiece[] }) {
  return (
    <div
      aria-hidden
      className="pointer-events-none fixed inset-0 z-100 overflow-hidden"
    >
      {pieces.map((piece) => (
        <motion.span
          key={piece.id}
          className="absolute top-0 block size-3 rounded-[60%_0%_60%_60%]"
          style={{ left: `${piece.x}%`, backgroundColor: piece.color }}
          initial={{ y: -24, x: 0, opacity: 0, rotate: 0, scale: piece.scale }}
          animate={{
            y: "105vh",
            x: piece.drift,
            opacity: [0, 1, 1, 0],
            rotate: piece.rotate,
          }}
          transition={{
            duration: 2.1,
            delay: piece.delay,
            ease: [0.2, 0.65, 0.4, 1],
          }}
        />
      ))}
    </div>
  );
}
