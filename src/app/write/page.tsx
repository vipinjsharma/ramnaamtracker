"use client";

import { usePractice } from "@/lib/practice-store";
import { WritingCanvas } from "@/components/writing-canvas";
import { Progress } from "@/components/ui/progress";

export default function WritePage() {
  const { data, todayMalaCount, t } = usePractice();
  const dailyPercent = Math.min(100, Math.round((data.todayCount / data.dailyGoal) * 100));

  return (
    <main className="mx-auto flex w-full min-w-0 max-w-lg flex-1 flex-col gap-5 px-4 py-6">
      <div className="grid grid-cols-3 gap-3 text-center">
        <div className="min-w-0">
          <div className="text-2xl font-semibold">{data.todayCount}</div>
          <div className="text-[11px] text-muted-foreground">{t("today_count")}</div>
        </div>
        <div className="min-w-0">
          <div className="text-2xl font-semibold">{todayMalaCount}</div>
          <div className="text-[11px] text-muted-foreground">{t("today_mala")}</div>
        </div>
        <div className="min-w-0">
          <div className="text-2xl font-semibold">{data.totalCount}</div>
          <div className="text-[11px] text-muted-foreground">{t("total_count")}</div>
        </div>
      </div>

      <Progress value={dailyPercent} />

      <WritingCanvas />
    </main>
  );
}
