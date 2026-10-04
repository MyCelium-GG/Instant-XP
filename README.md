# InstantXP

[![CI](https://github.com/MyCelium-GG/Instant-XP/actions/workflows/build.yml/badge.svg)](https://github.com/MyCelium-GG/Instant-XP/actions/workflows/build.yml)
[![License](https://img.shields.io/github/license/MyCelium-GG/Instant-XP)](LICENSE)
[![Last commit](https://img.shields.io/github/last-commit/MyCelium-GG/Instant-XP)](https://github.com/MyCelium-GG/Instant-XP/commits/main)

Never walk over XP orbs again. Every server tick, orbs within a few blocks of
you are picked up instantly, as if you had stepped on them. Silent,
server side, no keybinds.

Built on [Mycel](https://github.com/MyCelium-GG/MyCel-Lib) (`my.celium.org`), the shared
core for the MyCelium ecosystem. This mod is about 100 lines of feature code because
configuration, platform abstraction, scheduling and diagnostics are reused.

| | |
|---|---|
| Mod id | `instantxp` |
| Minecraft | **26.3** · Java **25** |
| Loaders | Fabric, Forge, NeoForge |

## Behavior

- Each server tick, every XP orb inside `radius` blocks of a player is collected.
- Collection goes through vanilla's own pickup path, so Mending repairs,
  pickup sounds, statistics and the orb-take animation all work exactly as usual.
- Vanilla's two-tick pacing between orbs is bypassed, so even huge piles from
  farms are absorbed in a single tick instead of draining over seconds.
- Dead, removed and spectator players collect nothing.

## Configuration (`config/instantxp.json`)

| Key | Default | Meaning |
|---|---|---|
| `enabled` | `true` | Master switch (also toggleable live via `Mycel.setEnabled`). |
| `radius` | `8` | Collection radius in blocks around each player (1-64). |

Reload live with `/mycel reload instantxp`. A config screen is
available from the mod list on Forge and NeoForge.

## Building

JDK 25, then:

```sh
./gradlew build            # all loaders + tests
./gradlew :common:test     # unit tests only
```

Mycel itself comes from [JitPack](https://jitpack.io/#MyCelium-GG/MyCel-Lib)
as one universal jar for all loaders
(`com.github.MyCelium-GG:MyCel-Lib:1.1.0`). No local setup needed.
