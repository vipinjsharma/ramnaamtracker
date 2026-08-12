export type ThemeId = "ram" | "krishna" | "lakshmi" | "ganesh" | "shiva" | "light";

export type Language = "en" | "hi";

export interface PracticeData {
  userName: string;
  todayCount: number;
  totalCount: number;
  currentMonthCount: number;
  currentMonth: number;
  currentStreak: number;
  longestStreak: number;
  lastActiveDate: string;
  dailyGoal: number;
  monthlyGoal: number;
  theme: ThemeId;
  language: Language;
}

export const MALA_COUNT = 108;

export const DEFAULT_DAILY_GOAL = 108;
export const DEFAULT_MONTHLY_GOAL = 3240;

export const DEFAULT_PRACTICE_DATA: PracticeData = {
  userName: "Ram Bhakt",
  todayCount: 0,
  totalCount: 0,
  currentMonthCount: 0,
  currentMonth: new Date().getMonth() + 1,
  currentStreak: 0,
  longestStreak: 0,
  lastActiveDate: "",
  dailyGoal: DEFAULT_DAILY_GOAL,
  monthlyGoal: DEFAULT_MONTHLY_GOAL,
  theme: "ram",
  language: "en",
};
