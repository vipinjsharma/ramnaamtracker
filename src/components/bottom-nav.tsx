"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { motion } from "framer-motion";
import { HomeIcon, PenLineIcon, UserRoundIcon } from "lucide-react";

import { usePractice } from "@/lib/practice-store";
import { cn } from "@/lib/utils";

const NAV_ITEMS = [
  { href: "/", labelKey: "nav_home", icon: HomeIcon },
  { href: "/write", labelKey: "nav_write", icon: PenLineIcon },
  { href: "/profile", labelKey: "nav_profile", icon: UserRoundIcon },
] as const;

export function BottomNav() {
  const pathname = usePathname();
  const { t } = usePractice();

  return (
    <nav
      className="fixed inset-x-0 bottom-0 z-40 border-t bg-background/95 pb-[env(safe-area-inset-bottom)] backdrop-blur supports-[backdrop-filter]:bg-background/80"
      aria-label="Primary"
    >
      <div className="mx-auto flex h-16 w-full max-w-lg items-stretch justify-around">
        {NAV_ITEMS.map(({ href, labelKey, icon: Icon }) => {
          const active = href === "/" ? pathname === "/" : pathname.startsWith(href);
          return (
            <Link
              key={href}
              href={href}
              className="relative flex flex-1 flex-col items-center justify-center gap-1"
            >
              {active && (
                <motion.span
                  layoutId="bottom-nav-active"
                  className="absolute top-1.5 h-1 w-8 rounded-full bg-primary"
                  transition={{ type: "spring", stiffness: 500, damping: 35 }}
                />
              )}
              <Icon
                className={cn(
                  "size-5.5 transition-colors",
                  active ? "text-primary" : "text-muted-foreground",
                )}
                strokeWidth={active ? 2.4 : 2}
              />
              <span
                className={cn(
                  "text-[11px] font-medium transition-colors",
                  active ? "text-primary" : "text-muted-foreground",
                )}
              >
                {t(labelKey)}
              </span>
            </Link>
          );
        })}
      </div>
    </nav>
  );
}
