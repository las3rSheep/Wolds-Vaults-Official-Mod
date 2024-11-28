package xyz.iwolfking.woldsvaults.config.recipes.augment;

import com.google.gson.annotations.Expose;
import iskallia.vault.config.recipe.ForgeRecipeType;
import iskallia.vault.config.recipe.ForgeRecipesConfig;

import java.util.ArrayList;
import java.util.List;

public class AugmentRecipesConfig extends ForgeRecipesConfig<ConfigAugmentRecipe, AugmentForgeRecipe>
{

    @Expose
    private final List<ConfigAugmentRecipe> augmentRecipes = new ArrayList<>();

    public AugmentRecipesConfig() {
        super(ForgeRecipeType.valueOf("AUGMENT"));
    }

    protected void reset() {

    }

    public List<ConfigAugmentRecipe> getConfigRecipes() {
        return this.augmentRecipes;
    }
}
