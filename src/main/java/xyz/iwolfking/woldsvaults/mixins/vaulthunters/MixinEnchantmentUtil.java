package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import cofh.ensorcellation.enchantment.*;
import cofh.lib.enchantment.EnchantmentCoFH;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.tool.PaxelItem;
import iskallia.vault.util.EnchantmentUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EnchantmentUtil.class, remap = false)
public abstract class MixinEnchantmentUtil {
    @Shadow
    private static boolean isBlockedProtectionEnchantment(Enchantment ench) {
        return false;
    }

    /**
     * @author iwolfking
     * @reason Block Ensorcellation enchants
     */
    @Overwrite
    public static boolean isEnchantmentBlocked(Enchantment ench, ItemStack stack) {
        if(ench instanceof EnchantmentCoFH && stack.equals(ItemStack.EMPTY)) {
            return true;
        }
        else {
            if(ench instanceof SoulboundEnchantment || ench instanceof AirAffinityEnchantment || ench instanceof VitalityEnchantment || ench instanceof XpBoostEnchantment || ench instanceof GourmandEnchantment || ench instanceof HunterEnchantment || ench instanceof QuickdrawEnchantment || ench instanceof TrueshotEnchantment || ench instanceof VolleyEnchantment || ench instanceof AnglerEnchantment) {
                return false;
            }
        }

        if (!isBlockedProtectionEnchantment(ench) && !(ench instanceof DamageEnchantment)) {
            if (!(ench instanceof SweepingEdgeEnchantment) && !(ench instanceof ArrowDamageEnchantment) && !(ench instanceof FireAspectEnchantment) && !(ench instanceof ArrowFireEnchantment) && !(ench instanceof MultiShotEnchantment) && !(ench instanceof ThornsEnchantment) && !(ench instanceof KnockbackEnchantment) && !(ench instanceof EnchantmentCoFH)) {
                return ench instanceof MendingEnchantment && (stack.getItem() instanceof VaultGearItem || stack.getItem() instanceof PaxelItem);
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
