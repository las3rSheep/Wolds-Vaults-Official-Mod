package xyz.iwolfking.woldsvaults.recipes.tool;

import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.ToolGearData;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.VanillaAnvilRecipe;
import iskallia.vault.item.tool.ToolItem;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.items.ToolModifierNullifyingItem;

import java.util.ArrayList;
import java.util.List;

public class ToolModifierNullifierRecipe extends VanillaAnvilRecipe {


    @Override
    public boolean onSimpleCraft(AnvilContext context) {

        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() instanceof ToolItem tool && secondary.getItem() instanceof ToolModifierNullifyingItem nullifyingItem) {
            ItemStack output = primary.copy();
            ItemStack input = secondary.copy();
            VaultGearAttribute<?> attributeToRemove = ToolModifierNullifyingItem.getModifierTag(input);
            if(attributeToRemove == null) {
                return false;
            }
            VaultGearData gear = ToolGearData.read(output);
            List<VaultGearModifier<?>> modifiersRemoved = new ArrayList<>();

            if(gear.has(attributeToRemove)) {
                for(VaultGearModifier<?> mod : gear.getModifiers(VaultGearModifier.AffixType.PREFIX)) {
                    if(mod.getAttribute().equals(attributeToRemove)) {
                        if(!modifiersRemoved.contains(mod)) {
                            modifiersRemoved.add(mod);
                            gear.removeModifier(mod);
                        }
                    }
                }
                for(VaultGearModifier<?> mod : gear.getModifiers(VaultGearModifier.AffixType.SUFFIX)) {
                    if(mod.getAttribute().equals(attributeToRemove)) {
                        if(!modifiersRemoved.contains(mod)) {
                            modifiersRemoved.add(mod);
                            gear.removeModifier(mod);
                        }
                    }
                }
            }
            else {
                return false;
            }

            gear.write(output);
            context.setOutput(output);

            context.onTake(context.getTake().append(() -> {
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
