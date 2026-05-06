package xyz.iwolfking.woldsvaults.modifiers.vault;

import com.google.gson.annotations.Expose;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.core.world.roll.IntRoll;
import net.minecraft.resources.ResourceLocation;
import xyz.iwolfking.woldsvaults.api.core.vault_events.lib.EventTag;

import java.util.List;

public class EnchantedElixirEventAmountModifier extends VaultModifier<EnchantedElixirEventAmountModifier.Properties> {

    public EnchantedElixirEventAmountModifier(ResourceLocation id, Properties properties, Display display) {
        super(id, properties, display);
    }

    public static class Properties {
        @Expose
        private final int count;


        public Properties(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }
}
