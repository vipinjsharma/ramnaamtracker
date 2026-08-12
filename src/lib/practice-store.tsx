"use client";

import * as React from "react";

import {
  DEFAULT_PRACTICE_DATA,
  MALA_COUNT,
  type Language,
  type PracticeData,
  type ThemeId,
} from "@/lib/types";
import { translate, type TranslationKey } from "@/lib/translations";

const STORAGE_KEY = "ramLekhakData";

interface PracticeContextValue {
  data: PracticeData;
  loaded: boolean;
  todayMalaCount: number;
  totalMalaCount: number;
  recordWrite: () => { malaCompleted: boolean; malaCount: number };
  setUserName: (name: string) => void;
  setGoals: (dailyGoal: number, monthlyGoal: number) => void;
  setTheme: (theme: ThemeId) => void;
  setLanguage: (language: Language) => void;
  t: (key: TranslationKey, vars?: Record<string, string | number>) => string;
}

const PracticeContext = React.createContext<PracticeContextValue | null>(null);

function readStoredData(): PracticeData {
  const today = new Date().toDateString();
  const thisMonth = new Date().getMonth() + 1;

  let saved: Partial<PracticeData> | null = null;
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    saved = raw ? JSON.parse(raw) : null;
  } catch {
    saved = null;
  }

  if (!saved) {
    return { ...DEFAULT_PRACTICE_DATA, currentMonth: thisMonth };
  }

  const merged: PracticeData = { ...DEFAULT_PRACTICE_DATA, ...saved };

  if (merged.lastActiveDate === today) {
    // Already active today; keep counts and streak as saved.
  } else {
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    if (merged.lastActiveDate !== yesterday.toDateString()) {
      merged.currentStreak = 0;
    }
    merged.todayCount = 0;
  }

  if (merged.currentMonth !== thisMonth) {
    merged.currentMonth = thisMonth;
    merged.currentMonthCount = 0;
  }

  return merged;
}

export function PracticeProvider({ children }: { children: React.ReactNode }) {
  const [data, setData] = React.useState<PracticeData>(DEFAULT_PRACTICE_DATA);
  const [loaded, setLoaded] = React.useState(false);

  React.useEffect(() => {
    // localStorage isn't available during SSR, so the real data can only be
    // read after mount - this is the standard "sync from an external system"
    // exception to the set-state-in-effect rule.
    // eslint-disable-next-line react-hooks/set-state-in-effect
    setData(readStoredData());
    setLoaded(true);
  }, []);

  const persist = React.useCallback((next: PracticeData) => {
    setData(next);
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
    } catch {
      // localStorage unavailable (private browsing, etc.) - ignore.
    }
  }, []);

  const recordWrite = React.useCallback(() => {
    let malaCompleted = false;
    let malaCount = 0;

    setData((prev) => {
      const today = new Date().toDateString();
      const isFirstWriteToday = prev.todayCount === 0;
      const todayCount = prev.todayCount + 1;
      const prevMala = Math.floor(prev.todayCount / MALA_COUNT);
      malaCount = Math.floor(todayCount / MALA_COUNT);
      malaCompleted = malaCount > prevMala;

      const next: PracticeData = {
        ...prev,
        todayCount,
        totalCount: prev.totalCount + 1,
        currentMonthCount: prev.currentMonthCount + 1,
        currentStreak: isFirstWriteToday ? prev.currentStreak + 1 : prev.currentStreak,
        longestStreak: isFirstWriteToday
          ? Math.max(prev.currentStreak + 1, prev.longestStreak)
          : prev.longestStreak,
        lastActiveDate: today,
      };

      try {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
      } catch {
        // ignore
      }

      return next;
    });

    return { malaCompleted, malaCount };
  }, []);

  const setUserName = React.useCallback(
    (userName: string) => persist({ ...data, userName }),
    [data, persist],
  );

  const setGoals = React.useCallback(
    (dailyGoal: number, monthlyGoal: number) =>
      persist({ ...data, dailyGoal, monthlyGoal }),
    [data, persist],
  );

  const setTheme = React.useCallback(
    (theme: ThemeId) => persist({ ...data, theme }),
    [data, persist],
  );

  const setLanguage = React.useCallback(
    (language: Language) => persist({ ...data, language }),
    [data, persist],
  );

  const t = React.useCallback(
    (key: TranslationKey, vars?: Record<string, string | number>) =>
      translate(data.language, key, vars),
    [data.language],
  );

  const value = React.useMemo<PracticeContextValue>(
    () => ({
      data,
      loaded,
      todayMalaCount: Math.floor(data.todayCount / MALA_COUNT),
      totalMalaCount: Math.floor(data.totalCount / MALA_COUNT),
      recordWrite,
      setUserName,
      setGoals,
      setTheme,
      setLanguage,
      t,
    }),
    [data, loaded, recordWrite, setUserName, setGoals, setTheme, setLanguage, t],
  );

  return (
    <PracticeContext.Provider value={value}>{children}</PracticeContext.Provider>
  );
}

export function usePractice() {
  const ctx = React.useContext(PracticeContext);
  if (!ctx) {
    throw new Error("usePractice must be used within a PracticeProvider");
  }
  return ctx;
}
