package xyz.iwolfking.woldsvaults.mixins.vaulthunters.testing;

import com.radimous.vhatcaniroll.mixin.accessors.cards.CardScalerFilterConfigAccessor;
import iskallia.vault.core.card.*;
import net.joseph.vaultfilters.mixin.data.CardScaleAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.WoldsVaults;

import java.util.*;

@Mixin(value = CardScaler.class, remap = false)
public abstract class MixinCardScaler {
    @Shadow
    @Final
    private Map<Integer, List<CardScaler.Filter>> filters;

    /**
     * @author iwolfking
     * @reason Testing filtered cards!
     */
    @Overwrite
    private static Set<CardPos> getFilteredCards(CardPos origin, CardDeck deck, CardScaler.Filter filter) {
        Set<CardPos> filteredCards = new HashSet<>();
        Set<CardEntry> filteredEntries = new HashSet<>();
        if (((CardScaler$FilterAccessor)filter).getNeighborFilter() != null) {
            for (CardNeighborType cardNeighborType : ((CardScaler$FilterAccessor)filter).getNeighborFilter()) {
                filteredCards.addAll(cardNeighborType.get(origin, deck));
            }
        } else {
            filteredCards.addAll(deck.getSlots());
            filteredCards.remove(origin);
        }
        filteredCards.forEach((pos) -> deck.getCard(pos).ifPresent((card) -> filteredEntries.addAll(card.getEntries())));
        filteredCards.forEach(cardPos -> {
            deck.getCard(cardPos).ifPresent((card) -> {
                if(card.getFirstName() != null) {
                    WoldsVaults.LOGGER.info(card.getFirstName().toString());
                }
                else {
                    WoldsVaults.LOGGER.info("Cards first name was null!!");
                }

                if(card.getGroups() != null) {
                    card.getGroups().forEach(WoldsVaults.LOGGER::info);
                }
                else {
                    WoldsVaults.LOGGER.info("Cards groups were null!!");
                }
                card.getEntries().forEach(cardEntry -> {
                    WoldsVaults.LOGGER.info(cardEntry.getName().toString());
                });
            });
        });

        WoldsVaults.LOGGER.info("Initial Filtered Cards (from getFiltered)");
        filteredCards.forEach(cardPos -> {
            WoldsVaults.LOGGER.info(cardPos.toString());
        });
        filteredEntries.forEach(cardPos -> {
            WoldsVaults.LOGGER.info(cardPos.getName().toString());
        });

        if (((CardScaler$FilterAccessor)filter).getTierFilter() != null) {
            filteredCards.removeIf((pos) -> {
                Card card =  deck.getCard(pos).orElse( null);
                return card == null || !((CardScaler$FilterAccessor)filter).getTierFilter().contains(card.getTier());
            });
        }

        WoldsVaults.LOGGER.info("Tier Filtered Cards");
        filteredCards.forEach(cardPos -> {
            WoldsVaults.LOGGER.info(cardPos.toString());
        });

        return filteredCards;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getFrequency(int tier, CardPos origin, CardDeck deck) {
        int total = 0;

        for (CardScaler.Filter filter :  CardEntry.getForTier(this.filters, tier).orElse(new ArrayList<>())) {
            Set<CardPos> filteredCards = getFilteredCards(origin, deck, filter);
            Set<CardEntry> filteredEntries = new HashSet<>();
            filteredCards.forEach((pos) -> deck.getCard(pos).ifPresent((card) -> filteredEntries.addAll(card.getEntries())));
            WoldsVaults.LOGGER.info("Initial Filtered Cards");
            filteredEntries.forEach(cardEntry -> {
                WoldsVaults.LOGGER.info(cardEntry.getName().toString());
            });

            if (((CardScaler$FilterAccessor)filter).getColorFilter() != null) {
                WoldsVaults.LOGGER.info("Color filter not null");
                filteredEntries.removeIf((entry) -> ((CardScaler$FilterAccessor)filter).getColorFilter().stream().noneMatch((color) -> entry.getColors().contains(color)));
            }

            WoldsVaults.LOGGER.info("Color Filtered Cards");
            filteredEntries.forEach(cardEntry -> {
                WoldsVaults.LOGGER.info(cardEntry.getName().toString());
            });

            if (((CardScaler$FilterAccessor)filter).getGroupFilter() != null) {
                WoldsVaults.LOGGER.info("Group filter not null");
                filteredEntries.removeIf((entry) -> ((CardScaler$FilterAccessor)filter).getGroupFilter().stream().noneMatch((group) -> entry.getGroups().contains(group)));
            }
            WoldsVaults.LOGGER.info("Group Filtered Cards");
            filteredEntries.forEach(cardEntry -> {
                WoldsVaults.LOGGER.info(cardEntry.getName().toString());
            });


            total += filteredEntries.size();
        }

        return total;
    }
}
