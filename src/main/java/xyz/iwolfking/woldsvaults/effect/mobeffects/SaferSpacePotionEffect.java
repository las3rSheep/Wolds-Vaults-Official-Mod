package xyz.iwolfking.woldsvaults.effect.mobeffects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SaferSpacePotionEffect extends MobEffect {
    public SaferSpacePotionEffect(MobEffectCategory mobEffectCategory, int i, ResourceLocation id) {
        super(mobEffectCategory, i);
        this.setRegistryName(id);
    }
}
