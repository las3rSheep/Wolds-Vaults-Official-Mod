package xyz.iwolfking.woldsvaults.datagen;

import net.minecraft.data.DataGenerator;
import xyz.iwolfking.vhapi.api.datagen.AbstractGreedTraderProvider;
import xyz.iwolfking.woldsvaults.WoldsVaults;

public class ModGreedTraderProvider extends AbstractGreedTraderProvider {
    protected ModGreedTraderProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    public void registerConfigs() {
        add("wolds_greed_challenges", builder -> {
            builder.addChallengeEntry("Pitch Black", "Lights out! You are blinded, fumbling through a spooky dark cavern...", "pitch_black", 1, -1);
            builder.addChallengeEntry("Ultra Hard", "Who said challenges were supposed to be fun, anyway?", "ultra_hard", 12, -1);
            builder.addChallengeEntry("Elixir of Doom", "RNG, more like Ridiculous Negative Garbage.", "elixir_of_doom", 3, -1);
            builder.addChallengeEntry("Ballistic Blackout", "Like a Bingo but more...", "ballistic_bingo_blackout", 2, -1);
            builder.addChallengeEntry("Collectathon", "Get them all and then some more! Black out that board!", "blackout_scavingo", 3, -1);
            builder.addChallengeEntry("God's Challenge", "It seems the god's are impressed with you... time to prove yourself to them.", "gods_challenge", 6, -1);
            builder.addChallengeEntry("Big Bad Brew", "Alchemy? I never even passed Geometry!", "big_bad_brew", 7, -1);
            builder.addChallengeEntry("Chaos Chaos Chaos", "OH, IT'S JUST A SIMPLE NUMBERS GAME", "chaos_chaos_chaos", 9, -1);
            builder.addChallengeEntry("Rage Cage", "The elites are back for more, and brought reinforcements!", "rage_cage", 11, -1);
            builder.addChallengeEntry("Survival of the Fittest", "Last as long as you can... but you won't.", "survival_of_the_fittest", 5, -1);
        });
    }
}
