package xyz.iwolfking.woldsvaults.api.registry.record;

import iskallia.vault.core.data.key.SupplierKey;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.item.crystal.objective.CrystalObjective;

import java.util.function.Supplier;

public record CustomVaultObjectiveEntry(String id, String name, Class<? extends CrystalObjective> crystalObjective, Supplier<? extends CrystalObjective> crystalObjectiveSupplier, SupplierKey<Objective> key) {
}
