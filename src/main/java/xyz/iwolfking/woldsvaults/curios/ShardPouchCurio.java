package xyz.iwolfking.woldsvaults.curios;

import iskallia.vault.init.ModItems;
import iskallia.vault.item.ItemShardPouch;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ShardPouchCurio implements ICurio {

    protected ItemStack pouch;

    public ShardPouchCurio(ItemStack pouch) {
        this.pouch = pouch;
    }

    @Override
    public boolean canEquip(SlotContext slotContext) {
        return slotContext.entity() instanceof Player player && CuriosApi.getCuriosHelper().findCurios(player, ModItems.SHARD_POUCH).isEmpty();
    }

    @Override
    public ItemStack getStack() {
        return pouch;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack) {
        ICurio.super.onEquip(slotContext, prevStack);
    }
}
