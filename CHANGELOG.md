# Changelog

All notable changes to InstantXP are documented here.
The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).
InstantXP is versioned semantically (`MAJOR.MINOR.PATCH`).
It runs on Minecraft 26.3 (Fabric + Forge + NeoForge, Java 25) and requires
[Mycel](https://github.com/MyCelium-GG/MyCel-Lib) (`my.celium.org`).

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
