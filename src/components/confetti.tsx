"use client";

import { motion } from "framer-motion";

const COLORS = ["#ff7817", "#2874a6", "#d4ac0d", "#c0392b", "#7d3c98", "#27ae60"];
const PIECE_COUNT = 32;

export interface ConfettiPiece {
  id: number;
  x: number;
  color: string;
  rotate: number;
  delay: number;
}

let nextBatchId = 1;

/** Call from an event handler (not during render) - uses Math.random. */
export function createConfettiBurst(): ConfettiPiece[] {
  const batchId = nextBatchId++;
  return Array.from({ length: PIECE_COUNT }, (_, i) => ({
    id: batchId * 1000 + i,
    x: Math.random() * 100,
    color: COLORS[i % COLORS.length],
    rotate: Math.random() * 360,
    delay: Math.random() * 0.3,
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
          className="absolute top-0 block h-2.5 w-1.5 rounded-sm"
          style={{ left: `${piece.x}%`, backgroundColor: piece.color }}
          initial={{ y: -20, opacity: 1, rotate: 0 }}
          animate={{ y: "110vh", opacity: [1, 1, 0], rotate: piece.rotate }}
          transition={{ duration: 1.8, delay: piece.delay, ease: "easeIn" }}
        />
      ))}
    </div>
  );
}
