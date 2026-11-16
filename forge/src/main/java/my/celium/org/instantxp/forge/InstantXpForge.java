package my.celium.org.instantxp.forge;

import my.celium.org.instantxp.InstantXpMod;
import my.celium.org.instantxp.MissingMycel;
import my.celium.org.instantxp.XpGate;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/** Forge entrypoint. Without Mycel the game still starts: InstantXP stays off and tells joining players. */
@Mod("instantxp")
public final class InstantXpForge {
    public InstantXpForge(@SuppressWarnings("unused") FMLJavaModLoadingContext context) {
        if (!ModList.isLoaded("mycel")) {
            MissingMycel.logOnce();
            PlayerEvent.PlayerLoggedInEvent.BUS.addListener(event -> {
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
            TickEvent.ServerTickEvent.Post.BUS.addListener(event -> InstantXpMod.tickServer(event.server()));
        }
    }
}
