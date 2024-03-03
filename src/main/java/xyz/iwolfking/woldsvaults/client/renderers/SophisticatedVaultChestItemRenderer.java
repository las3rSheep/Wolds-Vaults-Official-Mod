package xyz.iwolfking.woldsvaults.client.renderers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.p3pp3rf1y.sophisticatedstorage.block.ChestBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.ITintableBlockItem;
import net.p3pp3rf1y.sophisticatedstorage.item.WoodStorageBlockItem;
import xyz.iwolfking.woldsvaults.blocks.tiles.SophisticatedVaultChestEntity;

public class SophisticatedVaultChestItemRenderer extends BlockEntityWithoutLevelRenderer {
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final LoadingCache<BlockItem, SophisticatedVaultChestEntity> chestBlockEntities = CacheBuilder.newBuilder().maximumSize(512L).weakKeys().build(new CacheLoader<BlockItem, SophisticatedVaultChestEntity>() {
        public SophisticatedVaultChestEntity load(BlockItem blockItem) {
            return new SophisticatedVaultChestEntity(BlockPos.ZERO, (BlockState)blockItem.getBlock().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH));
        }
    });

    public SophisticatedVaultChestItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
        super(blockEntityRenderDispatcher, entityModelSet);
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
    }

    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Item var8 = stack.getItem();
        if (var8 instanceof BlockItem blockItem) {
            SophisticatedVaultChestEntity chestBlockEntity = (SophisticatedVaultChestEntity)this.chestBlockEntities.getUnchecked(blockItem);
            Item var10 = stack.getItem();
            if (var10 instanceof ITintableBlockItem tintableBlockItem) {
                chestBlockEntity.getStorageWrapper().setMainColor((Integer)tintableBlockItem.getMainColor(stack).orElse(-1));
                chestBlockEntity.getStorageWrapper().setAccentColor((Integer)tintableBlockItem.getAccentColor(stack).orElse(-1));
            }



            chestBlockEntity.setPacked(WoodStorageBlockItem.isPacked(stack));
            BlockEntityRenderer<SophisticatedVaultChestEntity> blockentityrenderer = this.blockEntityRenderDispatcher.getRenderer(chestBlockEntity);
            if (blockentityrenderer != null) {
                blockentityrenderer.render(chestBlockEntity, 0.0F, poseStack, buffer, packedLight, packedOverlay);
            }

        }
    }
}
