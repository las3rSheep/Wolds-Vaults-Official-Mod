package xyz.iwolfking.woldsvaults.integration.jei.compat;

import iskallia.vault.tags.ModItemTags;
import mezz.jei.api.recipe.RecipeType;
import net.mehvahdjukaar.cagerium.common.Tier;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.iwolfking.vhapi.integration.jevh.LabeledLootInfo;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.integration.jei.WoldsVaultsJeiPlugin;

import java.util.ArrayList;
import java.util.List;

public class CageriumJEIProvider {
    public static final RecipeType<LabeledLootInfo> CAGERIUM_EGG_SUPPORT = RecipeType.create(WoldsVaults.MOD_ID, "cagerium_eggs", LabeledLootInfo.class);


    public static List<LabeledLootInfo> getEggsPerTier() {
        List<LabeledLootInfo> lootInfo = new ArrayList<>();

        for (Tier tier : Tier.values()) {
            List<ItemStack> eggStacks = new ArrayList<>();

            ForgeRegistries.ENTITIES.forEach(type -> {
                if (tier.acceptsEntityType(type)) {
                    Item eggItem = ForgeSpawnEggItem.fromEntityType(type);

                    if (eggItem != null) {
                        ItemStack stack = new ItemStack(eggItem);

                        eggStacks.add(WoldsVaultsJeiPlugin.formatItemStack(stack, 1, 1, 1.0, 1, 1));
                    }
                }
            });

            if (!eggStacks.isEmpty()) {
                String tierName = tier.name().substring(0, 1).toUpperCase() + tier.name().substring(1).toLowerCase();
                lootInfo.add(LabeledLootInfo.of(
                        eggStacks,
                        new TextComponent("Cagerium - " + tierName + " Eggs"),
                        null
                ));
            }
        }

        return lootInfo;
    }

}
