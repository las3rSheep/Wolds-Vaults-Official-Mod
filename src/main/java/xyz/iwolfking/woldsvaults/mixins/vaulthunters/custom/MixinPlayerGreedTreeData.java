package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.greed.GreedQuestSlot;
import iskallia.vault.greed.GreedTree;
import iskallia.vault.world.data.PlayerGreedTreeData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerGreedTreeData.class, remap = false)
public abstract class MixinPlayerGreedTreeData {

    @Shadow
    public abstract void refreshQuests(ServerPlayer player);

    @Inject(method = "completeQuest", at = @At(value = "INVOKE", target = "Liskallia/vault/world/data/PlayerGreedTreeData;syncQuestData(Lnet/minecraft/server/level/ServerPlayer;)V"))
    private void repopulateQuestsIfEmpty(ServerPlayer player, int slotIndex, CallbackInfo ci) {
        GreedTree greedTree = this.getGreedTree(player);
        boolean allQuestsCompleted = greedTree.getQuestSlots().stream().allMatch(GreedQuestSlot::isCompleted);
        if(allQuestsCompleted) {
            refreshQuests(player);
        }
    }

    @Shadow
    public abstract GreedTree getGreedTree(Player player);
}
