"use client";

import { motion } from "framer-motion";
import { Button } from "@/components/ui/button";

export default function Home() {
  return (
    <main className="flex flex-1 flex-col items-center justify-center gap-8 bg-background px-6 py-24 text-center">
      <motion.h1
        initial={{ opacity: 0, y: 12 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5, ease: "easeOut" }}
        className="font-[family-name:var(--font-devanagari)] text-5xl font-semibold tracking-tight text-foreground"
      >
        राम लेखक
      </motion.h1>
      <motion.p
        initial={{ opacity: 0, y: 12 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5, delay: 0.1, ease: "easeOut" }}
        className="max-w-md text-balance text-muted-foreground"
      >
        The new Ram Lekhak scaffold is live: Next.js, Tailwind CSS, shadcn/ui,
        and Framer Motion are wired up and ready for the app migration.
      </motion.p>
      <motion.div
        initial={{ opacity: 0, y: 12 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5, delay: 0.2, ease: "easeOut" }}
      >
        <Button size="lg">Start Writing</Button>
      </motion.div>
    </main>
  );
}
