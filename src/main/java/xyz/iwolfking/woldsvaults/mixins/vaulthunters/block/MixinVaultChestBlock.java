package xyz.iwolfking.woldsvaults.mixins.vaulthunters.block;

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
        if (!((VaultChestBlock) (Object) this).hasStepBreaking()) {
            super.playerDestroy(world, player, pos, state, te, stack);
        } else {
            player.awardStat(Stats.BLOCK_MINED.get((VaultChestBlock) (Object) this));
            player.causeFoodExhaustion(0.005F);
            if (te instanceof VaultChestTileEntity) {
                VaultChestTileEntity chest = (VaultChestTileEntity)te;
                VaultGearData data = VaultGearData.read(player.getMainHandItem().copy());
                float dismantle_chance = 0.0F;
                if(data != null && data.hasAttribute(ModGearAttributes.DISMANTLE_CHANCE)) {
                    dismantle_chance = data.get(ModGearAttributes.DISMANTLE_CHANCE, VaultGearAttributeTypeMerger.floatSum());
                }
                for(int slot = 0; slot < chest.getContainerSize(); ++slot) {
                    ItemStack invStack = chest.getItem(slot);
                    if (!invStack.isEmpty()) {
                        Block.popResource(world, pos, invStack);
                        chest.setItem(slot, ItemStack.EMPTY);
                        if (dismantle_chance >= 1.0F) {
                            dismantle_chance -= 1.0F;
                        } else if (dismantle_chance > 0.0F) {
                            if (dismantle_chance > Math.random()) {
                                break;
                            }
                            dismantle_chance = 0.0F;
                        } else {
                            break;
                        }
                    }
                }
            }

        }
    }
    /*@Inject(method = "playerDestroy", at = @At(value = "INVOKE", target = "Liskallia/vault/block/entity/VaultChestTileEntity;setItem(ILnet/minecraft/world/item/ItemStack;)V"))
    private void calculateDismantle(CallbackInfo info) {
        System.out.println("A1 I'm not doing this");
    }*/
}
