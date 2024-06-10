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
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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

    /**
     * @author iwolfking
     * @reason Add Item Borders rendering to AE2 terminals
     */
    @Overwrite
    public void renderSlot(PoseStack poseStack, Slot s) {
        if (s instanceof AppEngSlot appEngSlot) {
            try {
                renderAppEngSlot(poseStack, appEngSlot);
            } catch (Exception err) {
                AELog.warn("[AppEng] AE prevented crash while drawing slot: " + err);
            }
        } else {
            super.renderSlot(poseStack, s);
            ItemBorders.renderBorder(poseStack, s);
        }
    }


    /**
     * @author iwolfking
     * @reason Try to display item border
     */
    @Overwrite(remap = false)
    private void renderAppEngSlot(PoseStack poseStack, AppEngSlot s) {
        ItemStack is = s.getItem();
        if ((s.renderIconWithItem() || is.isEmpty()) && s.isSlotEnabled() && s.getIcon() != null) {
            s.getIcon().getBlitter().dest(s.x, s.y).opacity(s.getOpacityOfIcon()).blit(poseStack, this.getBlitOffset());
        }

        if (!s.isValid()) {
            fill(poseStack, s.x, s.y, 16 + s.x, 16 + s.y, 1728013926);
        }
        super.renderSlot(poseStack, s);
        ItemBorders.renderBorder(poseStack, s);
    }

    /**
     * @author iwolfking
     * @reason Attempt to add border support to AE2
     */
    @Overwrite(remap = false)
    public void drawItem(int x, int y, ItemStack is) {
        this.itemRenderer.blitOffset = 100.0F;
        this.itemRenderer.renderAndDecorateItem(is, x, y);
        ItemBorders.renderBorder(null, is, x, y);
        this.itemRenderer.blitOffset = 0.0F;
    }
}
