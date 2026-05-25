package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.skill.ability.effect.spi.AbstractSmiteAbility;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;

@Mixin(AbstractSmiteAbility.class)
public class MixinAbstractSmiteAbility {
    @Redirect(method = "doDamage",
              at = @At(value = "INVOKE",
                       target = "Ljava/util/ArrayList;remove(Ljava/lang/Object;)Z"),
              remap = false)
    private boolean dontSmitePlayers(ArrayList<LivingEntity> array, Object x) {
        return array.removeIf((entity) -> entity instanceof ServerPlayer);
    }
}
