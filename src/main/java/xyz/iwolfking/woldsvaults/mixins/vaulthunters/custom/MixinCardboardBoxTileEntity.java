package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.block.entity.CardboardBoxTileEntity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CardboardBoxTileEntity.class, remap = false)
public class MixinCardboardBoxTileEntity {
    @Redirect(method = "generateLoot", at = @At(value = "INVOKE", target = "Liskallia/vault/util/calc/ItemQuantityHelper;getItemQuantity(Lnet/minecraft/world/entity/LivingEntity;)F"))
    private float noItemQuantityForBoxes(LivingEntity entity) {
        return 0;
    }
}
