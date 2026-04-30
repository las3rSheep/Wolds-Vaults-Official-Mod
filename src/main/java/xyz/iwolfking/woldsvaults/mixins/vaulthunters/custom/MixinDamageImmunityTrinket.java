package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultUtils;
import iskallia.vault.gear.trinket.effects.DamageImmunityTrinket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.modifiers.vault.AntiImmunityModifier;

import java.util.List;

@Mixin(value = DamageImmunityTrinket.class, remap = false)
public class MixinDamageImmunityTrinket {
    @Inject(method = "onPotionEffect", at = @At("HEAD"), cancellable = true)
    private static void handleAntiImmunity(PotionEvent.PotionApplicableEvent event, CallbackInfo ci) {
        if(event.getEntityLiving() instanceof Player player) {
            if(!event.getPotionEffect().getEffect().isBeneficial()) {
                Vault vault = VaultUtils.getVault(player.getLevel()).orElse(null);
                if(vault != null) {
                    List<ResourceLocation> antiImmunityModifierList = vault.get(Vault.MODIFIERS).getModifiers().stream().filter(vaultModifier -> vaultModifier instanceof AntiImmunityModifier).map(vaultModifier -> ((AntiImmunityModifier) vaultModifier).properties().getId()).toList();

                    if(antiImmunityModifierList.contains(event.getPotionEffect().getEffect().getRegistryName())) {
                        event.setResult(Event.Result.ALLOW);
                        ci.cancel();
                    }
                }
            }
        }
    }
}
