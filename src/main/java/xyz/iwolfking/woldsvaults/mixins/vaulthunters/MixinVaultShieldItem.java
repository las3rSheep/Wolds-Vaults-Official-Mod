package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import cofh.ensorcellation.init.EnsorcEnchantments;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.gear.VaultShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = VaultShieldItem.class, remap = false)
public abstract class MixinVaultShieldItem   extends ShieldItem implements VaultGearItem {

    public MixinVaultShieldItem(Properties p_43089_) {
        super(p_43089_);
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
