package my.celium.org.instantxp;

import java.util.List;

import my.celium.org.logging.MycelLog;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/**
 * The instant-pickup brain: no loader APIs, no events - pure collection logic
 * plus a per-tick entry point.
 *
 * <p>Why call {@code playerTouch} instead of granting XP directly? That one
 * call is vanilla's whole pickup path: Mending repairs, the orb-take
 * animation, statistics and the pickup sound. Reimplementing any of it would
 * drift from vanilla with every Minecraft update, so the mod only removes the
 * <em>walking</em>: orbs within {@code radius} blocks are touched as if the
 * player had stepped on them.
 *
 * <p>Vanilla normally paces absorption at one orb per two ticks per player
 * ({@code Player.takeXpDelay}), which is exactly why big piles take ages.
 * This method clears that cooldown before every touch, so the whole pile is
 * absorbed in a single tick instead of draining over seconds.
 */
public final class InstantXpBrain {
    private InstantXpBrain() {
    }

    /** One server tick of instant collection across every online player. */
    public static void tickServer(MinecraftServer server, double radius) {
        if (server == null || radius < 1) {
            return;
        }
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            try {
                collectForPlayer(player, radius);
            } catch (RuntimeException e) {
                // One bad player state must never break the tick for everyone else.
                MycelLog.warnMod(InstantXpMod.ID, "Skipping XP collection for a player: {}", e.toString());
            }
        }
    }

    /**
     * Touches every XP orb near {@code player} through the vanilla pickup
     * path, clearing vanilla's per-player pacing cooldown before each touch
     * so even large piles are absorbed in a single tick.
     *
     * @param player the collecting player (dead, removed and spectator players collect nothing)
     * @param radius collection radius in blocks
     * @return how many orbs were touched
     */
    public static int collectForPlayer(ServerPlayer player, double radius) {
        if (player == null || player.isRemoved() || !player.isAlive() || player.isSpectator()) {
            return 0;
        }
        if (radius < 1) {
            return 0;
        }
        AABB box = player.getBoundingBox().inflate(radius);
        List<ExperienceOrb> orbs =
                player.level().getEntitiesOfClass(ExperienceOrb.class, box, orb -> !orb.isRemoved());
        int touched = 0;
        for (ExperienceOrb orb : orbs) {
            if (orb.isRemoved()) {
                continue;
            }
            // Drop vanilla's two-tick pacing: without this, each touch sets
            // takeXpDelay = 2 and a large pile would still take ages.
            player.takeXpDelay = 0;
            orb.playerTouch(player);
            touched++;
        }
        return touched;
    }

    /**
     * Pure distance check backing the radius behaviour. Headless-testable: no
     * registries, no world, just vectors.
     */
    public static boolean withinRadius(Vec3 playerPos, Vec3 orbPos, double radius) {
        if (playerPos == null || orbPos == null || radius < 0) {
            return false;
        }
        return playerPos.distanceToSqr(orbPos) <= radius * radius;
    }
}
