"use client";

import Link from "next/link";

import { usePractice } from "@/lib/practice-store";

export function SiteHeader() {
  const { t } = usePractice();

  return (
    <header className="sticky top-0 z-40 border-b bg-primary pt-[env(safe-area-inset-top)] text-primary-foreground shadow-sm">
      <div className="mx-auto flex h-14 w-full max-w-lg items-center justify-center px-4">
        <Link
          href="/"
          className="font-[family-name:var(--font-devanagari-display)] text-2xl tracking-wide"
        >
          {t("app_title")}
        </Link>
      </div>
    </header>
  );
}
