package xyz.iwolfking.woldsvaults.mixins.vaulthunters.testing;

import iskallia.vault.core.card.CardEntry;
import iskallia.vault.core.card.CardNeighborType;
import iskallia.vault.core.card.CardScaler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(CardScaler.Filter.class)
public interface CardScaler$FilterAccessor {
    @Accessor("neighborFilter")
    Set<CardNeighborType> getNeighborFilter();

    @Accessor("groupFilter")
    Set<String> getGroupFilter();

    @Accessor("tierFilter")
    Set<Integer> getTierFilter();


    @Accessor("colorFilter")
    Set<CardEntry.Color> getColorFilter();
}
