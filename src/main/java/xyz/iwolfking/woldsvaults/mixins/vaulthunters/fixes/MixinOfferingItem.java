package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.init.ModConfigs;
import iskallia.vault.item.BasicItem;
import iskallia.vault.item.OfferingItem;
import iskallia.vault.item.tool.IManualModelLoading;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

@Mixin(value = OfferingItem.class, remap = false)
public abstract class MixinOfferingItem extends BasicItem implements IManualModelLoading {

    public MixinOfferingItem(ResourceLocation id) {
        super(id);
    }

    @Override
    public Component getName(@NotNull ItemStack stack) {
        if (ModConfigs.OFFERING_BOSS == null) {
            return super.getName(stack);
        } else {
            MutableComponent name = super.getName(stack).copy();

            int color = Color.DARK_GRAY.getRGB();
            return stack.hasTag() && stack.getOrCreateTag().contains("rotten")
                    ? new TextComponent("Rotten ")
                    .withStyle(Style.EMPTY.withColor(TextColor.parseColor("#00680A")))
                    .append(name.withStyle(Style.EMPTY.withColor(color)))
                    : name.withStyle(Style.EMPTY.withColor(54563));
        }
    }


    /**
     * @author
     * @reason
     */
    @Inject(method = "appendHoverText", at = @At("TAIL"), remap = true)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn, CallbackInfo ci) {
        if (stack.hasTag() && stack.getOrCreateTag().contains("rotten")) {
            tooltip.add(Mth.clamp(tooltip.size(), 0, tooltip.size() - 2), (new TextComponent("Rotten items can not be used in the vault")).withStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
        }

    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void loadModels(Consumer<ModelResourceLocation> consumer) {
        consumer.accept(new ModelResourceLocation("the_vault:offering/light_melee#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/heavy_melee#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/light_ranged#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/heavy_ranged#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/aoe_attack#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/attribute_dmg#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/attribute_health#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/on-hit_effect#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/buff_effect#inventory"));
        consumer.accept(new ModelResourceLocation("the_vault:offering/none#inventory"));
    }
}
