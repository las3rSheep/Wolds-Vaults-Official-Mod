package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.client.gui.screen.block.VaultForgeScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = VaultForgeScreen.class, remap = false)
public class MixinVaultForgeScreen
{
    @Redirect(method = "lambda$new$5", at = @At(value = "INVOKE", target = "Liskallia/vault/client/data/ClientProficiencyData;getProficiency()I"))
    private int temporaryFix() {
        return 0;
    }
}
