package xyz.iwolfking.woldsvaults.config.recipes.augment;

import iskallia.vault.config.recipe.ForgeRecipeType;
import iskallia.vault.container.oversized.OverSizedItemStack;
import iskallia.vault.core.data.key.ThemeKey;
import iskallia.vault.gear.crafting.recipe.VaultForgeRecipe;
import iskallia.vault.item.AugmentItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class AugmentForgeRecipe extends VaultForgeRecipe {

    public AugmentForgeRecipe(ResourceLocation id, ItemStack output, List<ItemStack> inputs) {
        super(ForgeRecipeType.valueOf("AUGMENT"), id, output, inputs);
    }

    public AugmentForgeRecipe(Object o, Object o1) {
        super(ForgeRecipeType.valueOf("AUGMENT"), (ResourceLocation) o, (ItemStack) o1);
    }

    public ItemStack createOutput(List<OverSizedItemStack> consumed, ServerPlayer crafter, int vaultLevel) {
        return super.createOutput(consumed, crafter, vaultLevel);
    }

    public void addCraftingDisplayTooltip(ItemStack result, List<Component> out) {
        Optional<ThemeKey> themeKey = AugmentItem.getTheme(result);
        themeKey.ifPresent(key -> out.add(new TextComponent("Theme: ").append(new TextComponent(key.getName()).withStyle(Style.EMPTY.withColor(key.getColor())))));
    }
}
