package xyz.iwolfking.woldsvaults.mixins.alexsmobs;

import com.github.alexthe666.alexsmobs.entity.EntityCosmaw;
import iskallia.vault.world.data.ServerVaults;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "alexsmobs")
        }
)
@Mixin(EntityCosmaw.class)
public abstract class MixinEntityCosmaw {

    @Inject(method = "registerGoals", at = @At("TAIL"), remap = true)
    private void onRegisterGoals(CallbackInfo ci) {
        EntityCosmaw cosmaw = (EntityCosmaw) (Object) this;

        cosmaw.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(cosmaw, Player.class, 10, true, false, (livingEntity) -> ServerVaults.get(cosmaw.level).isPresent()));
    }
}