# Changelog

All notable changes to InstantXP are documented here.
The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).
InstantXP is versioned semantically (`MAJOR.MINOR.PATCH`).
It runs on Minecraft 26.3, 26.2 and 26.1 (Fabric + Forge + NeoForge, Java 25) and requires
[Mycel](https://github.com/MyCelium-GG/MyCel-Lib) (`my.celium.org`).

## [1.4.0] - 2026-11-25

### Added
- XP counter: "+37 XP" above the hotbar while you pick up XP. Quick pickups add up into one number. Setting `xpCounter`.
- Death bottle: when you die, half of your XP is left in a Bottle o' Enchanting where you fell, instead of vanilla's few orbs (which give back at most 100 points). Pick it up and throw it to get exactly that XP back. Settings `deathBottle` and `deathBottlePercent` (1 to 100, default 50). Nothing changes with `keepInventory` on.
- Both work for players without the mod: everything happens on the server.

## [1.3.0] - 2026-11-19

### Added
- In 11 languages: English, German, Spanish, French, Dutch, Brazilian Portuguese, Russian, Polish, Italian, Japanese and Simplified Chinese. Messages from the server reach each player in their own game language.

### Changed
- Needs Mycel 1.7.0.

## [1.2.0] - 2026-11-16

### Added
- Bottle your XP: sneak and use an empty glass bottle to put 50 XP into a Bottle o' Enchanting named with the exact amount ("Bottle o' Enchanting (50 XP)"). Throwing it gives exactly that XP back, not vanilla's random 3 to 11. Bottles with the same amount stack. Settings `xpBottles` and `bottleXp` (1 to 1000).
- Works for players without the mod too: everything happens on the server. Filling water bottles and dragon's breath works as before.

### Changed
- Installed without Mycel, the game still starts: InstantXP stays off and tells joining players that Mycel is missing, with a download link.

## [1.1.2] - 2026-11-04

### Changed
- Built for Mycel 1.6.1: chat messages get the new MyCelium look, and Mycel can share anonymous stats if you agree. What is sent: https://wiki.mycelium-gg.org/privacy#mycel-stats

### Added
- Anonymous stats (how many XP orbs get collected and the radius) so we know what to improve. They follow your answer on Mycel's stats screen; nothing else is asked. What is sent: https://wiki.mycelium-gg.org/privacy#stats-instantxp

## [1.1.1] - 2026-11-02

### Changed
- Settings screen: on/off settings now show their real state when it opens (fixed in Mycel).
- Built against the newest Fabric API, Forge and NeoForge, including the first stable NeoForge for 26.3.

## [1.1.0] - 2026-10-28

### Added

- Minecraft 26.2 and 26.1 (26.1, 26.1.1, 26.1.2) support. Each release now
  has jars for 26.3, 26.2 and 26.1 on Fabric, Forge and NeoForge; pick the
  ones named for your Minecraft.

### Changed

- Requires Mycel 1.5.1 (for the same Minecraft version).

## [1.0.4] - 2026-10-26

### Added

- An icon in the mod list on Fabric, Forge and NeoForge.

### Changed

- Requires Mycel 1.4.1.

## [1.0.3] - 2026-10-25

### Changed

- Requires Mycel 1.4.0. Operators now get one chat line per session when a
  MyCelium mod has an update, and `/mycel mods` marks it.

## [1.0.2] - 2026-10-19

### Changed

- Updated Mycel lib to 1.3.0: Mycel's chat messages now use the MyCelium wiki's
  red style.
- Checks for new versions through the MyCelium wiki (one log line when an
  update is out; turn off with `enableUpdateChecker` in `config/mycel.json`).

## [1.0.1] - 2026-10-10

### Changed

- Updated Mycel lib to 1.2.1.

## [1.0.0] - 2026-10-10

### Added

- Initial release.
- Server-tick pickup: every XP orb inside `radius` blocks of a player is
  collected instantly, as if stepped on. Silent, server-side, no keybinds.
- Collection goes through vanilla's own pickup path, so Mending repairs,
  pickup sounds, statistics and the orb-take animation work exactly as usual.
- Vanilla's two-tick pacing between orbs is bypassed, so even huge piles
  from farms are absorbed in a single tick instead of draining over seconds.
- Dead, removed and spectator players collect nothing.
- Config (`config/instantxp.json`): `enabled`, `radius` (default 8,
  range 1-64). Reload live with `/mycel reload instantxp`; config screen
  available from the mod list on Forge and NeoForge.
- Built on Mycel 1.2.1 (`com.github.MyCelium-GG:MyCel-Lib:1.2.1` via JitPack).
