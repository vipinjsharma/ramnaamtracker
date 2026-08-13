import type { Language } from "@/lib/types";

const translations = {
  en: {
    app_title: "Ram Lekhak",
    today_count: "Today's Count",
    today_mala: "Today's Malas",
    total_count: "Total Count",
    write_ram: "Write RAM",
    stats: "Stats",

    clear: "Clear",
    submit: "Submit",
    keep_writing: "Keep writing राम to fill a mala (108).",

    profile: "Profile",
    total_written: "Total RAM Written",
    total_malas: "Total Malas",
    current_streak: "Current Streak",
    longest_streak: "Longest Streak",
    daily_goal: "Daily Goal",
    monthly_goal: "Monthly Goal",
    writing_goals: "Writing Goals",
    theme: "Theme",
    save: "Save",
    days: "days",

    ram_writing: "Ram Naam Writing",
    language: "Language",

    cancel: "Cancel",

    name_updated: "Name updated!",
    name_length_error: "Name must be between 2 and 30 characters",
    edit_name: "Edit Name",

    nav_home: "Home",
    nav_write: "Write",
    nav_profile: "Profile",

    home_greeting: "Ram Ram",
    home_subtitle: "Every राम you write is a step on the path.",
    home_cta: "Start Writing",
    home_daily_progress: "Today's progress",
    home_monthly_progress: "This month",

    mala_completed: "Congratulations! You completed {count} mala{plural}!",

    sync_title: "Sync across devices",
    sync_subtitle: "Sign in to keep your progress when you switch phones or browsers.",
    email_placeholder: "you@example.com",
    send_magic_link: "Send magic link",
    magic_link_sent: "Check your email for a sign-in link.",
    or_divider: "or",
    continue_with_google: "Continue with Google",
    signed_in_as: "Signed in as",
    sign_out: "Sign out",
  },
  hi: {
    app_title: "राम लेखक",
    today_count: "आज की गिनती",
    today_mala: "आज की माला",
    total_count: "कुल गिनती",
    write_ram: "राम लिखें",
    stats: "आंकड़े",

    clear: "साफ़ करें",
    submit: "जमा करें",
    keep_writing: "एक माला (108) पूरी करने के लिए राम लिखते रहें।",

    profile: "प्रोफ़ाइल",
    total_written: "कुल लिखित राम",
    total_malas: "कुल मालाएँ",
    current_streak: "वर्तमान स्ट्रीक",
    longest_streak: "सबसे लंबी स्ट्रीक",
    daily_goal: "दैनिक लक्ष्य",
    monthly_goal: "मासिक लक्ष्य",
    writing_goals: "लेखन लक्ष्य",
    theme: "थीम",
    save: "सहेजें",
    days: "दिन",

    ram_writing: "राम नाम लेखन",
    language: "भाषा",

    cancel: "रद्द करें",

    name_updated: "नाम अपडेट हो गया!",
    name_length_error: "नाम 2 से 30 अक्षरों के बीच होना चाहिए",
    edit_name: "नाम संपादित करें",

    nav_home: "होम",
    nav_write: "लिखें",
    nav_profile: "प्रोफ़ाइल",

    home_greeting: "राम राम",
    home_subtitle: "आप जो भी राम लिखते हैं वह पथ पर एक कदम है।",
    home_cta: "लिखना शुरू करें",
    home_daily_progress: "आज की प्रगति",
    home_monthly_progress: "इस महीने",

    mala_completed: "बधाई हो! आपने {count} माला{plural} पूरी कर ली!",

    sync_title: "डिवाइस पर सिंक करें",
    sync_subtitle: "फ़ोन या ब्राउज़र बदलने पर अपनी प्रगति सुरक्षित रखने के लिए साइन इन करें।",
    email_placeholder: "aap@example.com",
    send_magic_link: "मैजिक लिंक भेजें",
    magic_link_sent: "साइन-इन लिंक के लिए अपना ईमेल देखें।",
    or_divider: "या",
    continue_with_google: "Google से जारी रखें",
    signed_in_as: "साइन इन किया गया",
    sign_out: "साइन आउट",
  },
} satisfies Record<Language, Record<string, string>>;

export type TranslationKey = keyof (typeof translations)["en"];

export function translate(
  language: Language,
  key: TranslationKey,
  vars?: Record<string, string | number>,
) {
  let text = translations[language][key] ?? translations.en[key];
  if (vars) {
    for (const [k, v] of Object.entries(vars)) {
      text = text.replaceAll(`{${k}}`, String(v));
    }
  }
  return text;
}
