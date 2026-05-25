package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.network.message.PrestigePowerLevelMessage;
import iskallia.vault.skill.base.LearnableSkill;
import iskallia.vault.skill.base.Skill;
import iskallia.vault.skill.base.SkillContext;
import iskallia.vault.skill.base.TieredSkill;
import iskallia.vault.skill.prestige.core.PrestigePower;
import iskallia.vault.skill.tree.PrestigeTree;
import iskallia.vault.world.data.PlayerPrestigePowersData;
import iskallia.vault.world.data.PlayerVaultStatsData;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.iwolfking.woldsvaults.prestige.CraftingRecipePower;

@Mixin(value = PrestigePowerLevelMessage.class, remap = false)
public class MixinPrestigePowerLevelMessage {

    @Inject(
        method = "lambda$upgradePrestigePower$1",
        at = @At(value = "INVOKE", target = "Liskallia/vault/skill/base/TieredSkill;learn(Liskallia/vault/skill/base/SkillContext;)V", shift = At.Shift.AFTER),
        locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private static void triggerCustomLearnLogic(ServerPlayer player, PlayerVaultStatsData statsData, PlayerPrestigePowersData prestigePowers, PrestigeTree prestigeTree, Skill skill, CallbackInfo ci, SkillContext context, TieredSkill tiered, LearnableSkill next, PrestigePower power) {
        if(power instanceof CraftingRecipePower) {
            power.learn(context);
        }
    }
}