package xyz.iwolfking.woldsvaults.mixins;


import iskallia.vault.world.data.ServerVaults;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.data.WoldsVaultPlayerData;

@Mixin(value = LivingEntity.class, priority = 9999)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }
    @Inject(
            method = {"checkTotemDeathProtection"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void checkTotemDeathProtection(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (ServerVaults.get(this.level).isPresent()) {
            if(WoldsVaultPlayerData.totemVaultDataMap.containsKey(this.getUUID())) {
                if(WoldsVaultPlayerData.totemVaultDataMap.get(this.getUUID()) > 0) {
                    cir.setReturnValue(false);
                }
                else {
                    WoldsVaultPlayerData.totemVaultDataMap.put(this.getUUID(), 1);
                    cir.setReturnValue(true);
                }
            }
            else {
                WoldsVaultPlayerData.totemVaultDataMap.put(this.getUUID(), 1);
                cir.setReturnValue(true);
            }
        }
    }
}
