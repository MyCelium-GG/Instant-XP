package my.celium.org.instantxp.fabric;

import my.celium.org.instantxp.InstantXpMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

/** Fabric entrypoint: forwards every server tick into the common XP collector. */
public final class InstantXpFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        InstantXpMod.init();
        ServerTickEvents.END_SERVER_TICK.register(InstantXpMod::tickServer);
    }
}
