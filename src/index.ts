import { registerPlugin } from "@capacitor/core";

import type { TolutronicsCapacitorSqlitePlugin } from "./definitions";

const TolutronicsCapacitorSqlite =
  registerPlugin<TolutronicsCapacitorSqlitePlugin>(
    "TolutronicsCapacitorSqlite",
    {
      web: () =>
        import("./web").then((m) => new m.TolutronicsCapacitorSqliteWeb()),
      electron: () =>
        (window as any).CapacitorCustomPlatform.plugins
          .CapacitorDataStorageSqlite,
    },
  );

export * from "./definitions";
export { TolutronicsCapacitorSqlite };
