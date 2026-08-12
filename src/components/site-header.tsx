"use client";

import Link from "next/link";
import { MenuIcon, PenLineIcon, HomeIcon, UserRoundIcon } from "lucide-react";

import { usePractice } from "@/lib/practice-store";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import {
  Sheet,
  SheetClose,
  SheetContent,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/components/ui/sheet";

const NAV_ITEMS = [
  { href: "/", labelKey: "nav_home", icon: HomeIcon },
  { href: "/write", labelKey: "nav_write", icon: PenLineIcon },
  { href: "/profile", labelKey: "nav_profile", icon: UserRoundIcon },
] as const;

export function SiteHeader() {
  const { data, t } = usePractice();
  const initials = data.userName
    .split(" ")
    .map((part) => part[0])
    .slice(0, 2)
    .join("")
    .toUpperCase();

  return (
    <header className="sticky top-0 z-40 flex h-14 items-center justify-between border-b bg-primary px-2 text-primary-foreground shadow-sm">
      <Sheet>
        <SheetTrigger asChild>
          <Button
            variant="ghost"
            size="icon"
            className="text-primary-foreground hover:bg-primary-foreground/10 hover:text-primary-foreground"
            aria-label="Open menu"
          >
            <MenuIcon />
          </Button>
        </SheetTrigger>
        <SheetContent side="left">
          <SheetHeader>
            <SheetTitle className="font-[family-name:var(--font-devanagari)] text-2xl">
              {t("app_title")}
            </SheetTitle>
          </SheetHeader>
          <nav className="flex flex-col gap-1 px-2">
            {NAV_ITEMS.map(({ href, labelKey, icon: Icon }) => (
              <SheetClose asChild key={href}>
                <Link
                  href={href}
                  className="flex items-center gap-3 rounded-md px-3 py-2.5 text-sm font-medium text-foreground hover:bg-accent"
                >
                  <Icon className="size-4.5 text-muted-foreground" />
                  {t(labelKey)}
                </Link>
              </SheetClose>
            ))}
          </nav>
        </SheetContent>
      </Sheet>

      <Link
        href="/"
        className="font-[family-name:var(--font-devanagari)] text-xl font-semibold tracking-wide"
      >
        {t("app_title")}
      </Link>

      <Link href="/profile">
        <Avatar className="size-8 border border-primary-foreground/40">
          <AvatarFallback className="bg-primary-foreground/15 text-xs text-primary-foreground">
            {initials || "R"}
          </AvatarFallback>
        </Avatar>
      </Link>
    </header>
  );
}
