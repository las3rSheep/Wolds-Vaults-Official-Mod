package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.block.TreasureContainerBlock;
import iskallia.vault.block.entity.TreasureContainerTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.spongepowered.asm.mixin.Mixin;


@Mixin(value = TreasureContainerBlock.class, remap = false)
public abstract class MixinTreasureContainerBlock extends HorizontalDirectionalBlock implements EntityBlock {
    protected MixinTreasureContainerBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof TreasureContainerTileEntity treasure) {
                if (!treasure.isGenerated()) {
                    treasure.generateLoot(pPlayer);
                }
            }
    }


}
