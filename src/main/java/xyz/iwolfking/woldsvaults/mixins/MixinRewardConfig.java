package xyz.iwolfking.woldsvaults.mixins;


import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.bounty.TaskReward;
import iskallia.vault.config.bounty.RewardConfig;
import iskallia.vault.config.entry.LevelEntryMap;
import iskallia.vault.container.oversized.OverSizedItemStack;
import iskallia.vault.init.ModBlocks;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Mixin(value = RewardConfig.class, remap = false)
public class MixinRewardConfig {

    @Shadow protected HashMap<String, LevelEntryMap<RewardConfig.RewardEntry>> POOLS;

    @Inject(method = "generateReward", at = @At("TAIL"))
    public void generateReward(int vaultLevel, String poolId, CallbackInfoReturnable<TaskReward> cir, @Local(ordinal = 1) List<OverSizedItemStack> items) {
            OverSizedItemStack coinStack = woldsVaults$getCoinRewardForLevel(vaultLevel, poolId);
            items.add(coinStack);
    }
    

    @Unique
    private static OverSizedItemStack woldsVaults$getCoinRewardForLevel(int vaultLevel, String poolId) {
        Random random = new Random();
        int coinMult = 1;
        if(poolId.equals("rare")) {
            coinMult = 2;
        }
        else if(poolId.equals("legendary")) {
            coinMult = 4;
        }
        if(vaultLevel >= 0 && vaultLevel <= 10) {
            return new OverSizedItemStack(new ItemStack(ModBlocks.VAULT_GOLD), random.nextInt(coinMult, 3 * coinMult));
        }
        if(vaultLevel >= 11 && vaultLevel <= 20) {
            return new OverSizedItemStack(new ItemStack(ModBlocks.VAULT_GOLD), random.nextInt(2 * coinMult, 4 * coinMult));
        }
        if(vaultLevel >= 21 && vaultLevel <= 35) {
            return new OverSizedItemStack(new ItemStack(ModBlocks.VAULT_GOLD), random.nextInt(2 * coinMult, 4 * coinMult));
        }
        if(vaultLevel >= 36 && vaultLevel <= 50) {
            return new OverSizedItemStack(new ItemStack(ModBlocks.VAULT_GOLD), random.nextInt(2 * coinMult, 5 * coinMult));
        }
        if(vaultLevel >= 51 && vaultLevel <= 75) {
            return new OverSizedItemStack(new ItemStack(ModBlocks.VAULT_GOLD), random.nextInt(3* coinMult, 7 * coinMult));
        }
        if(vaultLevel >= 76) {
            return new OverSizedItemStack(new ItemStack(ModBlocks.VAULT_GOLD), random.nextInt(4 * coinMult, 10 * coinMult));
        }
        return OverSizedItemStack.EMPTY;
    }
}
