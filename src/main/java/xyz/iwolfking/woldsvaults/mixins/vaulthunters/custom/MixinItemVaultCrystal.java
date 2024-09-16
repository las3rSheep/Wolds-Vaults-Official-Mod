package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.item.crystal.VaultCrystalItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(value = VaultCrystalItem.class, remap = false)
public class MixinItemVaultCrystal {
    @Inject(method = "loadModels", at = @At("TAIL"))
    public void loadModels(Consumer<ModelResourceLocation> consumer, CallbackInfo ci) {
        consumer.accept(new ModelResourceLocation("the_vault:crystal/core/unhinged#inventory"));
    }
}
