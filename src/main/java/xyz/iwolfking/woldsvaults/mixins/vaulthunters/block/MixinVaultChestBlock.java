
package xyz.iwolfking.woldsvaults.mixins.vaulthunters.block;

import javax.annotation.ParametersAreNonnullByDefault;
import iskallia.vault.block.entity.VaultChestTileEntity;
import iskallia.vault.block.VaultChestBlock;
import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.gear.data.VaultGearData;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
import javax.annotation.Nullable;
import java.util.function.Supplier;


@Mixin(value = VaultChestBlock.class, remap = false)
@ParametersAreNonnullByDefault
public class MixinVaultChestBlock extends ChestBlock {

    public MixinVaultChestBlock(Properties p_51490_, Supplier<BlockEntityType<? extends ChestBlockEntity>> p_51491_) {
        super(p_51490_, p_51491_);
    }

    /**
     * @author Pengolord
     * @reason adding dismantle chance logic
     */
    @Overwrite
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack) {
        VaultChestBlock thisInstance = ((VaultChestBlock) (Object) this);
        if (!thisInstance.hasStepBreaking()) {
            super.playerDestroy(world, player, pos, state, te, stack);
        } else {
            player.awardStat(Stats.BLOCK_MINED.get(thisInstance));
            player.causeFoodExhaustion(0.005F);
            if (te instanceof VaultChestTileEntity chest) {
                VaultGearData data = VaultGearData.read(player.getMainHandItem().copy());
                if(data == null) return;
                boolean hasBreach = data.hasAttribute(ModGearAttributes.BREACHING);
                float dismantle_chance = 0.0F;

                if(data.hasAttribute(ModGearAttributes.DISMANTLE_CHANCE)) {
                    dismantle_chance = data.get(ModGearAttributes.DISMANTLE_CHANCE, VaultGearAttributeTypeMerger.floatSum());
                }

                for(int slot = 0; slot < chest.getContainerSize(); ++slot) {
                    ItemStack invStack = chest.getItem(slot);
                    if (!invStack.isEmpty()) {
                        Block.popResource(world, pos, invStack);
                        chest.setItem(slot, ItemStack.EMPTY);

                        if(!hasBreach) {
                            if (dismantle_chance >= 1.0F) {
                                dismantle_chance -= 1.0F;
                                this.spawnDestroyParticles(world, player, pos, state);
                            } else if (dismantle_chance > 0.0F) {
                                if (dismantle_chance < Math.random()) {
                                    break;
                                }
                                this.spawnDestroyParticles(world, player, pos, state);
                                dismantle_chance = 0.0F;
                            } else {
                                break;
                            }
                        }
                    }
                }
                if(hasBreach || chest.isEmpty()) {
                    world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                }
            }
        }
    }
}
