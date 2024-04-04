package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.dynamodel.DynamicModelItem;
import iskallia.vault.gear.item.IdentifiableItem;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.gear.tooltip.VaultGearTooltipItem;
import iskallia.vault.item.IAnvilPreventCombination;
import iskallia.vault.item.IConditionalDamageable;
import iskallia.vault.item.gear.DataTransferItem;
import iskallia.vault.item.gear.RecyclableItem;
import iskallia.vault.item.gear.VaultLevelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = VaultGearItem.class, remap = false)
public interface MixinVaultGearItem extends IForgeItem, VaultGearTooltipItem, DataTransferItem, VaultLevelItem, RecyclableItem, DynamicModelItem, IConditionalDamageable, IAnvilPreventCombination, IdentifiableItem {

    @Override
    public default boolean shouldPreventAnvilCombination(ItemStack other) {
        return true;
    }
}
