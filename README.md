# Ram Lekhak (राम लेखक)

A Ram Naam writing/tracking app, being migrated from a static Replit
(HTML/CSS/vanilla JS + Android WebView) app to a modern web stack.

## Stack

- [Next.js](https://nextjs.org) (App Router, TypeScript, Turbopack)
- [Tailwind CSS](https://tailwindcss.com)
- [shadcn/ui](https://ui.shadcn.com) (Radix UI primitives + `class-variance-authority`)
- [Framer Motion](https://www.framer.com/motion/) for animations
- Optimized for zero-config deployment on [Vercel](https://vercel.com)

## Getting started

```bash
npm install
npm run dev
```

Open [http://localhost:3000](http://localhost:3000).

Other scripts: `npm run build`, `npm run start`, `npm run lint`.

## Adding shadcn/ui components

The `shadcn` CLI needs network access to `ui.shadcn.com`, which isn't
reachable from this environment's sandboxed proxy. Component source files
are added manually under `src/components/ui/` instead, following the
standard shadcn/ui output for the `new-york` style (see `components.json`).

## `legacy/`

The original Replit app (static HTML/CSS/JS web app plus a native Kotlin
Android WebView wrapper) is preserved under [`legacy/`](./legacy) for
reference during the migration. It is not part of the new app's build.
