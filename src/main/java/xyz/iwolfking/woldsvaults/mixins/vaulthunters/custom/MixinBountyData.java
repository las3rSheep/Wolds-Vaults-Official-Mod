package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import iskallia.vault.bounty.Bounty;
import iskallia.vault.bounty.TaskRegistry;
import iskallia.vault.bounty.TaskReward;
import iskallia.vault.bounty.task.properties.TaskProperties;
import iskallia.vault.config.bounty.task.TaskConfig;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.world.data.BountyData;
import iskallia.vault.world.data.PlayerVaultStatsData;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.api.util.ServerPrestigePowerHelper;
import xyz.iwolfking.woldsvaults.prestige.LegendaryBountyPower;

import java.util.UUID;

@Mixin(value = BountyData.class, remap = false)
public abstract class MixinBountyData {

    @Shadow
    protected abstract Bounty generateLegendaryBounty(UUID playerId);

    @Inject(method = "generateBounty", at = @At("HEAD"), cancellable = true)
    private void createLegendaryBountyInstead(UUID playerId, CallbackInfoReturnable<Bounty> cir) {
        if(!ServerPrestigePowerHelper.getPrestigePowersOfType(playerId, LegendaryBountyPower.class).isEmpty()) {
            cir.setReturnValue(generateLegendaryBounty(playerId));
        }
    }

    @WrapOperation(method = "setupLegendary", at = @At(value = "INVOKE", target = "Liskallia/vault/world/data/BountyData;generateLegendaryBounty(Ljava/util/UUID;)Liskallia/vault/bounty/Bounty;"))
    private Bounty createMythicBountyForPrestige(BountyData instance, UUID playerId, Operation<Bounty> original) {
        if(!ServerPrestigePowerHelper.getPrestigePowersOfType(playerId, LegendaryBountyPower.class).isEmpty()) {
            return woldsVaults$generateMythicBounty(playerId);
        }

        return original.call(instance, playerId);
    }

    @Unique
    private Bounty woldsVaults$generateMythicBounty(UUID playerId) {
        int vaultLevel = PlayerVaultStatsData.get(ServerLifecycleHooks.getCurrentServer()).getVaultStats(playerId).getVaultLevel();
        ResourceLocation taskId = ModConfigs.BOUNTY_CONFIG.getRandomTask();
        TaskConfig<?, ?> config = TaskConfig.getConfig(taskId);
        TaskProperties properties = config.getGeneratedTaskProperties(vaultLevel);
        properties.setRewardPool("mythic");
        TaskReward reward = ModConfigs.REWARD_CONFIG.generateReward(vaultLevel, properties.getRewardPool());
        UUID bountyId = UUID.randomUUID();
        return new Bounty(bountyId, playerId, TaskRegistry.createTask(taskId, bountyId, properties, reward));
    }
}
