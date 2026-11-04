package my.celium.org.instantxp;

import my.celium.org.stats.ModStats;

/**
 * Anonymous stats for the FastStats project {@code instant-xp}, sent through Mycel under its
 * consent: how many XP orbs get collected, how big the piles are, and the settings.
 */
public final class XpStats {
    private static final String TOKEN = "ca89a4a8a91369c9a45915964297403e";

    private static volatile ModStats stats;

    private XpStats() {
    }

    static void start() {
        stats = ModStats.builder(InstantXpMod.META, TOKEN)
                .bool("enabled", () -> InstantXpMod.enabled.get())
                .number("radius", () -> InstantXpMod.radius.get())
                .counters("pile_sizes")
                .errors("my.celium.org.instantxp.")
                .start();
    }

    /** Counts a feature use (sent as {@code usage}). */
    public static void count(String what) {
        ModStats current = stats;
        if (current != null) {
            current.count(what);
        }
    }

    /** Counts {@code key} in the counter map {@code id}. */
    public static void count(String id, String key) {
        ModStats current = stats;
        if (current != null && key != null) {
            current.count(id, key.startsWith("minecraft:") ? key.substring(10) : key);
        }
    }
}
