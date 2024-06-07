package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.gear.GearRollHelper;
import iskallia.vault.gear.VaultGearModifierHelper;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModGearAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(value = GearRollHelper.class, remap = false)
public class MixinGearRollHelper {
    @Shadow @Final public static Random rand;

    @Inject(method = "initializeGear(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)V", at = @At("TAIL"))
    private static void addLegendaryModifierToCraftedGear(ItemStack stack, Player player, CallbackInfo ci, @Local VaultGearData data, @Local float extraLegendaryChance) {
        if(data.equals(VaultGearData.empty())) {
            return;
        }
        if ((Boolean) data.getFirstValue(ModGearAttributes.CRAFTED_BY).isPresent() && rand.nextFloat() < ModConfigs.VAULT_GEAR_CRAFTING_CONFIG.getLegendaryModifierChance() + extraLegendaryChance) {
                  VaultGearModifierHelper.generateLegendaryModifier(stack, rand);
        }
    }
}
