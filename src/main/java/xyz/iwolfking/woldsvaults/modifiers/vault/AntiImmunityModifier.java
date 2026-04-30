package xyz.iwolfking.woldsvaults.modifiers.vault;

import com.google.gson.annotations.Expose;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import net.minecraft.resources.ResourceLocation;

public class AntiImmunityModifier extends VaultModifier<AntiImmunityModifier.Properties> {

    public AntiImmunityModifier(ResourceLocation id, Properties properties, Display display) {
        super(id, properties, display);
    }

    public static class Properties {
        @Expose
        private final ResourceLocation id;

        public Properties(ResourceLocation id) {
            this.id = id;
        }



        public ResourceLocation getId() {
            return this.id;
        }

    }
}
