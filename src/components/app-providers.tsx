"use client";

import { PracticeProvider } from "@/lib/practice-store";
import { ThemeStyle } from "@/components/theme-style";
import { Toaster } from "@/components/ui/sonner";

export function AppProviders({ children }: { children: React.ReactNode }) {
  return (
    <PracticeProvider>
      <ThemeStyle />
      {children}
      <Toaster position="top-center" richColors />
    </PracticeProvider>
  );
}
