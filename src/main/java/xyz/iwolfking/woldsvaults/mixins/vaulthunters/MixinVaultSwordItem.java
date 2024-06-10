package xyz.iwolfking.woldsvaults.mixins.vaulthunters;


import cofh.ensorcellation.init.EnsorcEnchantments;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.gear.VaultSwordItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(VaultSwordItem.class)
public abstract class MixinVaultSwordItem extends SwordItem implements VaultGearItem, DyeableLeatherItem {
    public MixinVaultSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if(enchantment.equals(EnsorcEnchantments.CURSE_MERCY.get())) {
            return true;
        }
        else {
            return super.canApplyAtEnchantingTable(stack, enchantment);
        }
    }
}
