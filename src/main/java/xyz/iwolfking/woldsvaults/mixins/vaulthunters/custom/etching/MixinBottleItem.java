package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom.etching;

import iskallia.vault.init.ModConfigs;
import iskallia.vault.item.bottle.BottleItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.api.util.WoldEtchingHelper;
import xyz.iwolfking.woldsvaults.init.ModEtchingGearAttributes;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.BottleEffectAccessor;

@Mixin(value = BottleItem.class, remap = false)
public class MixinBottleItem {
    @Inject(method = "consumeCharge", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;putInt(Ljava/lang/String;I)V"))
    private static void prudentChaosEtching(ItemStack stack, ServerPlayer player, CallbackInfo ci) {
        if(WoldEtchingHelper.hasEtching(player, ModEtchingGearAttributes.PRUDENT_CHAOS)) {
            int effectNum = player.getRandom().nextInt(0, ModConfigs.VAULT_ALCHEMY_TABLE.getCraftableEffects().size());
            ModConfigs.VAULT_ALCHEMY_TABLE.getCraftableEffects().get(effectNum).createEffect(BottleItem.Type.BREW).ifPresent(effect -> ((BottleEffectAccessor)effect).callTrigger(player));
        }
    }
}
