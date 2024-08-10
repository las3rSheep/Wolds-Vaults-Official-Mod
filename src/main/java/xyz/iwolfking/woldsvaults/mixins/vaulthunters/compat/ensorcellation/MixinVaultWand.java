package xyz.iwolfking.woldsvaults.mixins.vaulthunters.compat.ensorcellation;


import cofh.ensorcellation.init.EnsorcEnchantments;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.BasicItem;
import iskallia.vault.item.gear.WandItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = WandItem.class, remap = false)
public abstract class MixinVaultWand extends BasicItem implements VaultGearItem {
    public MixinVaultWand(ResourceLocation id) {
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
