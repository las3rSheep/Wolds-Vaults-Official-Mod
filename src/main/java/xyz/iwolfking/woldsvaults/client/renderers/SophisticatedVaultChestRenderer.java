package xyz.iwolfking.woldsvaults.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import iskallia.vault.block.model.VaultChestModel;
import iskallia.vault.block.render.VaultChestRenderer;
import iskallia.vault.init.ModBlocks;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.p3pp3rf1y.sophisticatedstorage.block.ChestBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageWrapper;
import net.p3pp3rf1y.sophisticatedstorage.client.ClientEventHandler;
import net.p3pp3rf1y.sophisticatedstorage.client.StorageTextureManager;
import net.p3pp3rf1y.sophisticatedstorage.client.render.DisplayItemRenderer;
import net.p3pp3rf1y.sophisticatedstorage.client.render.LockRenderer;
import xyz.iwolfking.woldsvaults.blocks.tiles.SophisticatedVaultChestEntity;
import xyz.iwolfking.woldsvaults.mixins.VaultChestRendererAccessor;

import java.util.Map;

public class SophisticatedVaultChestRenderer implements BlockEntityRenderer<SophisticatedVaultChestEntity> {

    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private final ModelPart lidPart;
    private final ModelPart bottomPart;
    private final ModelPart lockPart;
    private final DisplayItemRenderer displayItemRenderer = new DisplayItemRenderer(0.4378125, 0.581875);




    public SophisticatedVaultChestRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart modelpart = context.bakeLayer(ClientEventHandler.CHEST_LAYER);
        this.bottomPart = modelpart.getChild("bottom");
        this.lidPart = modelpart.getChild("lid");
        this.lockPart = modelpart.getChild("lock");
    }


    @Override
    public void render(SophisticatedVaultChestEntity sophisticatedVaultChestEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

        Block block = convertSophisticatedToVaultBlock(convertSophisticatedToVaultBlock(sophisticatedVaultChestEntity.getBlockState().getBlock()));

        VaultChestModel model = (VaultChestModel) VaultChestRendererAccessor.getMapOfModels().get(block);
        if (model != null) {
            this.customRender(model, sophisticatedVaultChestEntity, v, poseStack, multiBufferSource, i, i1);
        } else {
            render(sophisticatedVaultChestEntity, v, poseStack, multiBufferSource, i, i1);
        }
    }

    public void customRender(VaultChestModel model,  SophisticatedVaultChestEntity chestEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockstate = chestEntity.getBlockState();
        StorageTextureManager matManager = StorageTextureManager.INSTANCE;
        Map<StorageTextureManager.ChestMaterial, Material> chestMaterials = matManager.getWoodChestMaterials(WoodType.ACACIA);
            poseStack.pushPose();
            Direction facing = (Direction)blockstate.getValue(ChestBlock.FACING);
            float f = facing.toYRot();
            poseStack.translate(0.5, 0.5, 0.5);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-f));
            poseStack.translate(-0.5, -0.5, -0.5);
            float lidAngle = chestEntity.getOpenNess(partialTick);
            lidAngle = 1.0F - lidAngle;
            lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
            StorageWrapper storageWrapper = chestEntity.getStorageWrapper();

            VertexConsumer vertexconsumer;


            Material tierMaterial = this.getTierMaterial(blockstate.getBlock());
            vertexconsumer = tierMaterial.buffer(bufferSource, RenderType::entityCutout);
            //this.renderBottomAndLid(poseStack, vertexconsumer, lidAngle, packedLight, packedOverlay);
//            if (storageWrapper.getRenderInfo().getItemDisplayRenderInfo().getDisplayItem().isEmpty()) {
//                this.renderLock(poseStack, vertexconsumer, lidAngle, packedLight, packedOverlay);
//            }
            model.setLidAngle(lidAngle);

            model.renderToBuffer(poseStack, vertexconsumer, packedLight, packedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
            if (chestEntity.isPacked()) {
                VertexConsumer consumer = ((Material)chestMaterials.get(StorageTextureManager.ChestMaterial.PACKED)).buffer(bufferSource, RenderType::entityCutout);
                poseStack.pushPose();
                poseStack.translate(-0.005, -0.005, -0.005);
                poseStack.scale(1.01F, 1.01F, 1.01F);
                this.renderBottomAndLid(poseStack, consumer, lidAngle, packedLight, packedOverlay);
                poseStack.popPose();
            } else if (this.shouldRenderDisplayItem(chestEntity.getBlockPos())) {
                LockRenderer.renderLock(chestEntity, facing, poseStack, bufferSource, packedLight, packedOverlay, 0.3125F, 0.581875F);
                this.displayItemRenderer.renderDisplayItem(chestEntity, poseStack, bufferSource, packedLight, packedOverlay);
            }




    }

    private boolean shouldRenderDisplayItem(BlockPos chestPos) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        return Vec3.atCenterOf(chestPos).closerThan(camera.getPosition(), 32.0);
    }

    private void renderBottomAndLid(PoseStack poseStack, VertexConsumer consumer, float lidAngle, int packedLight, int packedOverlay) {
        this.lidPart.xRot = -(lidAngle * 1.5707964F);
        this.lidPart.render(poseStack, consumer, packedLight, packedOverlay);
        this.bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
    }

    private void renderBottomAndLidWithTint(PoseStack poseStack, VertexConsumer consumer, float lidAngle, int packedLight, int packedOverlay, int tint) {
        float tintRed = (float)(tint >> 16 & 255) / 255.0F;
        float tingGreen = (float)(tint >> 8 & 255) / 255.0F;
        float tintBlue = (float)(tint & 255) / 255.0F;
        this.lidPart.xRot = -(lidAngle * 1.5707964F);
        this.lidPart.render(poseStack, consumer, packedLight, packedOverlay, tintRed, tingGreen, tintBlue, 1.0F);
        this.bottomPart.render(poseStack, consumer, packedLight, packedOverlay, tintRed, tingGreen, tintBlue, 1.0F);
    }

    private void renderLock(PoseStack poseStack, VertexConsumer consumer, float lidAngle, int packedLight, int packedOverlay) {
        this.lockPart.xRot = -(lidAngle * 1.5707964F);
        this.lockPart.render(poseStack, consumer, packedLight, packedOverlay);
    }
    private Block convertSophisticatedToVaultBlock(Block block) {
        if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_TREASURE_CHEST)) {
            return iskallia.vault.init.ModBlocks.TREASURE_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_WOODEN_CHEST)) {
            return iskallia.vault.init.ModBlocks.WOODEN_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_GILDED_CHEST)) {
            return iskallia.vault.init.ModBlocks.GILDED_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ORNATE_CHEST)) {
            return iskallia.vault.init.ModBlocks.ORNATE_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_LIVING_CHEST)) {
            return iskallia.vault.init.ModBlocks.LIVING_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ALTAR_CHEST)) {
            return iskallia.vault.init.ModBlocks.ALTAR_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_FLESH_CHEST)) {
            return iskallia.vault.init.ModBlocks.FLESH_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ENIGMA_CHEST)) {
            return iskallia.vault.init.ModBlocks.ENIGMA_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_HARDENED_CHEST)) {
            return iskallia.vault.init.ModBlocks.HARDENED_CHEST;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ORNATE_STRONGBOX)) {
            return ModBlocks.ORNATE_STRONGBOX;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_GILDED_STRONGBOX)) {
            return ModBlocks.GILDED_STRONGBOX;
        }
        else if(block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_LIVING_STRONGBOX)) {
            return ModBlocks.LIVING_STRONGBOX;
        }
        else {
            return block;
        }

    }

    private Material getTierMaterial(Block block) {
        if (block == xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_TREASURE_CHEST) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(iskallia.vault.init.ModBlocks.TREASURE_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_WOODEN_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.WOODEN_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_GILDED_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.GILDED_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ORNATE_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.ORNATE_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_LIVING_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.LIVING_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ALTAR_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.ALTAR_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_FLESH_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.FLESH_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ENIGMA_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.ENIGMA_CHEST);
        } else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_HARDENED_CHEST)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.HARDENED_CHEST);
        }
        else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_LIVING_STRONGBOX)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.LIVING_STRONGBOX);
        }
        else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_ORNATE_STRONGBOX)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.ORNATE_STRONGBOX);
        }
        else if (block.equals(xyz.iwolfking.woldsvaults.init.ModBlocks.SOPHISTICATED_VAULT_GILDED_STRONGBOX)) {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(ModBlocks.GILDED_STRONGBOX);
        }
        else {
            return (Material) VaultChestRenderer.NORMAL_MATERIAL_MAP.get(iskallia.vault.init.ModBlocks.TREASURE_CHEST);
        }
    }

}
