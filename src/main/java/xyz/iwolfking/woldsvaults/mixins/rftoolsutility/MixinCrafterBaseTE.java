package xyz.iwolfking.woldsvaults.mixins.rftoolsutility;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.research.Restrictions;
import iskallia.vault.world.data.PlayerResearchesData;
import mcjty.lib.tileentity.TickingTileEntity;
import mcjty.rftoolsbase.api.compat.JEIRecipeAcceptor;
import mcjty.rftoolsutility.modules.crafter.blocks.CrafterBaseTE;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "rftoolsutility")
        }
)
@Mixin(value = CrafterBaseTE.class, remap = false)
public abstract class MixinCrafterBaseTE extends TickingTileEntity implements JEIRecipeAcceptor {
    public MixinCrafterBaseTE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "craftOneItem", at = @At(value = "INVOKE", target = "Lmcjty/lib/container/UndoableItemHandler;<init>(Lnet/minecraftforge/items/IItemHandlerModifiable;)V"), cancellable = true)
    private void cancelRecipeIfLocked(mcjty.rftoolsutility.modules.crafter.data.CraftingRecipe craftingRecipe, CallbackInfoReturnable<Boolean> cir, @Local(name = "recipe") Recipe<?> recipe) {
        if(getOwnerUUID() == null) {
            cir.setReturnValue(false);
            return;
        }

        if(PlayerResearchesData.get((ServerLevel) this.level).getResearches(getOwnerUUID()).restrictedBy(recipe.getResultItem(), Restrictions.Type.CRAFTABILITY) != null) {
            cir.setReturnValue(false);
        }
    }

}
