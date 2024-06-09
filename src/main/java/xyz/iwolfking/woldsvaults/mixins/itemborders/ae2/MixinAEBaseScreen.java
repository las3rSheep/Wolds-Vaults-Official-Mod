package xyz.iwolfking.woldsvaults.mixins.itemborders.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.menu.AEBaseMenu;
import com.anthonyhilyard.itemborders.ItemBorders;
import com.mojang.blaze3d.vertex.PoseStack;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "itemborders")
        }
)
@Mixin(value = AEBaseScreen.class)
public abstract class MixinAEBaseScreen<T extends AEBaseMenu> extends AbstractContainerScreen<T> {

    public MixinAEBaseScreen(T p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }
    @Inject(method = "renderSlot(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/inventory/Slot;)V", at = @At("HEAD"))
    private void renderSlotBorder(PoseStack poseStack, Slot s, CallbackInfo ci) {
        ItemBorders.renderBorder(poseStack, s);

    }
}
