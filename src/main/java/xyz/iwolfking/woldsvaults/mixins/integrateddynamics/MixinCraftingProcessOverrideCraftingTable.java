package xyz.iwolfking.woldsvaults.mixins.integrateddynamics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.authlib.GameProfile;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.FakePlayer;
import org.cyclops.integratedcrafting.core.crafting.processoverride.CraftingProcessOverrideCraftingTable;
import org.cyclops.integrateddynamics.api.part.PartPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.iwolfking.woldsvaults.blocks.tiles.OwnedCraftingTableTileEntity;

import java.util.UUID;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "integratedcrafting")
        }
)
@Mixin(value = CraftingProcessOverrideCraftingTable.class, remap = false)
public class MixinCraftingProcessOverrideCraftingTable {

    @WrapOperation(
            method = "lambda$craft$1",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/cyclops/integratedcrafting/core/crafting/processoverride/CraftingProcessOverrideCraftingTable;getFakePlayer(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraftforge/common/util/FakePlayer;"
            )
    )
    private static FakePlayer redirectFakePlayer(ServerLevel world, Operation<FakePlayer> original, @Local(argsOnly = true) PartPos target) {

        BlockEntity te = world.getBlockEntity(target.getPos().getBlockPos());

        if (te instanceof OwnedCraftingTableTileEntity owned) {
            UUID owner = owned.getOwner();

            if (owner != null) {
                GameProfile profile = new GameProfile(owner, "ID Fake Player Profile");

                return new FakePlayer(world, profile);

            }
        }

        return original.call(world);
    }
}
