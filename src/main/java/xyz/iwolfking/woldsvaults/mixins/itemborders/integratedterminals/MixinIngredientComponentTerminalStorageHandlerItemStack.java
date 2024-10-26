package xyz.iwolfking.woldsvaults.mixins.itemborders.integratedterminals;

import com.anthonyhilyard.itemborders.ItemBorders;
import com.mojang.blaze3d.vertex.PoseStack;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.cyclops.integratedterminals.capability.ingredient.IngredientComponentTerminalStorageHandlerItemStack;
import org.cyclops.integratedterminals.client.gui.container.ContainerScreenTerminalStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "itemborders"),
                @Condition(type = Condition.Type.MOD, value = "integratedterminals")
        }
)
@Mixin(value = IngredientComponentTerminalStorageHandlerItemStack.class, remap = false)
public class MixinIngredientComponentTerminalStorageHandlerItemStack {
    @OnlyIn(Dist.CLIENT)
    @Inject(method = "drawInstance(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemStack;JLjava/lang/String;Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;Lorg/cyclops/integratedterminals/client/gui/container/ContainerScreenTerminalStorage$DrawLayer;FIIIILjava/util/List;)V", at = @At("TAIL"))
    public void drawInstance(PoseStack matrixStack, ItemStack instance, long maxQuantity, String label, AbstractContainerScreen gui, ContainerScreenTerminalStorage.DrawLayer layer, float partialTick, int x, int y, int mouseX, int mouseY, List<Component> additionalTooltipLines, CallbackInfo ci) {
        ItemBorders.renderBorder(matrixStack, instance, x, y);
    }
}
