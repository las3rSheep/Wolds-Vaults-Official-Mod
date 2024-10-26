package xyz.iwolfking.woldsvaults.mixins.cofhcore;

import cofh.lib.enchantment.EnchantmentCoFH;
import iskallia.vault.util.EnchantmentUtil;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "cofhcore")
        }
)
@Mixin(value = EnchantmentCoFH.class, remap = false)
public class MixinCofhEnchantment extends Enchantment{

    @Shadow protected boolean enable;
    @Shadow protected boolean allowVillagerTrade;

    @Shadow protected boolean allowOnBooks;

    protected MixinCofhEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Inject(
            method = {"canApplyAtEnchantingTable"},
            at = {@At("HEAD")},
            cancellable = true,
            remap = false
    )
    private void canGetAtEnchantingTable(ItemStack stack, CallbackInfoReturnable<Boolean> ci) {
        Enchantment thisEnchantment = (EnchantmentCoFH)(Object)this;
        if (EnchantmentUtil.isEnchantmentBlocked(thisEnchantment, stack)) {
            ci.setReturnValue(false);
        }

    }

    /**
     * @author iwolfking
     * @reason Always disallow trading for Ensorcellation enchants
     */
    @Overwrite
    public boolean isTradeable() {
        return false;
    }

    /**
     * @author iwolfking
     * @reason Always disallow books for Ensorcellation enchants
     */
    @Overwrite
    public boolean isAllowedOnBooks() {
        return false;
    }
}
