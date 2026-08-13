const CACHE_NAME = "ram-lekhak-v2";
const CORE_ASSETS = ["/", "/write", "/profile", "/manifest.webmanifest"];

self.addEventListener("install", (event) => {
  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => cache.addAll(CORE_ASSETS)),
  );
  self.skipWaiting();
});

self.addEventListener("activate", (event) => {
  event.waitUntil(
    caches
      .keys()
      .then((keys) =>
        Promise.all(keys.filter((key) => key !== CACHE_NAME).map((key) => caches.delete(key))),
      ),
  );
  self.clients.claim();
});

self.addEventListener("fetch", (event) => {
  if (event.request.method !== "GET") return;

  const url = new URL(event.request.url);
  if (url.origin !== self.location.origin) return;

  // Never cache Supabase auth/session traffic - it must always hit the network.
  if (url.pathname.startsWith("/auth/")) return;

  // HTML documents (navigations) must be network-first: the same URL
  // (e.g. /write) serves a different HTML shell after every deploy, since
  // it references that build's content-hashed JS/CSS. Serving it
  // stale-first would keep showing an old build indefinitely, only ever
  // updating in the background for a load that never comes. Only fall
  // back to cache when there's genuinely no network (offline).
  if (event.request.mode === "navigate") {
    event.respondWith(
      fetch(event.request)
        .then((response) => {
          const copy = response.clone();
          caches.open(CACHE_NAME).then((cache) => cache.put(event.request, copy));
          return response;
        })
        .catch(() => caches.match(event.request)),
    );
    return;
  }

  // Static, content-hashed assets (JS/CSS/images) are safe to serve
  // stale-while-revalidate - their URL changes whenever their content does.
  event.respondWith(
    caches.match(event.request).then((cached) => {
      const network = fetch(event.request)
        .then((response) => {
          if (response.ok) {
            const copy = response.clone();
            caches.open(CACHE_NAME).then((cache) => cache.put(event.request, copy));
          }
          return response;
        })
        .catch(() => cached);
      return cached || network;
    }),
  );
});
