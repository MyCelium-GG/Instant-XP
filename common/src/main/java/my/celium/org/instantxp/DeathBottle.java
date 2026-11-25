package my.celium.org.instantxp;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gamerules.GameRules;

/**
 * When a player dies, part of their XP is left behind in a Bottle o' Enchanting (the same exact
 * bottles as {@link XpBottles}) instead of vanilla's few orbs, which give back at most 100 points
 * no matter how much was lost. Pick it up and throw it to get that XP back.
 */
public final class DeathBottle {
    private DeathBottle() {
    }

    /** From LivingEntity.dropExperience: {@code true} when the bottle replaced vanilla's orbs. */
    public static boolean drop(ServerLevel level, ServerPlayer player) {
        if (!InstantXpMod.deathBottleEnabled() || player.isSpectator()
                || level.getGameRules().get(GameRules.KEEP_INVENTORY)) {
            return false;
        }
        int amount = keep(XpBottles.totalXp(player), InstantXpMod.deathBottlePercent.get());
        // Vanilla's orbs: 7 per level, at most 100. Never give less than that.
        if (amount <= Math.min(player.experienceLevel * 7, 100)) {
            return false;
        }
        player.spawnAtLocation(level, XpBottles.bottle(amount, player));
        XpStats.count("death_bottle");
        return true;
    }

    /** The part of {@code total} XP points that goes into the bottle. */
    static int keep(int total, int percent) {
        return (int) ((long) Math.max(0, total) * Math.max(0, Math.min(100, percent)) / 100);
    }
}
