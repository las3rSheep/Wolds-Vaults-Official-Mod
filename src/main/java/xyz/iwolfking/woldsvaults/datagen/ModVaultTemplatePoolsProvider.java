package xyz.iwolfking.woldsvaults.datagen;

import iskallia.vault.VaultMod;
import net.minecraft.data.DataGenerator;
import xyz.iwolfking.vhapi.api.datagen.gen.AbstractTemplatePoolProvider;
import xyz.iwolfking.woldsvaults.WoldsVaults;

public class ModVaultTemplatePoolsProvider extends AbstractTemplatePoolProvider {
    public ModVaultTemplatePoolsProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    protected void registerPools() {
        createStandardPoolsForTheme("sculk", WoldsVaults.id("universal_sculk"), VaultMod.id("generic/ore_placeholder_void"));
    }
}
