package xyz.iwolfking.woldsvaults.datagen;

import iskallia.vault.config.ThemeAugmentLoreConfig;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import xyz.iwolfking.vhapi.api.datagen.gen.AbstractThemeProvider;
import xyz.iwolfking.vhapi.api.util.builder.description.DescriptionDataBuilder;
import xyz.iwolfking.vhapi.api.util.builder.description.JsonDescription;
import xyz.iwolfking.woldsvaults.WoldsVaults;

public class ModVaultThemesProvider extends AbstractThemeProvider {
    public ModVaultThemesProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    protected void registerThemes() {
        add(WoldsVaults.id("sculk"), t -> {
            t.type("classic_vault")
                    .starts(WoldsVaults.id("sculk_starts").toString())
                    .rooms(WoldsVaults.id("sculk_rooms").toString())
                    .tunnels(WoldsVaults.id("sculk_tunnels").toString())
                    .ambientLight(0.2f)
                    .fogColor(12358351)
                    .grassColor(8041299)
                    .foliageColor(8041299)
                    .waterColor(3112412)
                    .waterFogColor(3112412)
                    .themeColor(3112412)
                    .particle("minecraft:ambient_entity_effect")
                    .particleProbability(0.002f)
                    .levelEntry("the_vault:default", 30)
                    .levelEntry("the_vault:default", 50)
                    .themeWeight(5)
                    .themeGroup("Void");
        });

        add(WoldsVaults.id("astral"), t -> {
            t.type("classic_vault")
                    .starts(WoldsVaults.id("astral_starts").toString())
                    .rooms(WoldsVaults.id("astral_rooms").toString())
                    .tunnels(WoldsVaults.id("astral_tunnels").toString())
                    .ambientLight(0.2f)
                    .fogColor(12358351)
                    .grassColor(8041299)
                    .foliageColor(8041299)
                    .waterColor(3112412)
                    .waterFogColor(3112412)
                    .themeColor(3112412)
                    .particle("minecraft:ambient_entity_effect")
                    .particleProbability(0.002f)
                    .levelEntry("the_vault:default", 30)
                    .levelEntry("the_vault:default", 50)
                    .themeWeight(5)
                    .themeGroup("Astral")
                    .themeLore(new ThemeAugmentLoreConfig.AugmentLore("Astral", new DescriptionDataBuilder()
                            .description(JsonDescription.perk("Space, the final frontier.\n"))
                            .description(JsonDescription.mobs("Hordes: ", new JsonDescription.MobEntry("WIP", 1, 2, 1), new JsonDescription.MobEntry("Test", 3, 2, 3)))
                            .description(JsonDescription.mobs("Assassins: ", new JsonDescription.MobEntry("WIP", 1, 2, 1), new JsonDescription.MobEntry("Test", 3, 2, 3)))
                            .description(JsonDescription.mobs("Tanks: ", new JsonDescription.MobEntry("WIP", 1, 2, 1), new JsonDescription.MobEntry("Test", 3, 2, 3)))
                            .description(JsonDescription.dwellers(3)).build(), 3112412));
        });
    }
}

