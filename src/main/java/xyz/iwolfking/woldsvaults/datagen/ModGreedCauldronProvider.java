package xyz.iwolfking.woldsvaults.datagen;

import iskallia.vault.init.ModItems;
import net.minecraft.data.DataGenerator;
import xyz.iwolfking.vhapi.api.datagen.AbstractGreedCauldronProvider;
import xyz.iwolfking.woldsvaults.WoldsVaults;

public class ModGreedCauldronProvider extends AbstractGreedCauldronProvider {
    protected ModGreedCauldronProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    public void registerConfigs() {
        add("wolds_greed_cauldron", builder -> {
            builder.add(ModItems.VAULT_MEAT, 300, 500, 1);
            builder.add(ModItems.AMPLIFYING_FOCUS, 150, 200, 1);
            builder.add(ModItems.NULLIFYING_FOCUS, 150, 200, 1);
            builder.add(ModItems.GEMSTONE, 150, 250, 1);
        });
    }
}
