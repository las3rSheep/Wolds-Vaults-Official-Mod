package xyz.iwolfking.woldsvaults.data;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ResearchBypassData {
    public static final List<Item> RESEARCH_BYPASS_ITEMS = new ArrayList<>();

    public static void add(Item item) {
        RESEARCH_BYPASS_ITEMS.add(item);
    }

    public static List<Item> getBypassedItems() {
        return RESEARCH_BYPASS_ITEMS;
    }
}
