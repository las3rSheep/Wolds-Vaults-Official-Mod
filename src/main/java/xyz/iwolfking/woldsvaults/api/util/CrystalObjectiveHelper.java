package xyz.iwolfking.woldsvaults.api.util;

import iskallia.vault.item.crystal.objective.ElixirCrystalObjective;
import iskallia.vault.item.crystal.objective.ScavengerBingoCrystalObjective;
import net.minecraft.resources.ResourceLocation;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.ElixirCrystalObjectiveAccessor;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.ScavengerBingoCrystalObjectiveAccessor;

public class CrystalObjectiveHelper {

    public static ScavengerBingoCrystalObjective createScavingo(float objectiveProbability, int width, int height, boolean blackout, ResourceLocation modifierPool) {
        ScavengerBingoCrystalObjective scavingo = new ScavengerBingoCrystalObjective(objectiveProbability);
        ((ScavengerBingoCrystalObjectiveAccessor)scavingo).setBlackout(blackout);
        ((ScavengerBingoCrystalObjectiveAccessor)scavingo).setHeight(height);
        ((ScavengerBingoCrystalObjectiveAccessor)scavingo).setWidth(width);
        ((ScavengerBingoCrystalObjectiveAccessor)scavingo).setModifierPool(modifierPool);
        return scavingo;
    }

    public static ElixirCrystalObjective createElixir(float objectiveProbability) {
        ElixirCrystalObjective elixir = new ElixirCrystalObjective();
        ((ElixirCrystalObjectiveAccessor)elixir).setObjectiveProbability(objectiveProbability);
        return elixir;
    }
}
