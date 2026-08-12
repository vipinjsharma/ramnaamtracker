"use client";

import * as React from "react";
import { CheckIcon, LoaderCircleIcon, PencilIcon, XIcon } from "lucide-react";
import { toast } from "sonner";

import { usePractice } from "@/lib/practice-store";
import { MALA_COUNT } from "@/lib/types";
import { THEME_ORDER, THEMES } from "@/lib/themes";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Separator } from "@/components/ui/separator";
import { cn } from "@/lib/utils";

export default function ProfilePage() {
  const { loaded } = usePractice();

  // Key on `loaded` so this remounts once real data arrives from
  // localStorage, letting the lazy useState initializers below pick up the
  // stored values without needing an effect to re-sync them.
  if (!loaded) return null;
  return <ProfileContent />;
}

function ProfileContent() {
  const {
    data,
    totalMalaCount,
    setUserName,
    setGoals,
    setTheme,
    setLanguage,
    t,
    cloudSyncAvailable,
  } = usePractice();

  const [editingName, setEditingName] = React.useState(false);
  const [nameDraft, setNameDraft] = React.useState(data.userName);
  const [dailyGoalDraft, setDailyGoalDraft] = React.useState(String(data.dailyGoal));
  const [monthlyGoalDraft, setMonthlyGoalDraft] = React.useState(
    String(data.monthlyGoal),
  );

  const initials = data.userName
    .split(" ")
    .map((part) => part[0])
    .slice(0, 2)
    .join("")
    .toUpperCase();

  const saveName = () => {
    const trimmed = nameDraft.trim();
    if (trimmed.length < 2 || trimmed.length > 30) {
      toast.error(t("name_length_error"));
      return;
    }
    setUserName(trimmed);
    setEditingName(false);
    toast.success(t("name_updated"));
  };

  const saveGoals = () => {
    const daily = Math.max(1, Number(dailyGoalDraft) || data.dailyGoal);
    const monthly = Math.max(1, Number(monthlyGoalDraft) || data.monthlyGoal);
    setGoals(daily, monthly);
    toast.success(t("save"));
  };

  return (
    <main className="mx-auto flex w-full min-w-0 max-w-lg flex-1 flex-col gap-6 px-4 py-6">
      <div className="flex flex-col items-center gap-3 pt-2">
        <Avatar className="size-20 border-2 border-primary/30">
          <AvatarFallback className="bg-primary/10 text-2xl text-primary">
            {initials || "R"}
          </AvatarFallback>
        </Avatar>

        {editingName ? (
          <div className="flex items-center gap-2">
            <Input
              value={nameDraft}
              onChange={(e) => setNameDraft(e.target.value)}
              className="h-9 w-44 text-center"
              autoFocus
              maxLength={30}
            />
            <Button size="icon" className="size-9" onClick={saveName}>
              <CheckIcon />
            </Button>
            <Button
              size="icon"
              variant="ghost"
              className="size-9"
              onClick={() => {
                setNameDraft(data.userName);
                setEditingName(false);
              }}
            >
              <XIcon />
            </Button>
          </div>
        ) : (
          <button
            type="button"
            onClick={() => setEditingName(true)}
            className="flex items-center gap-1.5 text-lg font-semibold"
          >
            {data.userName}
            <PencilIcon className="size-3.5 text-muted-foreground" />
          </button>
        )}
      </div>

      {cloudSyncAvailable && <SyncCard />}

      <div className="grid grid-cols-2 gap-3">
        <StatCard label={t("total_written")} value={data.totalCount} />
        <StatCard label={t("total_malas")} value={totalMalaCount} />
        <StatCard label={t("current_streak")} value={`${data.currentStreak} ${t("days")}`} />
        <StatCard label={t("longest_streak")} value={`${data.longestStreak} ${t("days")}`} />
      </div>

      <Card>
        <CardHeader>
          <CardTitle className="text-sm font-medium">{t("writing_goals")}</CardTitle>
        </CardHeader>
        <CardContent className="flex flex-col gap-4">
          <div className="flex items-center justify-between gap-2">
            <Label htmlFor="daily-goal" className="min-w-0 shrink text-muted-foreground">
              {t("daily_goal")}
            </Label>
            <Input
              id="daily-goal"
              type="number"
              min={1}
              value={dailyGoalDraft}
              onChange={(e) => setDailyGoalDraft(e.target.value)}
              className="w-20 shrink-0"
            />
          </div>
          <div className="flex items-center justify-between gap-2">
            <Label htmlFor="monthly-goal" className="min-w-0 shrink text-muted-foreground">
              {t("monthly_goal")}
            </Label>
            <Input
              id="monthly-goal"
              type="number"
              min={MALA_COUNT}
              step={MALA_COUNT}
              value={monthlyGoalDraft}
              onChange={(e) => setMonthlyGoalDraft(e.target.value)}
              className="w-20 shrink-0"
            />
          </div>
          <Button onClick={saveGoals}>{t("save")}</Button>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle className="text-sm font-medium">{t("theme")}</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-3 gap-3">
            {THEME_ORDER.map((themeId) => {
              const theme = THEMES[themeId];
              const active = data.theme === themeId;
              return (
                <button
                  key={themeId}
                  type="button"
                  onClick={() => setTheme(themeId)}
                  className={cn(
                    "flex min-w-0 flex-col items-center gap-1.5 rounded-lg border p-2 transition-colors",
                    active ? "border-primary ring-2 ring-primary/30" : "border-border",
                  )}
                >
                  <span
                    className="size-7 shrink-0 rounded-full border"
                    style={{ backgroundColor: theme.primary }}
                  />
                  <span className="w-full text-[11px] leading-tight text-muted-foreground">
                    {data.language === "hi" ? theme.labelHi : theme.label}
                  </span>
                </button>
              );
            })}
          </div>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle className="text-sm font-medium">{t("language")}</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid grid-cols-2 gap-3">
            <Button
              variant={data.language === "en" ? "default" : "outline"}
              onClick={() => setLanguage("en")}
            >
              English
            </Button>
            <Button
              variant={data.language === "hi" ? "default" : "outline"}
              onClick={() => setLanguage("hi")}
              className="font-[family-name:var(--font-devanagari)]"
            >
              हिन्दी
            </Button>
          </div>
        </CardContent>
      </Card>

      <Separator />
      <p className="pb-4 text-center text-xs text-muted-foreground">
        {t("app_title")} · राम राम 🙏
      </p>
    </main>
  );
}

function GoogleIcon(props: React.SVGProps<SVGSVGElement>) {
  return (
    <svg viewBox="0 0 24 24" width="16" height="16" {...props}>
      <path
        fill="#4285F4"
        d="M23.52 12.27c0-.85-.08-1.67-.22-2.45H12v4.64h6.47a5.54 5.54 0 0 1-2.4 3.63v3h3.88c2.27-2.09 3.57-5.17 3.57-8.82Z"
      />
      <path
        fill="#34A853"
        d="M12 24c3.24 0 5.95-1.07 7.94-2.91l-3.88-3c-1.08.72-2.45 1.15-4.06 1.15-3.12 0-5.77-2.11-6.71-4.94H1.28v3.1A12 12 0 0 0 12 24Z"
      />
      <path
        fill="#FBBC05"
        d="M5.29 14.3A7.2 7.2 0 0 1 4.91 12c0-.8.14-1.57.38-2.3v-3.1H1.28A12 12 0 0 0 0 12c0 1.94.46 3.77 1.28 5.4l4.01-3.1Z"
      />
      <path
        fill="#EA4335"
        d="M12 4.75c1.77 0 3.35.61 4.6 1.8l3.44-3.44C17.94 1.19 15.24 0 12 0 7.31 0 3.26 2.69 1.28 6.6l4.01 3.1C6.23 6.86 8.88 4.75 12 4.75Z"
      />
    </svg>
  );
}

function SyncCard() {
  const { user, authLoading, signInWithEmail, signInWithGoogle, signOut, t } = usePractice();
  const [email, setEmail] = React.useState("");
  const [sending, setSending] = React.useState(false);

  const sendMagicLink = async () => {
    if (!email.trim()) return;
    setSending(true);
    const { error } = await signInWithEmail(email.trim());
    setSending(false);
    if (error) {
      toast.error(error);
    } else {
      toast.success(t("magic_link_sent"));
    }
  };

  return (
    <Card>
      <CardHeader>
        <CardTitle className="text-sm font-medium">{t("sync_title")}</CardTitle>
      </CardHeader>
      <CardContent>
        {authLoading ? (
          <div className="flex items-center justify-center py-2 text-muted-foreground">
            <LoaderCircleIcon className="size-4 animate-spin" />
          </div>
        ) : user ? (
          <div className="flex flex-col gap-3">
            <p className="text-sm text-muted-foreground">
              {t("signed_in_as")} <span className="font-medium text-foreground">{user.email}</span>
            </p>
            <Button variant="outline" onClick={() => void signOut()}>
              {t("sign_out")}
            </Button>
          </div>
        ) : (
          <div className="flex flex-col gap-3">
            <p className="text-sm text-muted-foreground">{t("sync_subtitle")}</p>
            <div className="flex gap-2">
              <Input
                type="email"
                placeholder={t("email_placeholder")}
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="min-w-0 flex-1"
              />
              <Button onClick={() => void sendMagicLink()} disabled={sending}>
                {t("send_magic_link")}
              </Button>
            </div>
            <div className="flex items-center gap-3">
              <Separator className="flex-1" />
              <span className="text-xs text-muted-foreground">{t("or_divider")}</span>
              <Separator className="flex-1" />
            </div>
            <Button variant="outline" onClick={() => void signInWithGoogle()}>
              <GoogleIcon /> {t("continue_with_google")}
            </Button>
          </div>
        )}
      </CardContent>
    </Card>
  );
}

function StatCard({ label, value }: { label: string; value: React.ReactNode }) {
  return (
    <div className="shadow-soft min-w-0 rounded-2xl border bg-card px-4 py-3.5">
      <div className="truncate text-xl font-semibold">{value}</div>
      <div className="truncate text-xs text-muted-foreground">{label}</div>
    </div>
  );
}
