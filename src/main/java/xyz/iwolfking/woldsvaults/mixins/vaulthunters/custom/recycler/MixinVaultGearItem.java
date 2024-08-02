package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom.recycler;

import iskallia.vault.config.VaultRecyclerConfig;
import iskallia.vault.config.entry.ChanceItemStackEntry;
import iskallia.vault.config.gear.VaultGearTierConfig;
import iskallia.vault.dynamodel.DynamicModelItem;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.IdentifiableItem;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.gear.tooltip.VaultGearTooltipItem;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModItems;
import iskallia.vault.item.IAnvilPreventCombination;
import iskallia.vault.item.IConditionalDamageable;
import iskallia.vault.item.gear.DataTransferItem;
import iskallia.vault.item.gear.RecyclableItem;
import iskallia.vault.item.gear.VaultLevelItem;
import iskallia.vault.item.modification.ReforgeTagModificationFocus;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = VaultGearItem.class, remap = false)
public interface MixinVaultGearItem extends IForgeItem, VaultGearTooltipItem, DataTransferItem, VaultLevelItem, RecyclableItem, DynamicModelItem, IConditionalDamageable, IAnvilPreventCombination, IdentifiableItem {

    @Override
    public default boolean shouldPreventAnvilCombination(ItemStack other) {
        return true;
    }

    /**
     * @author iwolfking
     * @reason Give specific Faceted Focus when a legendary modifier is present
     */
    @Overwrite
    default VaultRecyclerConfig.RecyclerOutput getOutput(ItemStack input) {
        VaultGearData data = VaultGearData.read(input);
        VaultGearModifier<?> legMod = null;
        for (VaultGearModifier<?> mod : data.getAllModifierAffixes()) {
            if (mod.getCategory().equals(VaultGearModifier.AffixCategory.LEGENDARY)) {
                legMod = mod;
            }
        }



        if(legMod != null) {
            ItemStack focus = new ItemStack(ModItems.FACETED_FOCUS, 1);
            if(VaultGearTierConfig.getConfig(input).isPresent()) {
                VaultGearTierConfig tierConfig = VaultGearTierConfig.getConfig(input).get();
                VaultGearTierConfig.ModifierTierGroup group = tierConfig.getTierGroup(legMod.getModifierIdentifier());
                if(group != null) {
                    if(!group.getTags().isEmpty()) {
                        ReforgeTagModificationFocus.setModifierTag(focus, group.getTags().get(0));
                        return new VaultRecyclerConfig.RecyclerOutput(new ChanceItemStackEntry(new ItemStack(ModItems.VAULT_SCRAP), 4, 8, 1.0F), new ChanceItemStackEntry(new ItemStack(Items.NETHERITE_SCRAP), 1, 3, 0.2F), new ChanceItemStackEntry(focus, 1, 1, 1.0F));
                    }
                    else {
                        return new VaultRecyclerConfig.RecyclerOutput(new ChanceItemStackEntry(new ItemStack(ModItems.VAULT_SCRAP), 4, 8, 1.0F), new ChanceItemStackEntry(new ItemStack(Items.NETHERITE_SCRAP), 1, 3, 0.2F), new ChanceItemStackEntry(new ItemStack(ModItems.FACETED_FOCUS), 1, 1, 1.0F));
                    }
                }

            }

        }
        return ModConfigs.VAULT_RECYCLER.getGearRecyclingOutput();
    }
}
