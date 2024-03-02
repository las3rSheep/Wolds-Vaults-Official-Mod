package xyz.iwolfking.woldsvaults.mixins;


import cofh.ensorcellation.init.EnsorcEnchantments;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.BasicItem;
import iskallia.vault.item.gear.FocusItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = FocusItem.class, remap = false)
public abstract class MixinVaultFocus extends BasicItem implements VaultGearItem {
    public MixinVaultFocus(ResourceLocation id) {
        super(id);
    }


    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if(enchantment.equals(EnsorcEnchantments.SOULBOUND.get())) {
            return true;
        }
        else {
            return super.canApplyAtEnchantingTable(stack, enchantment);
        }

    }
}
