import type { ThemeId } from "@/lib/types";

export interface ThemeDefinition {
  id: ThemeId;
  label: string;
  labelHi: string;
  primary: string;
  primaryForeground: string;
  background: string;
  text: string;
}

export const THEMES: Record<ThemeId, ThemeDefinition> = {
  ram: {
    id: "ram",
    label: "Ram (Saffron)",
    labelHi: "राम (केसरिया)",
    primary: "#ff7817",
    primaryForeground: "#ffffff",
    background: "#fff7e6",
    text: "#333333",
  },
  krishna: {
    id: "krishna",
    label: "Krishna (Blue)",
    labelHi: "कृष्ण (नीला)",
    primary: "#2874a6",
    primaryForeground: "#ffffff",
    background: "#ebf5fb",
    text: "#1a5276",
  },
  lakshmi: {
    id: "lakshmi",
    label: "Lakshmi (Gold)",
    labelHi: "लक्ष्मी (सुनहरा)",
    primary: "#d4ac0d",
    primaryForeground: "#ffffff",
    background: "#fef9e7",
    text: "#7d6608",
  },
  ganesh: {
    id: "ganesh",
    label: "Ganesh (Red)",
    labelHi: "गणेश (लाल)",
    primary: "#c0392b",
    primaryForeground: "#ffffff",
    background: "#fdedec",
    text: "#641e16",
  },
  shiva: {
    id: "shiva",
    label: "Shiva (Purple)",
    labelHi: "शिव (बैंगनी)",
    primary: "#7d3c98",
    primaryForeground: "#ffffff",
    background: "#f4ecf7",
    text: "#4a235a",
  },
  light: {
    id: "light",
    label: "Light",
    labelHi: "हल्का",
    primary: "#404040",
    primaryForeground: "#ffffff",
    background: "#ffffff",
    text: "#171717",
  },
};

export const THEME_ORDER: ThemeId[] = [
  "ram",
  "krishna",
  "lakshmi",
  "ganesh",
  "shiva",
  "light",
];
