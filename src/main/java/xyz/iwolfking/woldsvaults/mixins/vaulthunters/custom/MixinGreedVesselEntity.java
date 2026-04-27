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

    @Unique
    private static final Set<MobEffect> woldsvaults$possibleVesselEffects = new HashSet<>();
    static {
        woldsvaults$possibleVesselEffects.add(MobEffects.POISON);
        woldsvaults$possibleVesselEffects.add(MobEffects.MOVEMENT_SLOWDOWN);
        woldsvaults$possibleVesselEffects.add(MobEffects.WEAKNESS);
        woldsvaults$possibleVesselEffects.add(ModEffects.SMITE);
        woldsvaults$possibleVesselEffects.add(ModEffects.TOTEM_MOB_DAMAGE);
        woldsvaults$possibleVesselEffects.add(ModEffects.BLEED);
        woldsvaults$possibleVesselEffects.add(ModEffects.NOVA_DOT);
        woldsvaults$possibleVesselEffects.add(ModEffects.CHILLED);
        woldsvaults$possibleVesselEffects.add(xyz.iwolfking.woldsvaults.init.ModEffects.ECHOING);
        woldsvaults$possibleVesselEffects.add(xyz.iwolfking.woldsvaults.init.ModEffects.REAVING);
    }

    @Inject(method = "canBeAffected" , at = @At("TAIL"), cancellable = true, remap = true)
    private void allowedVesselEffects(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        if (woldsvaults$possibleVesselEffects.contains(effect.getEffect())) {
            cir.setReturnValue(true);
        }
    }
}
