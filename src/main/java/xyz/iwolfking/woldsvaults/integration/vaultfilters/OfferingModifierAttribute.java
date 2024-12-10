package xyz.iwolfking.woldsvaults.integration.vaultfilters;

import iskallia.vault.item.OfferingItem;
import net.joseph.vaultfilters.attributes.abstracts.StringAttribute;
import net.minecraft.world.item.ItemStack;

public class OfferingModifierAttribute extends StringAttribute {
    public OfferingModifierAttribute(String value) {
        super(value);
    }

    @Override
    public String getValue(ItemStack itemStack) {
        if(itemStack.getItem() instanceof OfferingItem) {
            if(itemStack.getOrCreateTag().contains("Modifier")) {
               return OfferingItem.getModifierName(itemStack.getOrCreateTag().getString("Modifier")).getString();
            }
        }
        return "";
    }

    @Override
    public String getTranslationKey() {
        return "offering_modifier";
    }
}
