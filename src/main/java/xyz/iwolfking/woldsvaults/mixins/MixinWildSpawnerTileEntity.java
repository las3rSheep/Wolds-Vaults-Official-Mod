package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.block.entity.BaseSpawnerTileEntity;
import iskallia.vault.block.entity.WildSpawnerTileEntity;
import iskallia.vault.config.WildSpawnerConfig;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultLevel;
import iskallia.vault.core.vault.objective.Objectives;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.Iterator;

@Mixin(value = WildSpawnerTileEntity.class, remap = false)
public class MixinWildSpawnerTileEntity extends BaseSpawnerTileEntity {
    @Shadow @Nullable private WildSpawnerConfig.SpawnerGroup spawnerGroup;
    @Shadow private int playerCheckCooldown;

    protected MixinWildSpawnerTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /**
     * @author iwolfking
     * @reason Overwrite wild spawners for Haunted Braziers
     */
    @Overwrite
    private void initSpawnerGroup() {
        if (this.spawnerGroup == null) {
            int vaultLevel = (Integer) ServerVaults.get(this.level).map((vault) -> {
                return ((VaultLevel) vault.get(Vault.LEVEL)).get();
            }).orElse(0);
            Iterator var2 = ModConfigs.WILD_SPAWNER.spawnerGroups.iterator();

            String objective = ServerVaults.get(this.level).get().get(Vault.OBJECTIVES).get(Objectives.KEY);


            while (true) {
                WildSpawnerConfig.SpawnerGroup sg;
                do {
                    do {
                        if (!var2.hasNext()) {
                            if (this.spawnerGroup != null) {
                                this.playerCheckCooldown = Math.min(this.spawnerGroup.blockCheckRadius / 4, 10);
                            }

                            return;
                        }

                        sg = (WildSpawnerConfig.SpawnerGroup) var2.next();
                    } while (sg.minLevel > vaultLevel);
                } while (this.spawnerGroup != null && sg.minLevel <= this.spawnerGroup.minLevel);
                if(objective.equals("haunted_braziers")) {
                    this.spawnerGroup = ModConfigs.WILD_SPAWNER.spawnerGroups.get(3);
                    System.out.println(ModConfigs.WILD_SPAWNER.spawnerGroups.get(3).entities.get(0).value.type);
                    System.out.println(ModConfigs.WILD_SPAWNER.spawnerGroups.get(2).entities.get(0).value.type);
                }
                else {
                    this.spawnerGroup = sg;
                }

            }
        }
    }

    /**
     * @author iwolfking
     * @reason Spawn Wraiths inside of Haunted Braziers
     */






}
