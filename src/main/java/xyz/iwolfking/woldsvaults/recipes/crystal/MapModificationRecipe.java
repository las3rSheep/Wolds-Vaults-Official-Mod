package xyz.iwolfking.woldsvaults.recipes.crystal;

import iskallia.vault.VaultMod;
import iskallia.vault.config.VaultCrystalConfig;
import iskallia.vault.core.vault.modifier.VaultModifierStack;
import iskallia.vault.core.vault.modifier.registry.VaultModifierRegistry;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.VaultCrystalItem;
import iskallia.vault.item.crystal.objective.CrystalObjective;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.VanillaAnvilRecipe;
import iskallia.vault.item.crystal.theme.CrystalTheme;
import iskallia.vault.item.crystal.theme.ValueCrystalTheme;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
import xyz.iwolfking.woldsvaults.init.ModItems;
import xyz.iwolfking.woldsvaults.items.gear.VaultMapItem;
import xyz.iwolfking.woldsvaults.modifiers.vault.lib.SettableValueVaultModifier;

public class MapModificationRecipe extends VanillaAnvilRecipe {
    @Override
    public boolean onSimpleCraft(AnvilContext context) {
        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() instanceof VaultCrystalItem crystal && secondary.getItem() == ModItems.MAP) {
            ItemStack output = primary.copy();
            CrystalData data = CrystalData.read(output);

            if(data.getProperties().isUnmodifiable()) {
                return false;
            }

            if(!(secondary.getItem() instanceof VaultMapItem map)) {
                return false;
            }

            VaultGearData mapData = VaultGearData.read(secondary);

            String themeId = mapData.getFirstValue(ModGearAttributes.THEME).orElse(null);
            String objectiveId = mapData.getFirstValue(ModGearAttributes.OBJECTIVE).orElse(null);

            if(themeId != null) {
                CrystalTheme theme = new ValueCrystalTheme(new ResourceLocation(themeId));
                data.setTheme(theme);
            }
            else {
                return false;
            }

            if(objectiveId != null) {
                if(CrystalData.OBJECTIVE.getValue(objectiveId) != null) {
                    CrystalObjective objective = CrystalData.OBJECTIVE.getValue(objectiveId);
                    if(ModConfigs.VAULT_CRYSTAL.OBJECTIVES.containsKey(VaultMod.id(objectiveId))) {
                        VaultCrystalConfig.ObjectiveEntry entry = ModConfigs.VAULT_CRYSTAL.OBJECTIVES.get(VaultMod.id(objectiveId)).getForLevel(data.getProperties().getLevel().orElse(0)).orElse(null);
                        if(entry != null) {
                            CrystalObjective obj = entry.pool.getRandom().orElse(null);
                            if(obj != null) {
                                objective = obj;
                            }
                        }
                    }
                    data.setObjective(objective);

                }
            }
            else {
                return false;
            }

            for(VaultGearModifier<?> mod : mapData.getModifiers(VaultGearModifier.AffixType.PREFIX)) {
                    VaultModifier<?> vaultMod = VaultModifierRegistry.get(mod.getModifierIdentifier());
                    if(vaultMod instanceof SettableValueVaultModifier<?> settableValueVaultModifier) {
                        settableValueVaultModifier.properties().setValue((Float) mod.getValue());
                        VaultModifierStack stack = new VaultModifierStack(settableValueVaultModifier, 1);
                        data.getModifiers().add(stack);
                    }
            }

            for(VaultGearModifier<?> mod : mapData.getModifiers(VaultGearModifier.AffixType.SUFFIX)) {
                VaultModifier<?> vaultMod = VaultModifierRegistry.get(mod.getModifierIdentifier());
                if(vaultMod instanceof SettableValueVaultModifier<?> settableValueVaultModifier) {
                    settableValueVaultModifier.properties().setValue((Float) mod.getValue());
                    VaultModifierStack stack = new VaultModifierStack(settableValueVaultModifier, 1);
                    data.getModifiers().add(stack);
                }
            }



            data.write(output);
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
