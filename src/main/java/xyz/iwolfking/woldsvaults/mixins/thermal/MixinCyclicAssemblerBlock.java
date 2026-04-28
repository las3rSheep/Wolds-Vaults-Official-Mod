package xyz.iwolfking.woldsvaults.mixins.thermal;

import cofh.thermal.expansion.block.entity.machine.MachineCrafterTile;
import cofh.thermal.lib.block.entity.MachineBlockEntity;
import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.research.Restrictions;
import iskallia.vault.world.data.PlayerResearchesData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.api.lib.IPlayerOwnedBlock;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

@Mixin(value = MachineCrafterTile.class, remap = false)
public abstract class MixinCyclicAssemblerBlock extends MachineBlockEntity implements IPlayerOwnedBlock {

    public MixinCyclicAssemblerBlock(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
        super(tileEntityTypeIn, pos, state);
    }

    @Inject(method = "setRecipe", at = @At(value = "INVOKE", target = "Ljava/util/Optional;get()Ljava/lang/Object;"), cancellable = true)
    private void cancelRecipeIfLocked(CallbackInfo ci, @Local(name = "possibleRecipe") Optional<CraftingRecipe> possibleRecipe) {
        if(playerUUID == null) {
            ci.cancel();
            return;
        }

        if(PlayerResearchesData.get((ServerLevel) this.level).getResearches(playerUUID).restrictedBy(possibleRecipe.get().getResultItem(), Restrictions.Type.CRAFTABILITY) != null) {
            ci.cancel();
        }
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        if(playerUUID != null) {
            nbt.putUUID("PlayerUUID", playerUUID);
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        if(nbt.contains("PlayerUUID")) {
            playerUUID = nbt.getUUID("PlayerUUID");
        }
    }
    @Unique
    @Nullable
    private UUID playerUUID;

    @Override
    public void setPlacingPlayer(Player player) {
        this.playerUUID = player.getUUID();
    }

    @Override
    public UUID getPlayerUUID() {
        return this.playerUUID;
    }
}
