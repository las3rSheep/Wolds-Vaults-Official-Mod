package xyz.iwolfking.woldsvaults.datagen;

import iskallia.vault.VaultMod;
import iskallia.vault.core.world.roll.IntRoll;
import iskallia.vault.item.crystal.theme.ValueCrystalTheme;
import iskallia.vault.item.crystal.time.ValueCrystalTime;
import net.minecraft.data.DataGenerator;
import xyz.iwolfking.vhapi.api.datagen.AbstractChallengeCrystalProvider;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.objectives.HauntedBraziersCrystalObjective;

public class ModChallengeCrystalProvider extends AbstractChallengeCrystalProvider {
    protected ModChallengeCrystalProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    public void registerConfigs() {
        add("wolds_challenges", builder -> {
            builder.addEntry("pitch_black", challengeEntryBuilder -> {
               challengeEntryBuilder
                       .objective(new HauntedBraziersCrystalObjective(IntRoll.ofConstant(10), 0.5F))
                       .theme(new ValueCrystalTheme(VaultMod.id("classic_vault_dark_cavern")))
                       .time(new ValueCrystalTime(IntRoll.ofConstant(12000)))
                       .exhausted();
            });
        });
    }
}
