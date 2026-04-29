package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.item.ItemVaultFruit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.api.util.WoldTexFX;

import java.util.List;

@Mixin(value = ItemVaultFruit.class, remap = false)
public class MixinItemVaultFruitClient {
    @Unique
    @ModifyArg(method = "appendHoverText",
               at = @At(value = "INVOKE",
                        target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                        ordinal = 1)
               )
    public Object changeHPNumber(Object cmp) {
        return new TextComponent("Removes ")
                .withStyle(ChatFormatting.GRAY)
                .append(new TextComponent("10%")
                        .withStyle(ChatFormatting.STRIKETHROUGH))
                .append(WoldTexFX.corruptedEffect(
                        new TextComponent(" 17.3%! ")
                                .withStyle(ChatFormatting.ITALIC)
                                .withStyle(ChatFormatting.BOLD)))
                .append(new TextComponent("max health"))
                .withStyle(ChatFormatting.RED);
    }

    @Inject(method = "appendHoverText",
            at = @At(value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 1,
                    shift = At.Shift.AFTER)
            )
    public void improveFormula(ItemStack itemStack, Level worldIn, List<Component> tooltip, TooltipFlag tooltipFlag, CallbackInfo ci) {
                                           //Removes~10%~ 17.3%! max health
        tooltip.add(new TextComponent("      new and improved formula!")
                .withStyle(ChatFormatting.ITALIC)
                .withStyle(ChatFormatting.DARK_GRAY));
    }
}
