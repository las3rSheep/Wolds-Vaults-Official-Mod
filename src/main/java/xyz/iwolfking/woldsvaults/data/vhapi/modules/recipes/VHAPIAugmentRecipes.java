package xyz.iwolfking.woldsvaults.data.vhapi.modules.recipes;

import xyz.iwolfking.woldsvaults.data.vhapi.lib.IVHAPIModule;

public class VHAPIAugmentRecipes implements IVHAPIModule {
    public void init() {
        IVHAPIModule.registerVHAPIFile("recipes/augments.json", "vault_recipes/augment/wold_augment_recipes");
    }
}
