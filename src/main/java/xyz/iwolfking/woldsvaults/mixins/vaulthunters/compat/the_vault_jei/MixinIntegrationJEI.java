package xyz.iwolfking.woldsvaults.mixins.vaulthunters.compat.the_vault_jei;

import iskallia.vault.integration.jei.IntegrationJEI;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = IntegrationJEI.class, remap = false)
public class MixinIntegrationJEI {
    public MixinIntegrationJEI() {
    }

    @Redirect(method = "registerRecipeCatalysts", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/IRecipeCatalystRegistration;addRecipeCatalyst(Lnet/minecraft/world/item/ItemStack;[Lmezz/jei/api/recipe/RecipeType;)V", ordinal = 0), remap = false)
    private void the_vault_jei$updateCataTableRecipeCata(IRecipeCatalystRegistration instance, ItemStack ingredient, RecipeType<?>[] recipeTypes) {
    }

    @Redirect(method = "registerCategories", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/IRecipeCategoryRegistration;addRecipeCategories([Lmezz/jei/api/recipe/category/IRecipeCategory;)V", ordinal = 0), remap = false)
    private void the_vault_jei$updateCataTableRecipeCat(IRecipeCategoryRegistration instance, IRecipeCategory<?>[] iRecipeCategories) {
    }

    @Redirect(method = "registerRecipes", at = @At(value = "INVOKE", target = "Lmezz/jei/api/registration/IRecipeRegistration;addRecipes(Lmezz/jei/api/recipe/RecipeType;Ljava/util/List;)V", ordinal = 0), remap = false)
    private <T> void the_vault_jei$updateCataTableRecipes(IRecipeRegistration instance, RecipeType<T> tRecipeType, List<T> ts) {

    }

}
