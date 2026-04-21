package xyz.iwolfking.woldsvaults.mixins.botania;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.common.block.BlockCraftyCrate;
import vazkii.botania.common.block.BlockOpenCrate;
import vazkii.botania.common.block.tile.TileCraftCrate;
import xyz.iwolfking.woldsvaults.api.lib.IPlayerOwnedBlock;

import java.util.UUID;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "botania")
        }
)
@Mixin(value = BlockCraftyCrate.class, remap = false)
public class MixinCraftCrateBlock extends BlockOpenCrate {
    protected MixinCraftCrateBlock(Properties builder) {
        super(builder);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!pLevel.isClientSide() && pPlacer instanceof Player) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof TileCraftCrate)
                ((IPlayerOwnedBlock)blockEntity).setPlacingPlayer((Player)pPlacer);
        }
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }
}
