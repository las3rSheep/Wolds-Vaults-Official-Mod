package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import iskallia.vault.VaultMod;
import iskallia.vault.config.ChaosConfig;
import iskallia.vault.core.random.RandomSource;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.item.crystal.objective.ChaosCrystalObjective;
import iskallia.vault.task.ChaosTask;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = ChaosCrystalObjective.class, remap = false)
public class MixinChaosCrystalObjective {

    @Unique
    private static final ThreadLocal<String> woldsVaults$SIGIL = new ThreadLocal<>();

    @WrapOperation(method = "lambda$configure$1", at = @At(value = "INVOKE", target = "Liskallia/vault/config/ChaosConfig;generate(Lnet/minecraft/resources/ResourceLocation;I)Ljava/util/Optional;"))
    private Optional<ChaosTask> changeChaosTaskPool(ChaosConfig instance, ResourceLocation pool, int level, Operation<Optional<ChaosTask>> original)
    {
        if(woldsVaults$SIGIL.get().equals("adept")) {
            return instance.generate(VaultMod.id("big_chaos"), level);
        }

        return original.call(instance, pool, level);
    }

    @Inject(method = "configure", at = @At("HEAD"))
    private void captureSigil(Vault vault, RandomSource random, String sigil, CallbackInfo ci) {
        woldsVaults$SIGIL.set(sigil);
    }

    @Inject(method = "configure", at = @At("RETURN"))
    private void clearSigil(Vault vault, RandomSource random, String sigil, CallbackInfo ci) {
        woldsVaults$SIGIL.remove();
    }
}