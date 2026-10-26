package my.celium.org.instantxp;

import my.celium.org.Mycel;
import my.celium.org.config.ConfigBuilder;
import my.celium.org.config.ConfigValue;
import my.celium.org.config.MycelConfig;
import my.celium.org.metadata.ModMetadata;
import net.minecraft.server.MinecraftServer;

/**
 * InstantXP: XP orbs are picked up the moment they come close.
 *
 * <p>Every server tick each loader's native tick hook calls {@link #tickServer},
 * which collects all XP orbs inside {@code radius} blocks around every player.
 * Collection goes through vanilla's own {@code ExperienceOrb.playerTouch} path,
 * so Mending, pickup sounds, statistics and the take animation all behave
 * exactly as if the player had walked over the orb.
 *
 * <p>The whole mod is this file plus {@link InstantXpBrain}: metadata, a
 * two-value config, and a tick entry point. File IO, loader events, platform
 * detection and diagnostics all come from Mycel.
 */
public final class InstantXpMod {
    public static final String ID = "instantxp";

    public static final ModMetadata META = ModMetadata.builder(ID, "InstantXP", "1.0.4")
            .author("MyCelium")
            .website("https://www.curseforge.com/minecraft/mc-mods/instant-xp-pickup")
            .updateUrl("https://wiki.mycelium-gg.org/updates/instantxp.json")
            .build();

    public static ConfigValue<Boolean> enabled;
    public static ConfigValue<Integer> radius;
    public static MycelConfig config;

    private InstantXpMod() {
    }

    /** Called once from each loader's entrypoint. */
    public static void init() {
        Mycel.initialize();
        Mycel.registerMod(META);

        ConfigBuilder builder = Mycel.config(META);
        enabled = builder.booleanValue("enabled", true,
                "Whether nearby XP orbs are picked up instantly.");
        radius = builder.intValue("radius", 8, 1, 64,
                "Collection radius in blocks around each player.");
        config = builder.build();
    }

    public static boolean isModEnabled() {
        return Mycel.isEnabled(ID) && enabled.get();
    }

    /** Reads the radius config defensively clamped to its documented range. */
    public static double effectiveRadius() {
        return Math.max(1, Math.min(64, radius.get()));
    }

    /**
     * Server-tick hook, called by each loader's native tick listener. Cheap
     * when disabled or when nobody can pick anything up (see
     * {@link InstantXpBrain}): a no-op guard plus one bounding-box query per
     * eligible player.
     */
    public static void tickServer(MinecraftServer server) {
        if (!isModEnabled()) {
            return;
        }
        if (server == null) {
            return;
        }
        InstantXpBrain.tickServer(server, effectiveRadius());
    }
}
