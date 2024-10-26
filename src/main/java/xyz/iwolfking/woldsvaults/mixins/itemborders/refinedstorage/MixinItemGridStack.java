package xyz.iwolfking.woldsvaults.mixins.itemborders.refinedstorage;

import com.anthonyhilyard.itemborders.ItemBorders;
import com.mojang.blaze3d.vertex.PoseStack;
import com.refinedmods.refinedstorage.screen.BaseScreen;
import com.refinedmods.refinedstorage.screen.grid.stack.IGridStack;
import com.refinedmods.refinedstorage.screen.grid.stack.ItemGridStack;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "itemborders"),
                @Condition(type = Condition.Type.MOD, value = "refinedstorage")
        }
)
@Mixin(value = ItemGridStack.class, remap = false)
public abstract class MixinItemGridStack implements IGridStack {

    @Shadow @Final private ItemStack stack;


    @Inject(method = "draw", at = @At("TAIL"))
    public void drawItemBorder(PoseStack poseStack, BaseScreen<?> screen, int x, int y, CallbackInfo ci) {
        ItemBorders.renderBorder(poseStack, stack, x, y);
    }
}
