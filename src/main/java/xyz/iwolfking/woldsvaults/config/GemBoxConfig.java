package xyz.iwolfking.woldsvaults.config;

import com.google.gson.annotations.Expose;
import iskallia.vault.config.Config;
import iskallia.vault.config.entry.vending.ProductEntry;
import iskallia.vault.core.util.WeightedList;
import net.minecraft.world.item.Items;

public class GemBoxConfig extends Config {
    @Expose
    /* 10 */   public WeightedList<ProductEntry> POOL = new WeightedList<>();
    /*    */
    /*    */
    /*    */   public String getName() {
        /* 14 */     return "gem_box";
        /*    */   }
    /*    */
    /*    */
    /*    */   protected void reset() {
        /* 19 */     this.POOL.add(new ProductEntry(Items.APPLE, 8, null), 3);
        /* 20 */     this.POOL.add(new ProductEntry(Items.GOLDEN_APPLE, 1, null), 1);
        /*    */   }
}
