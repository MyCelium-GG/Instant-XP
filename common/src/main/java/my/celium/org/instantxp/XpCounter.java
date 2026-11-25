package my.celium.org.instantxp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * "+37 XP" above the hotbar while you pick up XP. Gains that come in quick succession add up, so
 * a pile shows one growing number instead of a flicker. Sent as a vanilla action-bar message, so
 * players without the mod see it too.
 */
public final class XpCounter {
    /** Gains within this many ticks of each other add up. */
    static final int WINDOW_TICKS = 40;
    static final int COLOR = 0x80FF20;

    private record Run(int total, int lastTick) {
    }

    private static final Map<UUID, Run> RUNS = new HashMap<>();

    private XpCounter() {
    }

    /** {@code player} just gained {@code points} XP (after Mending took its share). */
    public static void gained(ServerPlayer player, int points, int tick) {
        if (points <= 0 || !InstantXpMod.counterEnabled()) {
            return;
        }
        int total = add(player.getUUID(), points, tick);
        player.sendOverlayMessage(Component.literal(InstantXpMod.LANG.get(player, "counter", total))
                .withStyle(style -> style.withColor(COLOR)));
    }

    /** Adds to the running total for this player and returns it. */
    static int add(UUID player, int points, int tick) {
        Run run = RUNS.get(player);
        int total = run != null && tick - run.lastTick <= WINDOW_TICKS ? run.total + points : points;
        RUNS.put(player, new Run(total, tick));
        return total;
    }

    public static void forget(UUID player) {
        RUNS.remove(player);
    }
}
