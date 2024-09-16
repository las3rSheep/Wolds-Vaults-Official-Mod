package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;

import iskallia.vault.core.data.adapter.basic.TypeSupplierAdapter;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.model.CrystalModel;
import iskallia.vault.item.crystal.modifiers.CrystalModifiers;
import iskallia.vault.item.crystal.objective.CrystalObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.api.registry.CustomVaultObjectiveRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;
import xyz.iwolfking.woldsvaults.models.crystal.UnhingedCrystalModel;

@Mixin(value = CrystalData.class, remap = false)
public class MixinCrystalData
{

    @Shadow public static TypeSupplierAdapter<CrystalObjective> OBJECTIVE;
    @Shadow public static TypeSupplierAdapter<CrystalModel> MODEL;

    @Shadow private CrystalModifiers modifiers;

    static {
        for(CustomVaultObjectiveEntry entry : CustomVaultObjectiveRegistry.getCustomVaultObjectiveEntries()) {
            OBJECTIVE.register(entry.id(), entry.crystalObjective(), entry.crystalObjectiveSupplier());
        }

        MODEL.register("unhinged", UnhingedCrystalModel.class, UnhingedCrystalModel::new);
    }

    /**
     * @author iwolfking
     * @reason Always allow catalyst fragments
     */
    @Overwrite
    public boolean canGenerateCatalystFragments() {
        return true;
    }
}
