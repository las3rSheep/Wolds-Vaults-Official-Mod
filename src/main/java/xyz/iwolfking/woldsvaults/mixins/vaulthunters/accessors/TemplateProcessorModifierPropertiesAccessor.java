package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import iskallia.vault.core.vault.modifier.modifier.TemplateProcessorModifier;
import iskallia.vault.core.world.data.tile.TilePredicate;
import iskallia.vault.core.world.processor.tile.TileProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = TemplateProcessorModifier.Properties.class, remap = false)
public interface TemplateProcessorModifierPropertiesAccessor {
    @Accessor("whitelist")
    TilePredicate getWhitelist();

    @Accessor("blacklist")
    TilePredicate getBlacklist();

    @Accessor("fullBlock")
    List<TileProcessor> getFullBlock();

    @Accessor("partialBlock")
    List<TileProcessor> getPartialBlock();
}
