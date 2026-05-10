package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.client.gui.screen.player.legacy.tab.split.dialog.AbilityDialog;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.time.TickClock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(value = AbilityDialog.class, remap = false)
public class MixinAbilityDialog {

    @Redirect(
        method = "update",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Optional;isPresent()Z",
            ordinal = 0
        )
    )
    private boolean checkVaultPausedOne(Optional<Vault> optional) {
        return woldsVaults$forceActiveWhilePaused(optional);
    }

    @Redirect(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Optional;isPresent()Z",
                    ordinal = 1
            )
    )
    private boolean checkVaultPausedTwo(Optional<Vault> optional) {
        return woldsVaults$forceActiveWhilePaused(optional);
    }

    @Unique
    private boolean woldsVaults$forceActiveWhilePaused(Optional<Vault> optional) {
        if (optional.isPresent()) {
            boolean isPaused = optional.get().getOptional(Vault.CLOCK)
                    .map(clock -> clock.has(TickClock.PAUSED))
                    .orElse(false);
            if (isPaused) return false;
        }
        return optional.isPresent();
    }
}