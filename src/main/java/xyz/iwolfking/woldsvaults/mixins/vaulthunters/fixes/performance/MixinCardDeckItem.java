package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes.performance;

import iskallia.vault.core.card.CardDeck;
import iskallia.vault.item.CardDeckItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import xyz.iwolfking.woldsvaults.performance.data.CardDeckCacheData;

import java.util.Optional;

@Mixin(value = CardDeckItem.class, remap = false)
public class MixinCardDeckItem {

    /**
     * @author iwolfking
     * @reason ...
     */
    @Overwrite
    public static Optional<CardDeck> getCardDeck(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains("data")) {
            CardDeck deck = new CardDeck();
            if(CardDeckCacheData.contains(stack.hashCode())) {
                System.out.println("Reading cached deck");
                return Optional.of(CardDeckCacheData.get(stack.hashCode()));
            }
            System.out.println("Reading new deck data");
            deck.readNbt(stack.getTag().getCompound("data"));
            CardDeckCacheData.add(stack.hashCode(), deck);
            return Optional.of(deck);
        } else {
            return Optional.empty();
        }
    }
}
