package xyz.iwolfking.woldsvaults.integration.jei.category;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.init.ModBlocks;
import xyz.iwolfking.woldsvaults.recipes.lib.TrinketFusionRecipe;

public class TrinketFusionCategory implements IRecipeCategory<TrinketFusionRecipe> {

    public static final ResourceLocation UID = WoldsVaults.id("trinket_fusion");

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawableAnimated arrow;

    public TrinketFusionCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(
                WoldsVaults.id("textures/gui/trinket_fuser.png"),
                0, 0, 176, 70
        );

        this.icon = helper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(ModBlocks.TRINKET_FUSION_BLOCK)
        );

        IDrawableStatic arrowStatic = helper.createDrawable(
                WoldsVaults.id( "textures/gui/trinket_fuser.png"),
                176, 0, 24, 16
        );

        this.arrow = helper.createAnimatedDrawable(
                arrowStatic,
                100,
                IDrawableAnimated.StartDirection.LEFT,
                false
        );
    }

    @Override
    public void draw(TrinketFusionRecipe recipe, PoseStack poseStack, double mouseX, double mouseY) {
        arrow.draw(poseStack, 79, 34);
    }

    @Override
    public RecipeType<TrinketFusionRecipe> getRecipeType() {
        return RecipeType.create("woldsvaults", "trinket_fusion", TrinketFusionRecipe.class);
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("jei.woldsvaults.trinket_fusion");
    }

    @Override
    public IDrawable getBackground() { return background; }

    @Override
    public IDrawable getIcon() { return icon; }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, TrinketFusionRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 44, 35)
                .addItemStack(recipe.getInputA());

        builder.addSlot(RecipeIngredientRole.INPUT, 62, 35)
                .addItemStack(recipe.getInputB());

        builder.addSlot(RecipeIngredientRole.INPUT, 16, 35)
                .addIngredient(ForgeTypes.FLUID_STACK, recipe.getFluid());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35)
                .addItemStack(recipe.getOutput());
    }


    @Override @SuppressWarnings("removal")
    public ResourceLocation getUid() {
        return UID;
    }

    @Override @SuppressWarnings("removal")
    public Class<? extends TrinketFusionRecipe> getRecipeClass() {
        return TrinketFusionRecipe.class;
    }
}