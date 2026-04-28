package xyz.iwolfking.woldsvaults.mixins.botania;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.research.Restrictions;
import iskallia.vault.research.StageManager;
import iskallia.vault.world.data.PlayerResearchesData;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.block.IWandable;
import vazkii.botania.common.block.tile.TileCraftCrate;
import vazkii.botania.common.block.tile.TileOpenCrate;
import xyz.iwolfking.woldsvaults.api.lib.IPlayerOwnedBlock;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "botania")
        }
)
@Mixin(value = TileCraftCrate.class, remap = false)
public abstract class MixinTileCraftCrate extends TileOpenCrate implements IPlayerOwnedBlock, IWandable {

    @Shadow
    protected abstract Optional<CraftingRecipe> getMatchingRecipe(CraftingContainer craft);

    @Unique
    @Nullable
    private UUID playerUUID;

    public MixinTileCraftCrate(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Inject(method = "craft", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/tile/TileCraftCrate;getMatchingRecipe(Lnet/minecraft/world/inventory/CraftingContainer;)Ljava/util/Optional;"), cancellable = true)
    private void checkOwnerHasResearch(boolean fullCheck, CallbackInfoReturnable<Boolean> cir, @Local(name = "craft") CraftingContainer craft) {
        if(playerUUID == null) {
            cir.setReturnValue(false);
            return;
        }

        CraftingRecipe recipe = this.getMatchingRecipe(craft).orElse(null);

        if(recipe == null) {
            cir.setReturnValue(false);
            return;
        }

        if(PlayerResearchesData.get((ServerLevel) this.level).getResearches(playerUUID).restrictedBy(recipe.getResultItem(), Restrictions.Type.CRAFTABILITY) != null) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = {"writePacketNBT"}, at = {@At("HEAD")})
    public void write(CompoundTag tag, CallbackInfo ci) {
        if (this.playerUUID != null)
            tag.putUUID("PlayerUUID", this.playerUUID);
    }

    @Inject(method = {"readPacketNBT"}, at = {@At("HEAD")})
    public void read(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("PlayerUUID")) {
            playerUUID = tag.getUUID("PlayerUUID");
        }
    }

    @Override
    public void setPlacingPlayer(Player player) {
        this.playerUUID = player.getUUID();
    }

    @Override
    public UUID getPlayerUUID() {
        return this.playerUUID;
    }
}
