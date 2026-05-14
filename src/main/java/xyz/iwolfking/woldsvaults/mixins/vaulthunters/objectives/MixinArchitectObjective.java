package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;


import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.objective.ArchitectObjective;
import iskallia.vault.core.world.storage.VirtualWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(value = ArchitectObjective.class, remap = false)
public class MixinArchitectObjective {

    /**
     * @author Josh
     * @reason Remove physical inscription maps in portals rooms, as the data shows up in vaultmapper anyway
     *         This allows for different start rooms so that the map doesn't interfere with the build,
     *         and honestly it's just unnecessary.
     */
    @Overwrite
    public void tickServer(VirtualWorld world, Vault vault) {

    }

}
