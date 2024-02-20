package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.core.data.adapter.basic.TypeSupplierAdapter;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.objective.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.objectives.EnchantedElixirCrystalObjective;
import xyz.iwolfking.woldsvaults.objectives.HauntedBraziersCrystalObjective;
import xyz.iwolfking.woldsvaults.objectives.UnhingedScavengerCrystalObjective;

@Mixin(CrystalData.class)
public class MixinCrystalData
{

    private static TypeSupplierAdapter objectives = (new TypeSupplierAdapter("type", false)).register("null",NullCrystalObjective .class, () -> {
    return NullCrystalObjective.INSTANCE;
}).register("pool",PoolCrystalObjective .class, PoolCrystalObjective::new).register("empty",EmptyCrystalObjective .class, EmptyCrystalObjective::new).register("boss",BossCrystalObjective .class, BossCrystalObjective::new).register("cake",CakeCrystalObjective.class, CakeCrystalObjective::new).register("scavenger",ScavengerCrystalObjective.class, ScavengerCrystalObjective::new).register("speedrun",SpeedrunCrystalObjective.class, SpeedrunCrystalObjective::new).register("monolith",MonolithCrystalObjective.class, MonolithCrystalObjective::new).register("elixir",ElixirCrystalObjective.class, ElixirCrystalObjective::new).register("paradox",ParadoxCrystalObjective.class, ParadoxCrystalObjective::new).register("herald",HeraldCrystalObjective.class, HeraldCrystalObjective::new).register("compound",CompoundCrystalObjective.class, CompoundCrystalObjective::new).register("ascension",AscensionCrystalObjective.class, AscensionCrystalObjective::new).register("unhinged_scavenger", UnhingedScavengerCrystalObjective.class, UnhingedScavengerCrystalObjective::new).register("haunted_braziers", HauntedBraziersCrystalObjective.class, HauntedBraziersCrystalObjective::new).register("enchanted_elixir", EnchantedElixirCrystalObjective.class, EnchantedElixirCrystalObjective::new);
    @Shadow public static TypeSupplierAdapter<CrystalObjective> OBJECTIVE = objectives;
}
