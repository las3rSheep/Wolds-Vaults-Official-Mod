package xyz.iwolfking.woldsvaults.recipes.capstone;

import iskallia.vault.init.ModItems;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.VanillaAnvilRecipe;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.config.forge.OptionsHolder;

public class MoteClarityCapstoneRecipe extends VanillaAnvilRecipe {
    public MoteClarityCapstoneRecipe() {
    }

    public boolean onSimpleCraft(AnvilContext context) {
        if(!OptionsHolder.COMMON.enableMoteRecipes.get()) {
            return false;
        }
        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() == ModItems.VAULT_CRYSTAL && secondary.getItem() == ModItems.MOTE_CLARITY) {
            ItemStack output = primary.copy();
            CrystalData crystal = CrystalData.read(output);
            crystal.getModifiers().setClarity(true);
            context.onTake(context.getTake().append(() -> {
                context.getInput()[0].shrink(1);
                context.getInput()[1].shrink(1);
            }));
            return true;
        } else {
            return false;
        }
    }

    public void onRegisterJEI(IRecipeRegistration registry) {
    }
}
