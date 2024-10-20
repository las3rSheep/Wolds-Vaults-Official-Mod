package xyz.iwolfking.woldsvaults.entities.ghosts.lib;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.quark.content.mobs.entity.Wraith;
import xyz.iwolfking.woldsvaults.util.VaultModifierUtils;

import java.util.Optional;

public class VaultModifierWraith extends Wraith {

    private final ResourceLocation vaultModifierPool;
    public VaultModifierWraith(EntityType<? extends Wraith> type, Level worldIn, ResourceLocation vaultModifierPool) {
        super(type, worldIn);
        this.vaultModifierPool = vaultModifierPool;
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entityIn) {
        boolean did = super.doHurtTarget(entityIn);
        if (did) {
            if (entityIn instanceof Player player) {
                if(ServerVaults.get(player.level).isPresent()) {
                    Optional<Vault> vaultOpt = ServerVaults.get(player.level);
                    if(vaultOpt.isPresent()) {
                        Vault vault = vaultOpt.get();
                        VaultModifierUtils.addModifierFromPool(vault, vaultModifierPool);
                    }
                }

            }

            double dx = this.getX() - entityIn.getX();
            double dz = this.getZ() - entityIn.getZ();
            Vec3 vec = (new Vec3(dx, 0.0, dz)).normalize().add(0.0, 0.5, 0.0).normalize().scale(0.85);
            this.setDeltaMovement(vec);
        }

        return did;
    }
}
