package xyz.iwolfking.woldsvaults.mixins.itemborders.cyclopscore;

import com.anthonyhilyard.itemborders.ItemBorders;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.ItemStack;
import org.cyclops.cyclopscore.client.gui.RenderItemExtendedSlotCount;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "itemborders"),
                @Condition(type = Condition.Type.MOD, value = "cyclopscore")
        }
)
@Mixin(value = RenderItemExtendedSlotCount.class)
public abstract class MixinRenderItemExtendedSlotCount {

    @Inject(method = "renderGuiItemDecorations", at = @At("HEAD"))
    public void renderGuiItemDecorationsBorder(Font font, ItemStack stack, int x, int y, String text, CallbackInfo ci) {
        ItemBorders.renderBorder(null, stack, x, y);

    }
}
