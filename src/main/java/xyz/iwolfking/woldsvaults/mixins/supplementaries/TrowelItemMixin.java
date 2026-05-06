package xyz.iwolfking.woldsvaults.mixins.supplementaries;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.mehvahdjukaar.supplementaries.common.items.AbstractMobContainerItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.quark.content.tools.item.TrowelItem;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "supplementaries"),
                @Condition(type = Condition.Type.MOD, value = "quark")
        }
)
@Mixin(value = TrowelItem.class, remap = false)
public abstract class TrowelItemMixin {

    @Inject(method = "isValidTarget", at = @At("HEAD"), cancellable = true, remap = false)
    private static void preventFilledMobContainerDuplication(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isEmpty()) {
            return;
        }
        if (stack.getItem() instanceof AbstractMobContainerItem mobContainerItem && mobContainerItem.isFull(stack)) {
            cir.setReturnValue(false);
        }
    }
}