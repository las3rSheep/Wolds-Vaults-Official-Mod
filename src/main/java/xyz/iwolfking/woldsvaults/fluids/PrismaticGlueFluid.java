package xyz.iwolfking.woldsvaults.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidAttributes;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.init.ModBlocks;
import xyz.iwolfking.woldsvaults.init.ModFluids;
import xyz.iwolfking.woldsvaults.init.ModItems;

import javax.annotation.Nonnull;

public class PrismaticGlueFluid extends FlowingFluid {

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_PRISMATIC_GLUE.get();
    }

    @Override
    public Fluid getSource() {
        return ModFluids.PRISMATIC_GLUE.get();
    }

    @Override
    protected boolean canConvertToSource() {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {

    }

    @Override
    protected int getSlopeFindDistance(LevelReader pLevel) {
        return 2;
    }

    @Override
    protected int getDropOff(LevelReader pLevel) {
        return 2;
    }

    @Override
    public Item getBucket() {
        return ModItems.PRISMATIC_GLUE_BUCKET;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState pState, BlockGetter pLevel, BlockPos pPos, Fluid pFluid, Direction pDirection) {
        return false;
    }

    @Override
    public int getTickDelay(LevelReader pLevel) {
        return 100;
    }

    @Override
    protected float getExplosionResistance() {
        return 1000F;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState pState) {
        return ModBlocks.PRISMATIC_GLUE_BLOCK.defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(pState));
    }

    @Override
    public boolean isSource(FluidState pState) {
        return pState.isSource();
    }

    @Override
    public int getAmount(FluidState pState) {
        return pState.getAmount();
    }

    @Override
    public boolean isSame(Fluid pFluid) {
        return pFluid == ModFluids.PRISMATIC_GLUE.get() || pFluid == ModFluids.FLOWING_PRISMATIC_GLUE.get();
    }

    @Override
    public int getSpreadDelay(@Nonnull Level world, BlockPos pos, FluidState p_215667_3_, FluidState p_215667_4_) {
        int i = this.getTickDelay(world);
        if (!p_215667_3_.isEmpty() && !p_215667_4_.isEmpty() && !(Boolean)p_215667_3_.getValue(FALLING) && !(Boolean)p_215667_4_.getValue(FALLING) && p_215667_4_.getHeight(world, pos) > p_215667_3_.getHeight(world, pos) && world.getRandom().nextInt(4) != 0) {
            i *= 4;
        }

        return i;
    }

    @Nonnull
    protected FluidAttributes createAttributes() {
        return FluidAttributes.Water.builder(WoldsVaults.id("block/fluid/prismatic_glue"), WoldsVaults.id("block/fluid/flowing_prismatic_glue")).overlay(ResourceLocation.parse("block/water_overlay")).translationKey("block.woldsvaults.prismatic_glue").density(30000).viscosity(60000).temperature(0).color(0xFFFFFFFF).sound(SoundEvents.BUCKET_FILL, SoundEvents.BUCKET_EMPTY).build(this);
    }

    public static class Flowing extends PrismaticGlueFluid {
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends PrismaticGlueFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
