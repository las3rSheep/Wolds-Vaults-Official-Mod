package xyz.iwolfking.woldsvaults.integration.vaultfilters;

import net.joseph.vaultfilters.attributes.abstracts.IntAttribute;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.items.LayoutModificationItem;

public class EtchedLayoutTunnelAttribute extends IntAttribute {
    public EtchedLayoutTunnelAttribute(Integer value) {
        super(value);
    }

    @Override
    public Integer getValue(ItemStack itemStack) {
        if(itemStack.getItem() instanceof LayoutModificationItem) {
            if(itemStack.getOrCreateTag().contains("tunnel")) {
               return itemStack.getOrCreateTag().getInt("tunnel");
            }
        }
        return 0;
    }

    @Override
    public String getTranslationKey() {
        return "etched_layout_tunnel";
    }
}
