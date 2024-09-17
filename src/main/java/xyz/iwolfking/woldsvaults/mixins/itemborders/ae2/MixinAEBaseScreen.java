package xyz.iwolfking.woldsvaults.mixins.itemborders.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.core.AELog;
import appeng.menu.AEBaseMenu;
import appeng.menu.slot.AppEngSlot;
import com.anthonyhilyard.itemborders.ItemBorders;
import com.mojang.blaze3d.vertex.PoseStack;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "itemborders")
        }
)
@Mixin(value = AEBaseScreen.class)
public abstract class MixinAEBaseScreen<T extends AEBaseMenu> extends AbstractContainerScreen<T> {
    @Shadow protected abstract void renderAppEngSlot(PoseStack poseStack, AppEngSlot s);

    public MixinAEBaseScreen(T p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    /**
     * @author iwolfking
     * @reason Add Item Borders rendering to AE2 terminals
     */
    @Overwrite
    public void renderSlot(PoseStack poseStack, Slot s) {
        if (s instanceof AppEngSlot appEngSlot) {
            try {
                renderAppEngSlot(poseStack, appEngSlot);
                ItemBorders.renderBorder(poseStack, s);
            } catch (Exception err) {
                AELog.warn("[AppEng] AE prevented crash while drawing slot: " + err);
            }
        } else {
            super.renderSlot(poseStack, s);
            ItemBorders.renderBorder(poseStack, s);
        }
    }



}
