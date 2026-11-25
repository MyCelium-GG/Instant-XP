package my.celium.org.instantxp.mixin;

import my.celium.org.instantxp.DeathBottle;
import my.celium.org.instantxp.XpGate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** A dying player leaves a bottle with part of their XP instead of a few orbs. */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void instantxp$deathBottle(ServerLevel level, Entity killer, CallbackInfo ci) {
        if (XpGate.on() && (Object) this instanceof ServerPlayer player && DeathBottle.drop(level, player)) {
            ci.cancel();
        }
    }
}
