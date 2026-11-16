package my.celium.org.instantxp.mixin;

import my.celium.org.instantxp.XpBottles;
import my.celium.org.instantxp.XpGate;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Sneak + use an empty glass bottle with nothing to fill: bottle some XP (only when vanilla did nothing). */
@Mixin(BottleItem.class)
public abstract class BottleItemMixin {
    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
    private void instantxp$bottleXp(Level level, Player player, InteractionHand hand,
            CallbackInfoReturnable<InteractionResult> cir) {
        if (!XpGate.on() || cir.getReturnValue() != InteractionResult.PASS || level.isClientSide()) {
            return;
        }
        InteractionResult filled = XpBottles.fill(player, hand);
        if (filled != null) {
            cir.setReturnValue(filled);
        }
    }
}
