package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import iskallia.vault.init.ModEffects;
import iskallia.vault.entity.boss.TheVesselEntity;
import java.util.HashSet;
import java.util.Set;

@Mixin(value = TheVesselEntity.class, remap = false)
public class MixinGreedVesselEntity {
    @Inject(method = "canBeAffected" , at = @At("TAIL"), cancellable = true, remap = true)
    private void allowedVesselEffects(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if (effect.getEffect() == MobEffects.POISON ||
                effect.getEffect() == MobEffects.MOVEMENT_SLOWDOWN ||
                effect.getEffect() == MobEffects.WEAKNESS ||
                effect.getEffect() == ModEffects.SMITE ||
                effect.getEffect() == ModEffects.TOTEM_MOB_DAMAGE ||
                effect.getEffect() == ModEffects.BLEED ||
                effect.getEffect() == ModEffects.NOVA_DOT ||
                effect.getEffect() == ModEffects.CHILLED ||
                effect.getEffect() == xyz.iwolfking.woldsvaults.init.ModEffects.ECHOING ||
                effect.getEffect() == xyz.iwolfking.woldsvaults.init.ModEffects.REAVING)
        {
            cir.setReturnValue(true);
        }

        //if (woldsvaults$possibleVesselEffects.contains(effect.getEffect())) {
        //    cir.setReturnValue(true);
        //}
    }
}
