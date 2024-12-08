package xyz.iwolfking.woldsvaults.api.helper;

import iskallia.vault.VaultMod;
import iskallia.vault.config.gear.VaultGearTierConfig;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.modification.GearModification;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.util.MiscUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WoldGearModifierHelper {
    public static GearModification.Result addUnusualModifier(ItemStack stack, long worldGameTime, Random random) {
        VaultGearData data = VaultGearData.read(stack);
        VaultGearTierConfig cfg = (VaultGearTierConfig)VaultGearTierConfig.getConfig(stack).orElse((VaultGearTierConfig) null);
        if (cfg == null) {
            VaultMod.LOGGER.error("Unknown VaultGear: " + stack);
            return GearModification.Result.errorUnmodifiable();
        } else if (!data.isModifiable()) {
            return GearModification.Result.errorUnmodifiable();
        } else {
            int itemLevel = data.getItemLevel();
            int prefixes = (Integer)data.getFirstValue(ModGearAttributes.PREFIXES).orElse(0);
            int suffixes = (Integer)data.getFirstValue(ModGearAttributes.SUFFIXES).orElse(0);
            prefixes -= data.getModifiers(VaultGearModifier.AffixType.PREFIX).size();
            suffixes -= data.getModifiers(VaultGearModifier.AffixType.SUFFIX).size();
            boolean hasUnusual = false;
            for(VaultGearModifier modifier : data.getModifiers(VaultGearModifier.AffixType.PREFIX)) {
                hasUnusual = modifier.getCategories().contains(VaultGearModifier.AffixCategory.valueOf("UNUSUAL"));
                break;
            }

            if(hasUnusual) {
                return GearModification.Result.makeActionError("full", new Component[0]);
            }

            for(VaultGearModifier modifier : data.getModifiers(VaultGearModifier.AffixType.SUFFIX)) {
                hasUnusual = modifier.getCategories().contains(VaultGearModifier.AffixCategory.valueOf("UNUSUAL"));
                break;
            }

            if(hasUnusual) {
                return GearModification.Result.makeActionError("full", new Component[0]);
            }

            if (prefixes <= 0 && suffixes <= 0) {
                return GearModification.Result.makeActionError("full", new Component[0]);
            } else {
                List<VaultGearModifier.AffixType> types = new ArrayList();
                if (prefixes > 0) {
                    types.add(VaultGearModifier.AffixType.PREFIX);
                }

                if (suffixes > 0) {
                    types.add(VaultGearModifier.AffixType.SUFFIX);
                }



                VaultGearModifier.AffixType type = (VaultGearModifier.AffixType) MiscUtils.getRandomEntry(types, random);
                if(type == null) {
                    return GearModification.Result.errorInternal();
                }

                VaultGearTierConfig.ModifierAffixTagGroup modifierAffixTagGroup = VaultGearTierConfig.ModifierAffixTagGroup.valueOf("UNUSUAL_PREFIX");


                if(type.equals(VaultGearModifier.AffixType.PREFIX)) {
                    return cfg.getRandomModifier(modifierAffixTagGroup, itemLevel, random,data.getExistingModifierGroups(VaultGearData.Type.EXPLICIT_MODIFIERS)).map((modifier) -> {
                        data.getAllModifierAffixes().forEach(VaultGearModifier::resetGameTimeAdded);
                        modifier.setGameTimeAdded(worldGameTime);
                        modifier.addCategory(VaultGearModifier.AffixCategory.valueOf("UNUSUAL"));
                        data.addModifier(type, modifier);
                        data.write(stack);
                        return GearModification.Result.makeSuccess();
                    }).orElse(GearModification.Result.makeActionError("no_modifiers", new Component[0]));
                }

                else if(type.equals(VaultGearModifier.AffixType.SUFFIX)) {
                    return (GearModification.Result)cfg.getRandomModifier(VaultGearTierConfig.ModifierAffixTagGroup.valueOf("UNUSUAL_SUFFIX"), itemLevel, random, data.getExistingModifierGroups(VaultGearData.Type.EXPLICIT_MODIFIERS)).map((modifier) -> {
                        data.getAllModifierAffixes().forEach(VaultGearModifier::resetGameTimeAdded);
                        modifier.setGameTimeAdded(worldGameTime);
                        modifier.addCategory(VaultGearModifier.AffixCategory.valueOf("UNUSUAL"));
                        data.addModifier(type, modifier);
                        data.write(stack);
                        return GearModification.Result.makeSuccess();
                    }).orElse(GearModification.Result.makeActionError("no_modifiers", new Component[0]));
                }

                return GearModification.Result.errorInternal();

            }
        }
    }

    public static GearModification.Result removeRandomModifierAlways(ItemStack stack, Random random) {
        VaultGearData data = VaultGearData.read(stack);
            List<VaultGearModifier<?>> affixes = new ArrayList();
            affixes.addAll(data.getModifiers(VaultGearModifier.AffixType.PREFIX));
            affixes.addAll(data.getModifiers(VaultGearModifier.AffixType.SUFFIX));
            affixes.removeIf((modifier) -> {
                return !modifier.hasNoCategoryMatching(VaultGearModifier.AffixCategory::cannotBeModifiedByArtisanFoci);
            });
            if (affixes.isEmpty()) {
                return GearModification.Result.makeActionError("no_modifiers", new Component[0]);
            } else {
                VaultGearModifier<?> randomMod = (VaultGearModifier) MiscUtils.getRandomEntry(affixes, random);
                data.removeModifier(randomMod);
                data.write(stack);
                return GearModification.Result.makeSuccess();
            }
    }
}
