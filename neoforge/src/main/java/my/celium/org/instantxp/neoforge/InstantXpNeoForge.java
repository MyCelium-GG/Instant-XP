package my.celium.org.instantxp.neoforge;

import my.celium.org.instantxp.InstantXpMod;
import my.celium.org.instantxp.MissingMycel;
import my.celium.org.instantxp.XpGate;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

/** NeoForge entrypoint. Without Mycel the game still starts: InstantXP stays off and tells joining players. */
@Mod("instantxp")
public final class InstantXpNeoForge {
    public InstantXpNeoForge(@SuppressWarnings("unused") IEventBus modBus,
            @SuppressWarnings("unused") ModContainer container) {
        if (!ModList.get().isLoaded("mycel")) {
            MissingMycel.logOnce();
            NeoForge.EVENT_BUS.addListener((PlayerEvent.PlayerLoggedInEvent event) -> {
                if (event.getEntity() instanceof ServerPlayer player) {
                    MissingMycel.tell(player);
                }
            });
            return;
        }
        XpGate.open();
        Setup.start();
    }

    /** Only loaded with Mycel installed. */
    private static final class Setup {
        static void start() {
            InstantXpMod.init();
            NeoForge.EVENT_BUS.addListener((ServerTickEvent.Post event) -> InstantXpMod.tickServer(event.getServer()));
        }
    }
}
