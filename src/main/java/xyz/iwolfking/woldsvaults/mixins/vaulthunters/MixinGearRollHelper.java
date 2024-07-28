package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.gear.GearRollHelper;
import iskallia.vault.gear.VaultGearModifierHelper;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.skill.base.Skill;
import iskallia.vault.skill.tree.ExpertiseTree;
import iskallia.vault.world.data.PlayerExpertisesData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.expertises.CraftsmanExpertise;

import java.util.Random;

@Mixin(value = GearRollHelper.class, remap = false)
public class MixinGearRollHelper {
    @Shadow @Final public static Random rand;

    @Inject(method = "initializeGear(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;)V", at = @At("TAIL"))
    private static void addLegendaryModifierToCraftedGear(ItemStack stack, Player player, CallbackInfo ci, @Local VaultGearData data, @Local float extraLegendaryChance) {

        if(data.equals(VaultGearData.empty())) {
            return;
        }

        if ((Boolean) data.getFirstValue(ModGearAttributes.CRAFTED_BY).isPresent() && rand.nextFloat() < ModConfigs.VAULT_GEAR_CRAFTING_CONFIG.getLegendaryModifierChance() + extraLegendaryChance + 100) {
            ExpertiseTree expertises = PlayerExpertisesData.get((ServerLevel) player.getLevel()).getExpertises(player);
            int craftsmanLevel = 0;

            for (CraftsmanExpertise craftsmanExpertise : expertises.getAll(CraftsmanExpertise.class, Skill::isUnlocked)) {
                craftsmanLevel = craftsmanExpertise.getCraftsmanLevel();
            }
            if(craftsmanLevel > 0) {
                VaultGearModifierHelper.generateLegendaryModifier(stack, rand);
            }

        }

        if(data.getFirstValue(ModGearAttributes.GEAR_ROLL_TYPE_POOL).isPresent()) {
            if(data.getFirstValue(ModGearAttributes.GEAR_ROLL_TYPE_POOL).get().equals("jewel_crafted") && rand.nextFloat() < 0.01F) {
                VaultGearModifierHelper.generateLegendaryModifier(stack, rand);
            }
        }
    }
}
