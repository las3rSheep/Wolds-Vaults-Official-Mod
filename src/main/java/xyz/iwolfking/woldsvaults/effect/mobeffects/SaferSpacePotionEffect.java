package xyz.iwolfking.woldsvaults.effect.mobeffects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SaferSpacePotionEffect extends MobEffect {
    public SaferSpacePotionEffect(MobEffectCategory mobEffectCategory, int color, ResourceLocation id) {
        super(mobEffectCategory, color);
        this.setRegistryName(id);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
//        WoldsVaults.LOGGER.debug(pLivingEntity.level.isClientSide ? "client {}" : "server {}", pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) { return pDuration % 20 == 0; }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
