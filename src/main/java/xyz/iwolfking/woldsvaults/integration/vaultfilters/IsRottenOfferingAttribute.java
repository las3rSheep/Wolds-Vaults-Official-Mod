package xyz.iwolfking.woldsvaults.integration.vaultfilters;

import iskallia.vault.item.OfferingItem;
import net.joseph.vaultfilters.attributes.abstracts.BooleanAttribute;
import net.minecraft.world.item.ItemStack;

public class IsRottenOfferingAttribute extends BooleanAttribute {
    public IsRottenOfferingAttribute(Boolean value) {
        super(true);
    }

    public Boolean getValue(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof OfferingItem)) {
            return null;
        } else {
            if(itemStack.getOrCreateTag().contains("rotten")) {
                return true;
            }
        }
        return false;
    }

    public String getTranslationKey() {
        return "rotten_offering";
    }
}
