package xyz.iwolfking.woldsvaults.effect.mobeffects;

import iskallia.vault.util.damage.DamageUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xyz.iwolfking.woldsvaults.api.util.WoldEtchingHelper;
import xyz.iwolfking.woldsvaults.events.WoldActiveFlags;
import xyz.iwolfking.woldsvaults.init.ModEffects;
import xyz.iwolfking.woldsvaults.init.ModEtchingGearAttributes;

public class EchoingPotionEffect extends MobEffect {
    public EchoingPotionEffect(MobEffectCategory pCategory, int pColor, ResourceLocation id) {
        super(pCategory, pColor);
        this.setRegistryName(id);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return (pDuration % 10) == 1;
    }

    @Override
    public void applyEffectTick(LivingEntity affected, int pAmplifier) {

        if(affected.getEffect(ModEffects.ECHOING) instanceof EchoingEffectInstance echo
        && !(echo.getAttacker() != null && WoldEtchingHelper.hasEtching(echo.getAttacker(), ModEtchingGearAttributes.REVERBERATION)))
            WoldActiveFlags.IS_ECHOING_ATTACKING.runIfNotSet(() -> {
                DamageUtil.shotgunAttack(affected, e -> e.hurt(echo.source, echo.damage));
            });
    }
}