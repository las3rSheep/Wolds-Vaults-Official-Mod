package xyz.iwolfking.woldsvaults.mixins.ftbessentials;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.command.TeleportCommands;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.vhapi.api.util.MessageUtils;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "ftbessentials"),
        }
)
@Mixin(value = TeleportCommands.class, remap = false)
public class MixinFTBBackCommand {
    @Inject(method = "back", at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbessentials/util/FTBEPlayerData;get(Lnet/minecraft/world/entity/player/Player;)Ldev/ftb/mods/ftbessentials/util/FTBEPlayerData;", shift = At.Shift.AFTER))
    private static void preventBackingIntoVault(ServerPlayer player, CallbackInfoReturnable<Integer> cir) {
        FTBEPlayerData data = FTBEPlayerData.get(player);
        if(data != null && !data.teleportHistory.isEmpty() && data.teleportHistory.getLast().dimension.getRegistryName().getNamespace().equals("the_vault")) {
            MessageUtils.sendMessage(player, new TranslatableComponent("command.woldsvaults.prevent_back_into_vault"));
            cir.setReturnValue(0);
        }
    }
}
