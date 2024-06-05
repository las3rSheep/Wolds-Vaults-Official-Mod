package xyz.iwolfking.woldsvaults.mixins;


import net.blay09.mods.cookingforblockheads.block.BlockKitchen;
import net.blay09.mods.cookingforblockheads.tile.IMutableNameable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockKitchen.class)
public class MixinKitchenBlock {

    /**
     * @author iwolfking
     * @reason Patch nbt chunk reset with renamed Spice Rack
     */
    @Overwrite
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof IMutableNameable) {
                ((IMutableNameable) blockEntity).setCustomName(itemStack.getHoverName());
            }
        }

    }
}
