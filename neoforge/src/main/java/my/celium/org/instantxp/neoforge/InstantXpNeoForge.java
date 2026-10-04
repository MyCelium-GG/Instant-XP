package my.celium.org.instantxp.neoforge;

import my.celium.org.instantxp.InstantXpMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

/**
 * NeoForge entrypoint: forwards every server tick into the common XP collector.
 */
@Mod(InstantXpMod.ID)
public final class InstantXpNeoForge {
    public InstantXpNeoForge(@SuppressWarnings("unused") IEventBus modBus,
            @SuppressWarnings("unused") ModContainer container) {
        InstantXpMod.init();
        NeoForge.EVENT_BUS.addListener(this::onServerTick);
    }

    private void onServerTick(ServerTickEvent.Post event) {
        InstantXpMod.tickServer(event.getServer());
    }
}
