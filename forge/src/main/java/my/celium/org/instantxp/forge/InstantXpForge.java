package my.celium.org.instantxp.forge;

import my.celium.org.instantxp.InstantXpMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Forge entrypoint: forwards every server tick into the common XP collector.
 */
@Mod(InstantXpMod.ID)
public final class InstantXpForge {
    public InstantXpForge(@SuppressWarnings("unused") FMLJavaModLoadingContext context) {
        InstantXpMod.init();
        TickEvent.ServerTickEvent.Post.BUS.addListener(this::onServerTick);
    }

    private void onServerTick(TickEvent.ServerTickEvent.Post event) {
        InstantXpMod.tickServer(event.server());
    }
}
