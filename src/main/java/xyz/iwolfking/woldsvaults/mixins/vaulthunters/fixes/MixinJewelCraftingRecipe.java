package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.VaultMod;
import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.crafting.recipe.JewelCraftingRecipe;
import iskallia.vault.gear.data.VaultGearData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = JewelCraftingRecipe.class, remap = false)
public class MixinJewelCraftingRecipe {

    @Redirect(method = "createAttributeJewel", at = @At(value = "INVOKE", target = "Liskallia/vault/gear/data/VaultGearData;addModifier(Liskallia/vault/gear/attribute/VaultGearModifier$AffixType;Liskallia/vault/gear/attribute/VaultGearModifier;)Z", ordinal = 1))
    private static boolean createAttributeJewel(VaultGearData instance, VaultGearModifier.AffixType type, VaultGearModifier<?> modifier, @Local VaultGearAttribute<?> attribute) {
        if (attribute != null) {
            if (attribute.getRegistryName().equals(VaultMod.id("immortality"))) {
                return instance.addModifier(VaultGearModifier.AffixType.SUFFIX, new VaultGearModifier(attribute, 0.2F));
            }
        }
        return instance.addModifier(VaultGearModifier.AffixType.SUFFIX, new VaultGearModifier(attribute, true));
    }

}
