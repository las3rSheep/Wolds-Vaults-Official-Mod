package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.greed.ChallengeState;
import iskallia.vault.greed.GreedChallengeSlot;
import iskallia.vault.greed.GreedTree;
import iskallia.vault.init.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Set;

@Mixin(value = GreedTree.class, remap = false)
public abstract class MixinGreedTree {
    @Shadow
    protected List<GreedChallengeSlot> challengeSlots;
    @Shadow
    protected int greedTier;
    @Shadow
    protected boolean challengesPopulated;

    @Shadow
    public abstract void populateChallenges(int greedTier);

    @Shadow
    protected Set<String> completedChallengeIds;

    @Shadow
    protected List<GreedChallengeSlot> challengeHistory;

    /**
     * @author iwolfking
     * @reason Completing and abandoning challenges can refresh an additional challenge to complete *before* GT13, completing all challenges grants access to do all challenges again
     */
    @Overwrite
    public void checkChallengesCycleReset() {
        if (!this.challengeSlots.isEmpty()) {
            for (GreedChallengeSlot slot : this.challengeSlots) {
                if (slot.isAvailable() || slot.isAttempted()) {
                    return;
                }
            }

            this.challengeSlots.clear();
            if (this.greedTier >= 1) {
                this.challengesPopulated = false;
                this.populateChallenges(this.greedTier);
            }

            challengeHistory.removeIf(GreedChallengeSlot::isSkipped);

            if(completedChallengeIds.size() == ModConfigs.GREED_TRADER.getChallenges().size()) {
                this.challengeHistory.clear();
                this.challengeSlots.clear();
                this.completedChallengeIds.clear();
                this.challengesPopulated = false;
                this.populateChallenges(this.greedTier);
            }

        }
    }
}
