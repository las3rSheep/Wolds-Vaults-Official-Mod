package xyz.iwolfking.woldsvaults.config.recipes.augment;

import iskallia.vault.config.entry.ItemEntry;
import iskallia.vault.config.entry.recipe.ConfigForgeRecipe;
import iskallia.vault.item.AugmentItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ConfigAugmentRecipe extends ConfigForgeRecipe<AugmentForgeRecipe> {
    public ConfigAugmentRecipe(ResourceLocation id, ResourceLocation theme) {
        super(id, AugmentItem.create(theme));
    }

    @Override
    public AugmentForgeRecipe makeRecipe() {
        ItemStack out = this.output.createItemStack();
        List<ItemStack> in = this.inputs.stream().map(ItemEntry::createItemStack).toList();
        return new AugmentForgeRecipe(this.id, out, in);
    }



}
