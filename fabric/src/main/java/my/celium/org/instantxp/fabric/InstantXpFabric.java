package my.celium.org.instantxp.fabric;

import my.celium.org.instantxp.InstantXpMod;
import my.celium.org.instantxp.MissingMycel;
import my.celium.org.instantxp.XpGate;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;

/** Fabric entrypoint. Without Mycel the game still starts: InstantXP stays off and tells joining players. */
public final class InstantXpFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        if (!FabricLoader.getInstance().isModLoaded("mycel")) {
            MissingMycel.logOnce();
            ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> MissingMycel.tell(handler.getPlayer()));
            return;
        }
        XpGate.open();
        Setup.start();
    }

    /** Only loaded with Mycel installed. */
    private static final class Setup {
        static void start() {
            InstantXpMod.init();
            ServerTickEvents.END_SERVER_TICK.register(InstantXpMod::tickServer);
        }
    }
}
