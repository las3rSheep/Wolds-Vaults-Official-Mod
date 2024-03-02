package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.gear.item.VaultGearItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = VaultGearItem.class, remap = false)
public interface MixinVaultGearItem {

    /**
     * @author iwolfking
     * @reason Prevent anvil enchantments on vault gear
     */
    @Overwrite
    public default boolean shouldPreventAnvilCombination(ItemStack other) {
        return true;
    }
}
