package xyz.iwolfking.woldsvaults.recipes.crystal;

import iskallia.vault.VaultMod;
import iskallia.vault.core.vault.modifier.VaultModifierStack;
import iskallia.vault.core.vault.modifier.registry.VaultModifierRegistry;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.IdentifiableItem;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.VaultCrystalItem;
import iskallia.vault.item.crystal.properties.CapacityCrystalProperties;
import iskallia.vault.recipe.anvil.AnvilContext;
import iskallia.vault.recipe.anvil.VanillaAnvilRecipe;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AnvilBlock;
import xyz.iwolfking.vhapi.integration.jevh.VHAPIJEIPlugin;
import xyz.iwolfking.vhapi.integration.jevh.categories.CrystalWorkbenchRecipeCategory;
import xyz.iwolfking.vhapi.integration.jevh.lib.CrystalWorkbenchRecipe;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
import xyz.iwolfking.woldsvaults.init.ModItems;
import xyz.iwolfking.woldsvaults.items.gear.VaultMapItem;
import java.util.List;

import static xyz.iwolfking.woldsvaults.items.gear.VaultMapItem.applySpecialModifiers;

public class MapModificationRecipe extends VanillaAnvilRecipe {
    @Override
    public boolean onSimpleCraft(AnvilContext context) {
        if (context.getBlockState().map((state) -> state.getBlock() instanceof AnvilBlock).orElse(false)) {
            return false;
        }

        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() instanceof VaultCrystalItem && secondary.getItem() == ModItems.MAP) {
            ItemStack output = primary.copy();
            CrystalData data = CrystalData.read(output);

            if (data.getProperties().isUnmodifiable()) {
                return false;
            }

            if (!(secondary.getItem() instanceof VaultMapItem vaultMapItem)) {
                return false;
            }
            else {
                return vaultMapItem.applyCrystalRecipe(context, data, secondary, output);
            }
        }

        return false;
    }

    @Override
    public void onRegisterJEI(IRecipeRegistration registry) {

        ItemStack map = new ItemStack(ModItems.MAP);
        if(map.getItem() instanceof IdentifiableItem identifiableItem) {
            identifiableItem.instantIdentify(null, map);
        }

        VaultGearData mapData = VaultGearData.read(map);

        ItemStack crystalOutput = VaultCrystalItem.create(crystalData -> {
            crystalData.getProperties().setLevel(100);
            applySpecialModifiers(crystalData, mapData, VaultGearModifier.AffixType.IMPLICIT, null, null, false);
            applySpecialModifiers(crystalData, mapData, VaultGearModifier.AffixType.PREFIX, null, null, false);
            applySpecialModifiers(crystalData, mapData, VaultGearModifier.AffixType.SUFFIX, null, null, false);
            crystalData.getProperties().setUnmodifiable(true);
        });

        registry.addRecipes(List.of(new CrystalWorkbenchRecipe(map, crystalOutput)), CrystalWorkbenchRecipeCategory.UID);
    }

}
