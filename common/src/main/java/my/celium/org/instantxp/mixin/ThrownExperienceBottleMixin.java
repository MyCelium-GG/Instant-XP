package my.celium.org.instantxp.mixin;

import my.celium.org.instantxp.XpBottles;
import my.celium.org.instantxp.XpGate;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownExperienceBottle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/** A bottle filled by InstantXP gives back exactly what went in. */
@Mixin(ThrownExperienceBottle.class)
public abstract class ThrownExperienceBottleMixin {
    @ModifyArg(method = "onHit", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/entity/ExperienceOrb;awardWithDirection(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;I)V"),
            index = 3)
    private int instantxp$storedXp(int vanilla) {
        if (!XpGate.on()) {
            return vanilla;
        }
        return XpBottles.stored(((ThrowableItemProjectile) (Object) this).getItem(), vanilla);
    }
}
