package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.config.VaultChestConfig;
import iskallia.vault.world.vault.chest.VaultChestEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.api.data.traps.WoldsChestTraps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(value = VaultChestConfig.class, remap = false)
public class MixinVaultChestConfig {

    @Inject(method = "getAll", at = @At("RETURN"), cancellable = true)
    private void appendCustomTraps(CallbackInfoReturnable<List<VaultChestEffect>> cir) {
        List<VaultChestEffect> originalList = cir.getReturnValue();

        List<VaultChestEffect> customTraps = Stream.of(
                WoldsChestTraps.MOB_BUFF_TRAP_EFFECTS,
                WoldsChestTraps.SHOCKWAVE_TRAP_EFFECTS
        )
        .flatMap(Collection::stream)
        .collect(Collectors.toUnmodifiableList());

        originalList.addAll(customTraps);
        cir.setReturnValue(originalList);
    }
}