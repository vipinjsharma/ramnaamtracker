"use client";

import type * as React from "react";
import Link from "next/link";
import { motion } from "framer-motion";
import { FlameIcon, PenLineIcon } from "lucide-react";

import { usePractice } from "@/lib/practice-store";
import { MALA_COUNT } from "@/lib/types";
import { Button } from "@/components/ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Progress } from "@/components/ui/progress";

export default function Home() {
  const { data, todayMalaCount, loaded, t } = usePractice();

  const dailyPercent = loaded
    ? Math.min(100, Math.round((data.todayCount / data.dailyGoal) * 100))
    : 0;
  const monthlyPercent = loaded
    ? Math.min(100, Math.round((data.currentMonthCount / data.monthlyGoal) * 100))
    : 0;

  return (
    <main className="mx-auto flex w-full min-w-0 max-w-lg flex-1 flex-col gap-6 px-4 py-6">
      <motion.section
        initial={{ opacity: 0, y: 8 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.4 }}
        className="flex flex-col items-center gap-2 py-4 text-center"
      >
        <h1 className="font-[family-name:var(--font-devanagari)] text-3xl font-semibold">
          {t("home_greeting")}, {data.userName}
        </h1>
        <p className="max-w-xs text-sm text-muted-foreground">{t("home_subtitle")}</p>
      </motion.section>

      <motion.div
        initial={{ opacity: 0, y: 8 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.4, delay: 0.05 }}
        className="grid min-w-0 grid-cols-3 gap-3"
      >
        <StatTile label={t("today_count")} value={data.todayCount} />
        <StatTile label={t("today_mala")} value={todayMalaCount} />
        <StatTile
          label={t("current_streak")}
          value={data.currentStreak}
          icon={<FlameIcon className="size-3.5 text-primary" />}
        />
      </motion.div>

      <motion.div
        initial={{ opacity: 0, y: 8 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.4, delay: 0.1 }}
      >
        <Card>
          <CardHeader>
            <CardTitle className="text-sm font-medium text-muted-foreground">
              {t("home_daily_progress")}
            </CardTitle>
          </CardHeader>
          <CardContent className="flex flex-col gap-4">
            <div>
              <div className="mb-1.5 flex items-baseline justify-between text-sm">
                <span className="font-medium">
                  {data.todayCount} / {data.dailyGoal}
                </span>
                <span className="text-muted-foreground">{dailyPercent}%</span>
              </div>
              <Progress value={dailyPercent} />
            </div>
            <div>
              <div className="mb-1.5 flex items-baseline justify-between gap-2 text-sm">
                <span className="shrink-0 font-medium text-muted-foreground">
                  {t("home_monthly_progress")}
                </span>
                <span className="truncate text-muted-foreground">
                  {Math.floor(data.currentMonthCount / MALA_COUNT)} /{" "}
                  {Math.ceil(data.monthlyGoal / MALA_COUNT)} malas
                </span>
              </div>
              <Progress value={monthlyPercent} indicatorClassName="bg-primary/70" />
            </div>
          </CardContent>
        </Card>
      </motion.div>

      <motion.div
        initial={{ opacity: 0, y: 8 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.4, delay: 0.15 }}
        className="mt-auto pb-2"
      >
        <Button asChild size="lg" className="w-full">
          <Link href="/write">
            <PenLineIcon /> {t("home_cta")}
          </Link>
        </Button>
      </motion.div>
    </main>
  );
}

function StatTile({
  label,
  value,
  icon,
}: {
  label: string;
  value: number;
  icon?: React.ReactNode;
}) {
  return (
    <div className="flex min-w-0 flex-col items-center gap-1 rounded-xl border bg-card px-2 py-3 text-center shadow-sm">
      <div className="flex items-center gap-1 text-xl font-semibold">
        {icon}
        {value}
      </div>
      <div className="w-full text-[11px] leading-tight text-muted-foreground">
        {label}
      </div>
    </div>
  );
}
