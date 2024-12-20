package xyz.iwolfking.woldsvaults.items.gear;

import com.google.common.collect.Multimap;
import iskallia.vault.gear.VaultGearClassification;
import iskallia.vault.gear.VaultGearHelper;
import iskallia.vault.gear.VaultGearType;
import iskallia.vault.gear.crafting.ProficiencyType;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.gear.tooltip.GearTooltip;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.item.BasicItem;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class VaultMapItem extends BasicItem implements VaultGearItem {


    public VaultMapItem(ResourceLocation id, Properties properties) {
        super(id, properties);
    }

    @NotNull
    @Override
    public VaultGearClassification getClassification(ItemStack itemStack) {
        return VaultGearClassification.valueOf("MAP");
    }

    @NotNull
    @Override
    public ProficiencyType getCraftingProficiencyType(ItemStack itemStack) {
        return ProficiencyType.UNKNOWN;
    }

    @NotNull
    @Override
    public VaultGearType getGearType(ItemStack itemStack) {
        return VaultGearType.JEWEL;
    }

    @Nullable
    @Override
    public ResourceLocation getRandomModel(ItemStack stack, Random random) {
        VaultGearData gearData = VaultGearData.read(stack);
        EquipmentSlot intendedSlot = this.getEquipmentSlot(stack);
        return ModConfigs.GEAR_MODEL_ROLL_RARITIES.getRandomRoll(stack, gearData, intendedSlot, random);
    }

    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            items.add(this.defaultItem());
        }

    }

    public int getDefaultTooltipHideFlags(@NotNull ItemStack stack) {
        return super.getDefaultTooltipHideFlags(stack) | ItemStack.TooltipPart.MODIFIERS.getMask();
    }

    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return VaultGearHelper.getModifiers(stack, slot);
    }

    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return VaultGearHelper.shouldPlayGearReequipAnimation(oldStack, newStack, slotChanged);
    }

    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    public int getMaxDamage(ItemStack stack) {
        return 1;
    }

    public Component getName(ItemStack stack) {
        return VaultGearHelper.getDisplayName(stack, super.getName(stack));
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        return VaultGearHelper.rightClick(world, player, hand, super.use(world, player, hand));
    }

    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, world, entity, itemSlot, isSelected);
        if (entity instanceof ServerPlayer player) {
            this.vaultGearTick(stack, player);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.addAll(this.createTooltip(stack, GearTooltip.itemTooltip()));
    }
}