import type { SupabaseClient } from "@supabase/supabase-js";

import { DEFAULT_PRACTICE_DATA, type Language, type PracticeData, type ThemeId } from "@/lib/types";

const TABLE = "practice_data";

/**
 * Applies the same daily/monthly rollover rules regardless of whether the
 * data came from localStorage or Supabase, so a device that's been offline
 * for a while still gets a correct "is the streak still alive" check.
 */
export function applyRollover(input: PracticeData): PracticeData {
  const data = { ...input };
  const today = new Date().toDateString();
  const thisMonth = new Date().getMonth() + 1;

  if (data.lastActiveDate !== today) {
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    if (data.lastActiveDate !== yesterday.toDateString()) {
      data.currentStreak = 0;
    }
    data.todayCount = 0;
  }

  if (data.currentMonth !== thisMonth) {
    data.currentMonth = thisMonth;
    data.currentMonthCount = 0;
  }

  return data;
}

/**
 * Reconciles a device's local progress with whatever's already saved to an
 * account being signed into for the first time on this device. Cumulative
 * numbers (totals, longest streak) take the max of both sides so neither
 * device's history is discarded; per-day numbers prefer whichever side was
 * actually active today. Settings (name/goals) prefer the account's values
 * once they've been customized, since the account represents the person's
 * primary identity across devices.
 */
export function mergePracticeData(local: PracticeData, remote: PracticeData): PracticeData {
  const today = new Date().toDateString();
  const localActiveToday = local.lastActiveDate === today;
  const remoteActiveToday = remote.lastActiveDate === today;
  const activeToday = localActiveToday || remoteActiveToday;

  return {
    userName:
      remote.userName !== DEFAULT_PRACTICE_DATA.userName ? remote.userName : local.userName,
    todayCount: activeToday
      ? Math.max(
          localActiveToday ? local.todayCount : 0,
          remoteActiveToday ? remote.todayCount : 0,
        )
      : 0,
    totalCount: Math.max(local.totalCount, remote.totalCount),
    currentMonthCount: Math.max(local.currentMonthCount, remote.currentMonthCount),
    currentMonth: remote.currentMonth,
    currentStreak: Math.max(local.currentStreak, remote.currentStreak),
    longestStreak: Math.max(local.longestStreak, remote.longestStreak),
    lastActiveDate: activeToday ? today : remote.lastActiveDate || local.lastActiveDate,
    dailyGoal:
      remote.dailyGoal !== DEFAULT_PRACTICE_DATA.dailyGoal ? remote.dailyGoal : local.dailyGoal,
    monthlyGoal:
      remote.monthlyGoal !== DEFAULT_PRACTICE_DATA.monthlyGoal
        ? remote.monthlyGoal
        : local.monthlyGoal,
    theme: remote.theme,
    language: remote.language,
  };
}

interface PracticeDataRow {
  user_id: string;
  user_name: string;
  today_count: number;
  total_count: number;
  current_month_count: number;
  current_month: number;
  current_streak: number;
  longest_streak: number;
  last_active_date: string;
  daily_goal: number;
  monthly_goal: number;
  theme: string;
  language: string;
  updated_at: string;
}

function toRow(userId: string, data: PracticeData): Omit<PracticeDataRow, "updated_at"> {
  return {
    user_id: userId,
    user_name: data.userName,
    today_count: data.todayCount,
    total_count: data.totalCount,
    current_month_count: data.currentMonthCount,
    current_month: data.currentMonth,
    current_streak: data.currentStreak,
    longest_streak: data.longestStreak,
    last_active_date: data.lastActiveDate,
    daily_goal: data.dailyGoal,
    monthly_goal: data.monthlyGoal,
    theme: data.theme,
    language: data.language,
  };
}

function fromRow(row: PracticeDataRow): PracticeData {
  return {
    userName: row.user_name,
    todayCount: row.today_count,
    totalCount: row.total_count,
    currentMonthCount: row.current_month_count,
    currentMonth: row.current_month,
    currentStreak: row.current_streak,
    longestStreak: row.longest_streak,
    lastActiveDate: row.last_active_date,
    dailyGoal: row.daily_goal,
    monthlyGoal: row.monthly_goal,
    theme: (row.theme as ThemeId) ?? DEFAULT_PRACTICE_DATA.theme,
    language: (row.language as Language) ?? DEFAULT_PRACTICE_DATA.language,
  };
}

export async function fetchRemotePracticeData(
  supabase: SupabaseClient,
  userId: string,
): Promise<PracticeData | null> {
  const { data, error } = await supabase
    .from(TABLE)
    .select("*")
    .eq("user_id", userId)
    .maybeSingle();

  if (error) {
    console.error("Failed to fetch cloud practice data", error);
    return null;
  }
  return data ? fromRow(data as PracticeDataRow) : null;
}

export async function pushRemotePracticeData(
  supabase: SupabaseClient,
  userId: string,
  data: PracticeData,
): Promise<void> {
  const { error } = await supabase.from(TABLE).upsert(toRow(userId, data));
  if (error) {
    console.error("Failed to sync practice data to the cloud", error);
  }
}
