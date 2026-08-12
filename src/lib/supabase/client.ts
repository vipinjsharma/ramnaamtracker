"use client";

import { createBrowserClient } from "@supabase/ssr";

import { isSupabaseConfigured, supabaseAnonKey, supabaseUrl } from "@/lib/supabase/env";

let browserClient: ReturnType<typeof createBrowserClient> | null = null;

/** Returns null when Supabase isn't configured - callers must handle that. */
export function getSupabaseBrowserClient() {
  if (!isSupabaseConfigured) return null;
  if (!browserClient) {
    browserClient = createBrowserClient(supabaseUrl!, supabaseAnonKey!);
  }
  return browserClient;
}
