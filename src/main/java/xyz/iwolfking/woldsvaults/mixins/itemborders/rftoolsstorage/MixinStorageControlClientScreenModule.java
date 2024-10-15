package xyz.iwolfking.woldsvaults.mixins.itemborders.rftoolsstorage;

import com.anthonyhilyard.itemborders.ItemBorders;
import com.mojang.blaze3d.vertex.PoseStack;
import mcjty.rftoolsbase.api.screens.IClientScreenModule;
import mcjty.rftoolsstorage.modules.scanner.items.StorageControlClientScreenModule;
import mcjty.rftoolsstorage.modules.scanner.items.StorageControlScreenModule;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "itemborders"),
                @Condition(type = Condition.Type.MOD, value = "rftoolsstorage")
        }
)
@Mixin(value = StorageControlClientScreenModule.class, remap = false)
public abstract class MixinStorageControlClientScreenModule implements IClientScreenModule<StorageControlScreenModule.ModuleDataStacks> {
    /**
     * @author iwolfking
     * @reason Render Item Borders on RF Tools Storage Scanner slots
     */
    @Overwrite
    private void renderSlot(PoseStack matrixStack, MultiBufferSource buffer, int currenty, ItemStack stack, int x, int lightmapValue) {
        matrixStack.pushPose();
        matrixStack.translate((double) ((float) x + 8.0F), (double) ((float) currenty + 8.0F), 5.0);
        matrixStack.scale(16.0F, 16.0F, 16.0F);
        ItemRenderer itemRender = Minecraft.getInstance().getItemRenderer();
        BakedModel ibakedmodel = itemRender.getModel(stack, Minecraft.getInstance().level, (LivingEntity) null, 0);
        itemRender.render(stack, ItemTransforms.TransformType.GUI, false, matrixStack, buffer, lightmapValue, OverlayTexture.NO_OVERLAY, ibakedmodel);
        ItemBorders.renderBorder(matrixStack, stack, x, currenty);
        matrixStack.popPose();
    }
}
