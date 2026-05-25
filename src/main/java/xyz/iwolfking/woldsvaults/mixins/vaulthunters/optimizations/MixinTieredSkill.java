package xyz.iwolfking.woldsvaults.mixins.vaulthunters.optimizations;

import iskallia.vault.skill.base.SkillContext;
import iskallia.vault.skill.base.TieredSkill;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Throttles the checking of bonus tier application because it really doesn't need to run every tick and is a bit heavier than you'd expect.
@Mixin(value = TieredSkill.class, remap = false)
public class MixinTieredSkill {

    @Unique
    private int woldsvaults$updateDelay = 0;

    @Inject(method = "lambda$onTick$1", at = @At(value = "INVOKE", target = "Liskallia/vault/skill/base/TieredSkill;updateBonusTier(Liskallia/vault/snapshot/AttributeSnapshot;Liskallia/vault/skill/base/SkillContext;)V"), cancellable = true)
    private void throttleBonusTier(SkillContext context, ServerPlayer player, CallbackInfo ci) {
        this.woldsvaults$updateDelay++;
        
        if (this.woldsvaults$updateDelay < 20) {
            ci.cancel(); 
        } else {
            this.woldsvaults$updateDelay = 0;
        }
    }
}