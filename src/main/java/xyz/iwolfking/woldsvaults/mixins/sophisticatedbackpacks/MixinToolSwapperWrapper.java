package xyz.iwolfking.woldsvaults.mixins.sophisticatedbackpacks;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper.ToolSwapperUpgradeWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "sophisticatedbackpacks")
        }
)
@Mixin(value = ToolSwapperUpgradeWrapper.class, remap = false)
public abstract class MixinToolSwapperWrapper {
    /**
     * @author iwolfking
     * @reason Disable Tool Swapper in vaults.
     */
    @Inject(method = "onBlockClick", at = @At("HEAD"), cancellable = true)
    public void onBlockClickInVault(Player player, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(player.getLevel().dimension().location().getNamespace().equals("the_vault")) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onAttackEntity", at = @At("HEAD"), cancellable = true)
    public void onAttackEntityInVault(Player player, CallbackInfoReturnable<Boolean> cir) {
        if(player.getLevel().dimension().location().getNamespace().equals("the_vault")) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onBlockInteract", at = @At("HEAD"), cancellable = true)
    public void onBlockInteractInVault(Level world, BlockPos pos, BlockState blockState, Player player, CallbackInfoReturnable<Boolean> cir) {
        if(player.getLevel().dimension().location().getNamespace().equals("the_vault")) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onEntityInteract", at = @At("HEAD"), cancellable = true)
    public void onEntityInteractInVault(Level world, Entity entity, Player player, CallbackInfoReturnable<Boolean> cir) {
        if(player.getLevel().dimension().location().getNamespace().equals("the_vault")) {
            cir.setReturnValue(false);
        }
    }


}
