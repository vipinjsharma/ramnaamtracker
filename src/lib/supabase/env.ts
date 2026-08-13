/**
 * Cloud sync is optional: until these env vars are set (in .env.local for
 * dev, or the Vercel project settings for prod), the app runs entirely on
 * localStorage, exactly as before. Nothing that depends on Supabase should
 * ever throw when it's unconfigured - it should just quietly not offer sync.
 */
export const supabaseUrl = process.env.NEXT_PUBLIC_SUPABASE_URL;
export const supabaseAnonKey = process.env.NEXT_PUBLIC_SUPABASE_ANON_KEY;

export const isSupabaseConfigured = Boolean(supabaseUrl && supabaseAnonKey);
