package xyz.iwolfking.woldsvaults.recipes.lib;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class TrinketFusionRecipe {
    private final ItemStack inputA;
    private final ItemStack inputB;
    private final FluidStack fluid;
    private final ItemStack output;

    public TrinketFusionRecipe(ItemStack a, ItemStack b, FluidStack fluid, ItemStack output) {
        this.inputA = a;
        this.inputB = b;
        this.fluid = fluid;
        this.output = output;
    }

    public ItemStack getInputA() { return inputA; }
    public ItemStack getInputB() { return inputB; }
    public FluidStack getFluid() { return fluid; }
    public ItemStack getOutput() { return output; }
}