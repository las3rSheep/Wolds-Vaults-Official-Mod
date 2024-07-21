package xyz.iwolfking.woldsvaults.mixins;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Iterator;
import java.util.Map;

@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {

    @Unique
    private static Map<Enchantment, Integer> MAX_ENCHANTMENT_LEVEL_VALUE_MAP = Map.of(Enchantments.BLOCK_FORTUNE, 5, Enchantments.BLOCK_EFFICIENCY, 7);


    @Shadow
    public static int getItemEnchantmentLevel(Enchantment p_44844_, ItemStack p_44845_) {
        return 0;
    }

    /**
     * @author iwolfking
     * @reason Return max values for certain enchantments
     */
    @Overwrite
    public static int getEnchantmentLevel(Enchantment enchantment, LivingEntity entity) {
        Iterable<ItemStack> iterable = enchantment.getSlotItems(entity).values();
        if (iterable == null) {
            return 0;
        } else {
            int i = 0;
            Iterator var4 = iterable.iterator();

            while (var4.hasNext()) {
                ItemStack itemstack = (ItemStack) var4.next();
                int j = getItemEnchantmentLevel(enchantment, itemstack);
                if (j > i) {
                    i = j;
                }
            }
            if(MAX_ENCHANTMENT_LEVEL_VALUE_MAP.containsKey(enchantment)) {
                int maxLevel = MAX_ENCHANTMENT_LEVEL_VALUE_MAP.get(enchantment);
                if(i > maxLevel) {
                    return maxLevel;
                }
            }
            return i;
        }
    }
}
