"use client";

import * as React from "react";
import type { AuthChangeEvent, Session, User } from "@supabase/supabase-js";

import {
  DEFAULT_PRACTICE_DATA,
  MALA_COUNT,
  type Language,
  type PracticeData,
  type ThemeId,
} from "@/lib/types";
import { translate, type TranslationKey } from "@/lib/translations";
import { getSupabaseBrowserClient } from "@/lib/supabase/client";
import { isSupabaseConfigured } from "@/lib/supabase/env";
import {
  applyRollover,
  fetchRemotePracticeData,
  mergePracticeData,
  pushRemotePracticeData,
} from "@/lib/practice-sync";

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
  /** Whether cloud sync is even possible in this deployment (env vars set). */
  cloudSyncAvailable: boolean;
  user: User | null;
  authLoading: boolean;
  signInWithEmail: (email: string) => Promise<{ error: string | null }>;
  signInWithGoogle: () => Promise<void>;
  signOut: () => Promise<void>;
}

const PracticeContext = React.createContext<PracticeContextValue | null>(null);

function readLocalData(): PracticeData {
  let saved: Partial<PracticeData> | null = null;
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    saved = raw ? JSON.parse(raw) : null;
  } catch {
    saved = null;
  }
  if (!saved) {
    return { ...DEFAULT_PRACTICE_DATA, currentMonth: new Date().getMonth() + 1 };
  }
  return applyRollover({ ...DEFAULT_PRACTICE_DATA, ...saved });
}

export function PracticeProvider({ children }: { children: React.ReactNode }) {
  const [data, setData] = React.useState<PracticeData>(DEFAULT_PRACTICE_DATA);
  const [loaded, setLoaded] = React.useState(false);
  const [user, setUser] = React.useState<User | null>(null);
  const [authLoading, setAuthLoading] = React.useState(isSupabaseConfigured);

  const dataRef = React.useRef(data);
  const userRef = React.useRef(user);

  React.useEffect(() => {
    dataRef.current = data;
  }, [data]);

  React.useEffect(() => {
    userRef.current = user;
  }, [user]);

  const persistLocal = React.useCallback((next: PracticeData) => {
    setData(next);
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(next));
    } catch {
      // localStorage unavailable (private browsing, etc.) - ignore.
    }
  }, []);

  const persist = React.useCallback(
    (next: PracticeData) => {
      persistLocal(next);
      const supabase = getSupabaseBrowserClient();
      if (supabase && userRef.current) {
        void pushRemotePracticeData(supabase, userRef.current.id, next);
      }
    },
    [persistLocal],
  );

  // Initial local load - localStorage is only available after mount.
  React.useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    setData(readLocalData());
    setLoaded(true);
  }, []);

  // Auth: pick up any existing session, then react to sign-in/out. On a
  // fresh sign-in, reconcile this device's local progress with whatever's
  // already saved to the account (see mergePracticeData for the rules).
  React.useEffect(() => {
    const supabase = getSupabaseBrowserClient();
    if (!supabase) return;

    supabase.auth.getSession().then(({ data }: { data: { session: Session | null } }) => {
      setUser(data.session?.user ?? null);
      setAuthLoading(false);
    });

    const { data: subscription } = supabase.auth.onAuthStateChange(
      async (event: AuthChangeEvent, session: Session | null) => {
        setUser(session?.user ?? null);

        if (event === "SIGNED_IN" && session?.user) {
          const remote = await fetchRemotePracticeData(supabase, session.user.id);
          if (remote) {
            const merged = applyRollover(mergePracticeData(dataRef.current, remote));
            persistLocal(merged);
            void pushRemotePracticeData(supabase, session.user.id, merged);
          } else {
            void pushRemotePracticeData(supabase, session.user.id, dataRef.current);
          }
        }
      },
    );

    return () => subscription.subscription.unsubscribe();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const recordWrite = React.useCallback(() => {
    let malaCompleted = false;
    let malaCount = 0;
    let written: PracticeData | null = null;

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
      written = next;
      return next;
    });

    const supabase = getSupabaseBrowserClient();
    if (supabase && userRef.current && written) {
      void pushRemotePracticeData(supabase, userRef.current.id, written);
    }

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

  const signInWithEmail = React.useCallback(async (email: string) => {
    const supabase = getSupabaseBrowserClient();
    if (!supabase) return { error: "Cloud sync isn't set up yet." };
    const { error } = await supabase.auth.signInWithOtp({
      email,
      options: { emailRedirectTo: `${window.location.origin}/auth/callback` },
    });
    return { error: error?.message ?? null };
  }, []);

  const signInWithGoogle = React.useCallback(async () => {
    const supabase = getSupabaseBrowserClient();
    if (!supabase) return;
    await supabase.auth.signInWithOAuth({
      provider: "google",
      options: { redirectTo: `${window.location.origin}/auth/callback` },
    });
  }, []);

  const signOut = React.useCallback(async () => {
    const supabase = getSupabaseBrowserClient();
    if (!supabase) return;
    await supabase.auth.signOut();
  }, []);

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
      cloudSyncAvailable: isSupabaseConfigured,
      user,
      authLoading,
      signInWithEmail,
      signInWithGoogle,
      signOut,
    }),
    [
      data,
      loaded,
      recordWrite,
      setUserName,
      setGoals,
      setTheme,
      setLanguage,
      t,
      user,
      authLoading,
      signInWithEmail,
      signInWithGoogle,
      signOut,
    ],
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
