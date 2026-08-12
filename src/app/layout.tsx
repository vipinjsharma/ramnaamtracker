import type { Metadata, Viewport } from "next";
import { Geist, Geist_Mono, Noto_Sans_Devanagari, Tiro_Devanagari_Hindi } from "next/font/google";
import "./globals.css";
import { AppProviders } from "@/components/app-providers";
import { SiteHeader } from "@/components/site-header";
import { BottomNav } from "@/components/bottom-nav";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

const notoDevanagari = Noto_Sans_Devanagari({
  variable: "--font-devanagari",
  subsets: ["devanagari"],
});

const tiroDevanagari = Tiro_Devanagari_Hindi({
  variable: "--font-devanagari-display",
  subsets: ["devanagari"],
  weight: "400",
});

export const metadata: Metadata = {
  title: "Ram Lekhak | राम लेखक",
  description: "Practice writing Ram Naam and track your daily japa progress.",
};

export const viewport: Viewport = {
  themeColor: "#ff7817",
  width: "device-width",
  initialScale: 1,
  viewportFit: "cover",
};

export default function RootLayout({ children }: LayoutProps<"/">) {
  return (
    <html
      lang="en"
      className={`${geistSans.variable} ${geistMono.variable} ${notoDevanagari.variable} ${tiroDevanagari.variable} h-full antialiased`}
    >
      <body className="flex min-h-full flex-col overflow-x-hidden">
        <AppProviders>
          <SiteHeader />
          <div className="flex flex-1 flex-col pb-[calc(4rem+env(safe-area-inset-bottom))]">
            {children}
          </div>
          <BottomNav />
        </AppProviders>
      </body>
    </html>
  );
}
