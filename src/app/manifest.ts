import type { MetadataRoute } from "next";

export default function manifest(): MetadataRoute.Manifest {
  return {
    name: "Ram Lekhak (राम लेखक)",
    short_name: "Ram Lekhak",
    description: "Write राम daily. Track your streak, malas, and practice.",
    start_url: "/",
    display: "standalone",
    background_color: "#fff7e6",
    theme_color: "#ff7817",
    orientation: "portrait",
    icons: [
      { src: "/icons/icon-192.png", sizes: "192x192", type: "image/png" },
      { src: "/icons/icon-512.png", sizes: "512x512", type: "image/png" },
      {
        src: "/icons/icon-maskable-512.png",
        sizes: "512x512",
        type: "image/png",
        purpose: "maskable",
      },
    ],
  };
}
