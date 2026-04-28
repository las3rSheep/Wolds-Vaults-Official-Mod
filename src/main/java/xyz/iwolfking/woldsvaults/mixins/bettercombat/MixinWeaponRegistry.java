package xyz.iwolfking.woldsvaults.mixins.bettercombat;

import iskallia.vault.gear.VaultGearState;
import iskallia.vault.gear.data.GearDataCache;
import iskallia.vault.gear.item.IdentifiableItem;
import iskallia.vault.gear.item.VaultGearItem;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "bettercombat")
        }
)
@Mixin(value = WeaponRegistry.class, remap = false)
public class MixinWeaponRegistry {
    @Inject(method = "getAttributes(Lnet/minecraft/world/item/ItemStack;)Lnet/bettercombat/api/WeaponAttributes;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;", shift = At.Shift.AFTER), cancellable = true, remap = true)
    private static void ignoreFallbackForWeaponTypeAttribute(ItemStack itemStack, CallbackInfoReturnable<WeaponAttributes> cir) {
        if(WoldsVaultsConfig.CLIENT.weaponsShouldntBeBetter.get()) {
            cir.setReturnValue(null);
        }

        if(Minecraft.getInstance().player != null) {
            LocalPlayer player = Minecraft.getInstance().player;
            if(player.getCooldowns().isOnCooldown(itemStack.getItem())) {
                cir.setReturnValue(null);
            }

            if(itemStack.getItem() instanceof VaultGearItem vaultGearItem)  {
                if(vaultGearItem.isBroken(itemStack)) {
                    cir.setReturnValue(null);
                }

                if(GearDataCache.of(itemStack).getState() != null && GearDataCache.of(itemStack).getState().equals(VaultGearState.UNIDENTIFIED)) {
                    cir.setReturnValue(null);
                }
            }

        }
    }
}
