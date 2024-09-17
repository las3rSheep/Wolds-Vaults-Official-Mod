package xyz.iwolfking.woldsvaults.recipes.gear;

import cofh.ensorcellation.init.EnsorcEnchantments;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.VanillaAnvilRecipe;
import iskallia.vault.item.tool.ToolItem;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import xyz.iwolfking.woldsvaults.init.ModItems;

import java.util.Map;

public class MercyEnchantmentAdderRecipe extends VanillaAnvilRecipe {


    @Override
    public boolean onSimpleCraft(AnvilContext context) {
        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() instanceof SwordItem || primary.getItem() instanceof AxeItem || primary.getItem() instanceof ToolItem && secondary.getItem() == ModItems.MERCY_ORB) {
            ItemStack output = primary.copy();
            Map<Enchantment, Integer> stackEnchMap = EnchantmentHelper.getEnchantments(output);
            if(stackEnchMap.containsKey(EnsorcEnchantments.CURSE_MERCY.get())) {
                return false;
            }


            context.onTake(context.getTake().append(() -> {
                output.enchant(EnsorcEnchantments.CURSE_MERCY.get(), 1);
                context.setOutput(output);
                context.getInput()[0].shrink(1);
                context.getInput()[1].shrink(1);
            }));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRegisterJEI(IRecipeRegistration iRecipeRegistration) {

    }
}
