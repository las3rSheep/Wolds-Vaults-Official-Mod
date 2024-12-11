package xyz.iwolfking.woldsvaults.integration.vaultfilters;

import net.joseph.vaultfilters.attributes.abstracts.StringAttribute;
import net.minecraft.world.item.ItemStack;
import org.codehaus.plexus.util.StringUtils;
import xyz.iwolfking.woldsvaults.items.LayoutModificationItem;

public class EtchedLayoutTypeAttribute extends StringAttribute {
    public EtchedLayoutTypeAttribute(String value) {
        super(value);
    }

    @Override
    public String getValue(ItemStack itemStack) {
        if(itemStack.getItem() instanceof LayoutModificationItem) {
            if(itemStack.getOrCreateTag().contains("layout")) {
               return StringUtils.capitalise(itemStack.getOrCreateTag().getString("layout"));
            }
        }
        return "";
    }

    @Override
    public String getTranslationKey() {
        return "etched_layout_type";
    }
}
