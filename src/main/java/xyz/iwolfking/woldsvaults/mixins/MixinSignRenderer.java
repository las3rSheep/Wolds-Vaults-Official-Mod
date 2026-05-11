package xyz.iwolfking.woldsvaults.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import net.minecraft.world.level.block.state.properties.WoodType;

@Mixin(SignRenderer.class)
public class MixinSignRenderer {

    @WrapOperation(
        method = "render(Lnet/minecraft/world/level/block/entity/SignBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/Sheets;getSignMaterial(Lnet/minecraft/world/level/block/state/properties/WoodType;)Lnet/minecraft/client/resources/model/Material;"
        )
    )
    private Material preventBuildscapeCrash(WoodType pWoodType, Operation<Material> original) {
        if (original.call(pWoodType) == null) {
            return Sheets.getSignMaterial(WoodType.OAK);
        }

        return original.call(pWoodType);
    }
}