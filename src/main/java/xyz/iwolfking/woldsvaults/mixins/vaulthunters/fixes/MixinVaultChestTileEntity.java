package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.block.entity.VaultChestTileEntity;
import iskallia.vault.init.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = VaultChestTileEntity.class, remap = false)
public class MixinVaultChestTileEntity {
    /**
     * @author
     * @reason
     */
    @Overwrite
    private int getSize(BlockState state) {
        Block block = state.getBlock();
        if (block != ModBlocks.TREASURE_CHEST && block != ModBlocks.TREASURE_CHEST_PLACEABLE) {
            return block != ModBlocks.WOODEN_CHEST && block != ModBlocks.WOODEN_CHEST_PLACEABLE && block != ModBlocks.WOODEN_BARREL ? 45 : 36;
        } else {
            return 54;
        }
    }
}
