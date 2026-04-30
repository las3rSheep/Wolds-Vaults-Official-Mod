package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import iskallia.vault.item.crystal.objective.ElixirCrystalObjective;
import iskallia.vault.item.crystal.objective.ScavengerBingoCrystalObjective;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ElixirCrystalObjective.class, remap = false)
public interface ElixirCrystalObjectiveAccessor {
    @Accessor("objectiveProbability")
    void setObjectiveProbability(float objectiveProbability);


}
