package xyz.iwolfking.woldsvaults.mixins.ars_nouveau;

import com.hollingsworth.arsnouveau.common.event.ReactiveEvents;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "ars_nouveau")
        }
)
@Mixin(value = ReactiveEvents.class, remap = false)
public class MixinReactiveEnchantment {
   @Inject(method = "livingHitEvent", at = @At("HEAD"), cancellable = true)
    private static void cancelReactiveInVaults(LivingHurtEvent e, CallbackInfo ci)  {
       if(e.getEntityLiving().level.dimension().location().getNamespace().equals("the_vault")) {
           ci.cancel();
       }
   }

    @Inject(method = "leftClickAir", at = @At("HEAD"), cancellable = true)
    private static void cancelReactiveInVaults(PlayerInteractEvent.LeftClickEmpty e, CallbackInfo ci)  {
        if(e.getEntityLiving().level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }

    @Inject(method = "leftClickBlock", at = @At("HEAD"), cancellable = true)
    private static void cancelReactiveInVaults(PlayerInteractEvent.LeftClickBlock e, CallbackInfo ci)  {
        if(e.getEntityLiving().level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }

    @Inject(method = "playerAttackEntity", at = @At("HEAD"), cancellable = true)
    private static void cancelReactiveInVaults(AttackEntityEvent e, CallbackInfo ci)  {
        if(e.getEntityLiving().level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }

    @Inject(method = "castSpell", at = @At("HEAD"), cancellable = true)
    private static void cancelReactiveInVaults(Player playerIn, ItemStack s, CallbackInfo ci)  {
        if(playerIn.level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }
}
