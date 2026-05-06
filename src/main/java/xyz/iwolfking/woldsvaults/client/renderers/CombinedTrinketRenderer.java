package xyz.iwolfking.woldsvaults.client.renderers;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import iskallia.vault.item.gear.TrinketItem;
import iskallia.vault.item.render.core.SpecialItemRenderer;
import iskallia.vault.gear.trinket.TrinketEffect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.items.CombinedTrinketItem;

import java.util.Collections;
import java.util.List;

public class CombinedTrinketRenderer extends SpecialItemRenderer {

    public static final CombinedTrinketRenderer INSTANCE = new CombinedTrinketRenderer();

    @Override
    public void renderByItem(
            @NotNull ItemStack stack,
            @NotNull ItemTransforms.TransformType transformType,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource buffer,
            int light,
            int overlay
    ) {
        List<TrinketEffect<?>> effects = CombinedTrinketItem.getTrinkets(stack);
        if(effects == null || effects.isEmpty()) {
            ResourceLocation tex = ResourceLocation.fromNamespaceAndPath(
                    "woldsvaults",
                    "textures/item/combined_trinket.png"
            );
            VertexConsumer vc = buffer.getBuffer(RenderType.entityCutoutNoCull(tex));

            poseStack.pushPose();

            if (transformType == ItemTransforms.TransformType.GUI) {
                Lighting.setupForFlatItems();
            } else {
                Lighting.setupFor3DItems();
            }

            int guiLight = (transformType == ItemTransforms.TransformType.GUI)
                    ? 0xF000F0
                    : light;

            PoseStack.Pose pose = poseStack.last();
            Matrix4f mat = pose.pose();
            Matrix3f normal = pose.normal();

            vc.vertex(mat, 0, 1, 0).color(255,255,255,255).uv(0,0).overlayCoords(overlay).uv2(guiLight).normal(normal,0,0,1).endVertex();
            vc.vertex(mat, 1, 1, 0).color(255,255,255,255).uv(1,0).overlayCoords(overlay).uv2(guiLight).normal(normal,0,0,1).endVertex();
            vc.vertex(mat, 1, 0, 0).color(255,255,255,255).uv(1,1).overlayCoords(overlay).uv2(guiLight).normal(normal,0,0,1).endVertex();
            vc.vertex(mat, 0, 0, 0).color(255,255,255,255).uv(0,1).overlayCoords(overlay).uv2(guiLight).normal(normal,0,0,1).endVertex();

            poseStack.popPose();
            return;
        }

        RenderSystem.enablePolygonOffset();
        RenderSystem.polygonOffset(-1.0F, -1.0F);
        for (int i = effects.size() - 1; i >= 0; i--) {

            TrinketEffect<?> effect = effects.get(i);

            poseStack.pushPose();

            ItemStack fake = TrinketItem.createBaseTrinket(effect);

            BakedModel model = Minecraft.getInstance()
                    .getItemRenderer()
                    .getModel(fake, null, null, 0);

            Minecraft.getInstance()
                    .getItemRenderer()
                    .renderModelLists(
                            model,
                            fake,
                            light,
                            overlay,
                            poseStack,
                            buffer.getBuffer(RenderType.translucent())
                    );
            poseStack.popPose();
        }
        RenderSystem.disablePolygonOffset();
    }
}