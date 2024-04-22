package xyz.iwolfking.woldsvaults.api.registry;

import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.item.crystal.objective.CrystalObjective;

import java.util.ArrayList;
import java.util.List;

public class CustomVaultObjectiveRegistry {

    public static final List<Objective> CUSTOM_OBJECTIVES = new ArrayList<>();
    public static final List<CrystalObjective> CUSTOM_CRYSTAL_OBJECTIVES = new ArrayList<>();;

    public static void register(Objective objective, CrystalObjective crystalObjective) {
        CUSTOM_OBJECTIVES.add(objective);
        CUSTOM_CRYSTAL_OBJECTIVES.add(crystalObjective);
    }
}
