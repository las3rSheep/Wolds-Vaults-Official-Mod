package xyz.iwolfking.woldsvaults.performance.data;

import iskallia.vault.core.card.CardDeck;

import java.util.HashMap;
import java.util.Map;

public class CardDeckCacheData {

    public static Map<Integer, CardDeck> getHashcodeToDeckMap() {
        return HASHCODE_TO_DECK_MAP;
    }

    public static void add(Integer hash, CardDeck deck) {
        HASHCODE_TO_DECK_MAP.put(hash, deck);
    }

    public static CardDeck get(Integer hash) {
        return HASHCODE_TO_DECK_MAP.get(hash);
    }

    public static boolean contains(Integer hash) {
        return HASHCODE_TO_DECK_MAP.containsKey(hash);
    }

    public static void invalidate(Integer hash) {
        HASHCODE_TO_DECK_MAP.remove(hash);
    }

    private static Map<Integer, CardDeck> HASHCODE_TO_DECK_MAP = new HashMap<>();


}
