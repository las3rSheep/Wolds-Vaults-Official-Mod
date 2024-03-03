package xyz.iwolfking.woldsvaults.items.sophisticated;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.common.util.NonNullLazy;
import xyz.iwolfking.woldsvaults.client.renderers.SophisticatedVaultChestItemRenderer;

import java.util.function.Consumer;

public class SophisticatedVaultChestItem extends SophisticatedVaultStorageBlockItem{
    public SophisticatedVaultChestItem(Block block) {
        super(block);
    }

    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(() -> {
                return new SophisticatedVaultChestItemRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
            });

            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return (BlockEntityWithoutLevelRenderer)this.ister.get();
            }
        });
    }
}
