<p align="center">
  <img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/header.svg" alt="InstantXP" width="100%">
</p>

<p align="center">
  <a href="https://www.curseforge.com/minecraft/mc-mods/instant-xp-pickup"><img src="https://img.shields.io/curseforge/dt/1726939?style=flat-square&logo=curseforge&logoColor=white&label=downloads&color=F16436" alt="CurseForge downloads"></a>
  <a href="https://www.curseforge.com/minecraft/mc-mods/instant-xp-pickup/files"><img src="https://img.shields.io/badge/Minecraft-26.1%20--%2026.3-62B47A?style=flat-square" alt="Minecraft 26.1 to 26.3"></a>
  <img src="https://img.shields.io/badge/loaders-Fabric%2C%20Forge%2C%20NeoForge-1E88E5?style=flat-square" alt="Fabric, Forge, NeoForge">
  <img src="https://img.shields.io/badge/side-server%20only-8E44AD?style=flat-square" alt="Server only">
  <img src="https://img.shields.io/badge/Java-25-E76F00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 25">
  <a href="https://www.curseforge.com/minecraft/mc-mods/mycel"><img src="https://img.shields.io/badge/requires-Mycel-F16436?style=flat-square&logo=curseforge&logoColor=white" alt="Requires Mycel"></a>
  <a href="https://wiki.mycelium-gg.org/instantxp/"><img src="https://img.shields.io/badge/docs-wiki-0A7BBB?style=flat-square" alt="Docs on the wiki"></a>
  <a href="https://github.com/MyCelium-GG/Instant-XP/blob/main/LICENSE"><img src="https://img.shields.io/github/license/MyCelium-GG/Instant-XP?style=flat-square&color=F2C14E" alt="License"></a>
</p>

<a href="https://www.curseforge.com/minecraft/mc-mods/mycel"><img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/8af308e5bb70/docs/img/banners/mycel-required.svg" alt="Mycel is required: this mod needs the Mycel library to run. Install Mycel next to it, for the same Minecraft version and loader. Download it from CurseForge." width="100%"></a>

![About the mod](https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/about-the-mod.svg)

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/about.svg" alt="Never walk over XP orbs again. Every server tick, orbs within a few blocks of you are picked up instantly, as if you had stepped on them. Bottle your XP with an empty glass bottle and get exactly that amount back later. Server side, no keybinds." width="100%">

![The hotbar: Bottle o' Enchanting (50 XP) selected, three bottles stacked, level 28](https://wiki.mycelium-gg.org/instantxp/img/xp-bottles.png?v=120)

![Behavior](https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/behavior.svg)

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/behavior-box.svg" alt="Instant pickup: Each server tick, every XP orb inside radius blocks of a player is collected. Vanilla path: Collection goes through vanilla's own pickup, so Mending repairs, pickup sounds, statistics and the orb-take animation all work as usual. No waiting: Vanilla's two-tick pacing between orbs is bypassed, so huge piles from farms are absorbed in a single tick. Who collects: Dead, removed and sp" width="100%">

![Before: a field full of XP orbs. After: the same field, empty](https://wiki.mycelium-gg.org/instantxp/img/before-after.jpg)

![Bottle your XP](https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/bottle-your-xp.svg)

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/bottle-your-xp-box.svg" alt="Fill: Sneak and use an empty glass bottle (not at water). 50 XP of yours goes into a Bottle o' Enchanting named with the exact amount, for example Bottle o' Enchanting (50 XP). With less XP, the bottle takes what you have. Throw: It gives back exactly that XP, not vanilla's random 3 to 11, and instant pickup collects it straight away. Stacks: Bottles with the same amount stack. Change the amount w" width="100%">

![Anonymous stats](https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/anonymous-stats.svg)

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/anonymous-stats-box.svg" alt="Instant XP sends anonymous stats (how many XP orbs get collected, the radius, bottles filled and thrown, and errors in its own code) to its FastStats project through Mycel. It follows Mycel's choice exactly: the one-time screen in your own game, shareStats and shareErrors in config/mycel.json on servers." width="100%">

![Configuration](https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/configuration.svg)

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/configuration-table.svg" alt="Configuration table: enabled, true, Instant pickup on or off; radius, 8, Collection radius in blocks around each player (1 to 64); xpBottles, true, Sneak and use an empty glass bottle to bottle your XP; bottleXp, 50, XP points per bottle (1 to 1000)" width="100%">

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/configuration-box.svg" alt="Reload live with /mycel reload instantxp. A config screen is available from the mod list on Forge and NeoForge." width="100%">

![Install](https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/install.svg)

<img src="https://raw.githubusercontent.com/MyCelium-GG/Instant-XP/060898cb8412/docs/img/banners/install-box.svg" alt="Server side: install it on the server, or in your own game for singleplayer. Requires Mycel. Minecraft 26.1, 26.2 and 26.3 on Fabric, Forge and NeoForge, Java 25. On Fabric you also need Fabric API. Without Mycel the game still starts: the mod stays off and tells joining players." width="100%">
