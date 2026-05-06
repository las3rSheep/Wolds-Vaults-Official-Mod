package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultUtils;
import iskallia.vault.snapshot.AttributeSnapshot;
import iskallia.vault.util.calc.GrantedEffectHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.effect.mobeffects.SaferSpacePotionEffect;
import xyz.iwolfking.woldsvaults.modifiers.vault.AntiImmunityModifier;

@Mixin(value = GrantedEffectHelper.class, remap = false)
public class MixinGrantedEffectHelper {

    @Inject(method = "canAvoidEffect", at = @At("TAIL"), cancellable = true)
    private static void handleAntiImmunity(MobEffect effect, LivingEntity entity, Random random, CallbackInfoReturnable<Boolean> cir) {
        if(entity instanceof Player player && cir.getReturnValue()) {
            if(!effect.isBeneficial()) {
                Vault vault = VaultUtils.getVault(player.getLevel()).orElse(null);
                if(vault != null) {
                    List<ResourceLocation> antiImmunityModifierList = vault.get(Vault.MODIFIERS).getModifiers().stream().filter(vaultModifier -> vaultModifier instanceof AntiImmunityModifier).map(vaultModifier -> ((AntiImmunityModifier) vaultModifier).properties().getId()).toList();

                    if(antiImmunityModifierList.contains(effect.getRegistryName())) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }

    /**
     * @author aida
     * @reason keep safer space visible
     */
    @Overwrite
    public static void applyEffects(LivingEntity entity, Map<MobEffect, Integer> effects) {
        effects.forEach(
                (effect, amplifier) -> {
                    if (amplifier >= 0) {
                        MobEffectInstance activeEffect = entity.getEffect(effect);
                        if (activeEffect == null
                                || activeEffect.getAmplifier() < amplifier
                                || activeEffect.getAmplifier() == amplifier && activeEffect.getDuration() <= 259) {
                            boolean beVisible = effect instanceof SaferSpacePotionEffect;
                            entity.addEffect(new MobEffectInstance(effect, 339, amplifier, true, beVisible, beVisible));
                        }
                    }
                }
        );
    }
}
