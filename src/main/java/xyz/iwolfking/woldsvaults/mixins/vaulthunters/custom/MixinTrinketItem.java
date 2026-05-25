package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.gear.item.IdentifiableItem;
import iskallia.vault.gear.trinket.TrinketEffect;
import iskallia.vault.init.ModItems;
import iskallia.vault.item.BasicItem;
import iskallia.vault.item.core.DataTransferItem;
import iskallia.vault.item.gear.RecyclableItem;
import iskallia.vault.item.gear.TrinketItem;
import iskallia.vault.item.gear.VaultUsesHelper;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import xyz.iwolfking.woldsvaults.items.CombinedTrinketItem;

import java.util.List;
import java.util.Optional;

@Mixin(value = TrinketItem.class, remap = false)
public abstract class MixinTrinketItem extends BasicItem implements ICurioItem, DataTransferItem, RecyclableItem, IdentifiableItem {
    public MixinTrinketItem(ResourceLocation id) {
        super(id);
    }

    /**
     * @author iwolfking
     * @reason Prevent equipping multiple of the same trinket.
     */
    @Inject(method = "canEquip", at = @At("HEAD"), cancellable = true)
    public void canEquip(SlotContext slotContext, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (slotContext.entity() instanceof Player player) {
            LazyOptional<IItemHandlerModifiable> curioHandlerOpt = CuriosApi.getCuriosHelper().getEquippedCurios(player);
            if(curioHandlerOpt.isPresent()) {
                IItemHandlerModifiable curioHandler = curioHandlerOpt.resolve().orElse(null);
                if(curioHandler != null) {
                    for(int i = 0; i < curioHandler.getSlots(); i++) {
                        ItemStack curioStack = curioHandler.getStackInSlot(i);
                        if(curioStack.getItem() instanceof CombinedTrinketItem) {
                            List<TrinketEffect<?>> curioTrinketEffects = CombinedTrinketItem.getTrinkets(curioStack);
                            if(stack.getItem().equals(ModItems.TRINKET)) {
                                TrinketEffect<?> effect = TrinketItem.getTrinket(stack).orElse(null);
                                if(effect != null) {
                                    if(curioTrinketEffects.contains(effect)) {
                                        cir.setReturnValue(false);
                                        return;
                                    }
                                }
                            }
                            else if(stack.getItem() instanceof CombinedTrinketItem) {
                                List<TrinketEffect<?>> effects = CombinedTrinketItem.getTrinkets(stack);
                                if(effects != null && !effects.isEmpty()) {
                                    for(TrinketEffect<?> effect : effects) {
                                        if(curioTrinketEffects.contains(effect)) {
                                            cir.setReturnValue(false);
                                            return;
                                        }
                                    }
                                }
                            }
                        }

                        else if(curioStack.getItem() instanceof TrinketItem) {
                         TrinketEffect<?> effect = TrinketItem.getTrinket(curioStack).orElse(null);
                         if(stack.getItem() instanceof CombinedTrinketItem) {
                             List<TrinketEffect<?>> effects = CombinedTrinketItem.getTrinkets(stack);
                            if(effects != null && !effects.isEmpty() && effects.contains(effect)) {
                                cir.setReturnValue(false);
                                return;
                            }
                         }
                         else if(stack.getItem().equals(ModItems.TRINKET) && effect != null && effect.equals(TrinketItem.getTrinket(stack).orElse(null))) {
                             cir.setReturnValue(false);
                         }
                        }
                    }
                }
            }
        }
    }
}
