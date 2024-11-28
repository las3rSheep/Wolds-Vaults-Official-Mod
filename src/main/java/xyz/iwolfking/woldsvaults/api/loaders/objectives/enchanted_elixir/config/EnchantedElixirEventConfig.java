package xyz.iwolfking.woldsvaults.api.loaders.objectives.enchanted_elixir.config;

import com.google.gson.annotations.Expose;
import iskallia.vault.config.Config;
import iskallia.vault.util.data.WeightedList;
import xyz.iwolfking.woldsvaults.objectives.data.lib.events.VaultModifierEnchantedEvent;

public class EnchantedElixirEventConfig extends Config {

    @Expose
    public WeightedList<VaultModifierEnchantedEvent> MODIFIER_ENCHANTED_EVENTS;



    @Override
    public String getName() {
        return "enchanted_elixir_events";
    }

    @Override
    protected void reset() {

    }
}
