package xyz.iwolfking.woldsvaults.mixins.vaulthunters.testing;

import iskallia.vault.core.vault.Vault;
import iskallia.vault.world.data.ServerVaults;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ServerVaults.class, remap = false)
public abstract class MixinServerVaults {


    @Redirect(method = "add", at = @At(value = "INVOKE", target = "Liskallia/vault/world/data/VaultSnapshots;onVaultStarted(Liskallia/vault/core/vault/Vault;)V"))
    private static void add(Vault vault) {

    }
}
