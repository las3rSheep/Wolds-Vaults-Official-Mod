package xyz.iwolfking.woldsvaults.mixins.vaulthunters.testing;

import iskallia.vault.core.event.Event;
import iskallia.vault.core.event.common.PlayerMineEvent;
import iskallia.vault.event.PlayerEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(value = PlayerEvents.class, remap = false)
public class MixinPlayerEvents {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Liskallia/vault/core/event/common/PlayerMineEvent;register(Ljava/lang/Object;Ljava/util/function/Consumer;)Liskallia/vault/core/event/Event;"))
    private static Event cancelMineEvent(PlayerMineEvent instance, Object o, Consumer consumer) {
        return null;
    }
}
