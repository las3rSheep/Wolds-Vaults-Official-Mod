package xyz.iwolfking.woldsvaults.api.objectives.enchanted_elixir;

import iskallia.vault.util.data.WeightedList;
import xyz.iwolfking.vhapi.api.events.VaultConfigEvent;
import xyz.iwolfking.vhapi.api.lib.loaders.VaultConfigDataLoader;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.api.objectives.enchanted_elixir.config.EnchantedElixirEventConfig;
import xyz.iwolfking.woldsvaults.objectives.data.EnchantedEventsRegistry;
import xyz.iwolfking.woldsvaults.objectives.data.lib.events.VaultModifierEnchantedEvent;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

import java.util.HashMap;
import java.util.Map;

public class EnchantedElixirEventLoader extends VaultConfigDataLoader<EnchantedElixirEventConfig> {
    public EnchantedElixirEventLoader() {
        super(new EnchantedElixirEventConfig(), "enchanted_elixir_events", new HashMap<>(), WoldsVaults.MOD_ID);
    }

    @Override
    public void afterConfigsLoad(VaultConfigEvent.End event) {
        for(EnchantedElixirEventConfig config : CUSTOM_CONFIGS.values()) {
            for (WeightedList.Entry<VaultModifierEnchantedEvent> modifierEnchantedEventEntry : config.MODIFIER_ENCHANTED_EVENTS) {
                EnchantedEventsRegistry.register(modifierEnchantedEventEntry.value, ((double)modifierEnchantedEventEntry.weight), modifierEnchantedEventEntry.value.isOmega, modifierEnchantedEventEntry.value.isPositive);
            }
        }
        this.CUSTOM_CONFIGS.clear();
    }
}
