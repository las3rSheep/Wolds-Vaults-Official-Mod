package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.core.Version;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultFactory;
import iskallia.vault.item.crystal.CrystalData;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = VaultFactory.class, remap = false)
public class MixinVaultFactory {
    @Inject(method = "create", at = @At(value = "INVOKE", target = "Liskallia/vault/item/crystal/CrystalData;configure(Liskallia/vault/core/vault/Vault;Liskallia/vault/core/random/RandomSource;)V"))
    private static void create(Version version, CrystalData crystal, CallbackInfoReturnable<Vault> cir, @Local Vault vault) {
        if(vault != null) {
            Optional<CompoundTag> crystalTag = crystal.writeNbt();
            crystalTag.ifPresent(compoundTag -> vault.set(Vault.CRYSTAL, compoundTag));
        }
    }
}
