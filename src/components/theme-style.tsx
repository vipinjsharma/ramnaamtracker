"use client";

import * as React from "react";

import { usePractice } from "@/lib/practice-store";
import { THEMES } from "@/lib/themes";

export function ThemeStyle() {
  const { data, loaded } = usePractice();

  React.useEffect(() => {
    if (!loaded) return;
    const theme = THEMES[data.theme] ?? THEMES.ram;
    const root = document.documentElement.style;
    root.setProperty("--primary", theme.primary);
    root.setProperty("--primary-foreground", theme.primaryForeground);
    root.setProperty("--background", theme.background);
    root.setProperty("--foreground", theme.text);
    root.setProperty("--ring", theme.primary);
  }, [data.theme, loaded]);

  return null;
}
