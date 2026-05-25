package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import iskallia.vault.block.VaultChestBlock;
import iskallia.vault.block.entity.VaultChestTileEntity;
import iskallia.vault.skill.ability.effect.spi.AbstractEarthquakeAbility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = AbstractEarthquakeAbility.class, remap = false)
public class MixinAbstractEarthquakeAbility {
    @WrapOperation(method = "breakChests", at = @At(value = "INVOKE", target = "Liskallia/vault/block/entity/VaultChestTileEntity;isVaultChest()Z"))
    private boolean checkIfChestIsLocked(VaultChestTileEntity instance, Operation<Boolean> original) {
        if(instance.getBlockState().getBlock() instanceof VaultChestBlock vaultChestBlock) {
            if(vaultChestBlock.isLocked()) {
                return false;
            }
        }

        return original.call(instance);
    }
}
