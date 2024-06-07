package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import cofh.ensorcellation.init.EnsorcEnchantments;
import io.github.lightman314.lightmanscurrency.common.items.WalletItem;
import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.gear.data.AttributeGearData;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.world.data.SoulboundSnapshotData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SoulboundSnapshotData.class)
public class MixinSoulboundSnapshot {
    /**
     * @author iwolfking
     * @reason Add wallets to soulbound snapshot
     */
    @Overwrite(remap = false)
    protected boolean shouldSnapshotItem(Player player, ItemStack stack) {
        return !stack.isEmpty() && (((Boolean) AttributeGearData.read(stack).get(ModGearAttributes.SOULBOUND, VaultGearAttributeTypeMerger.anyTrue()) || stack.getItem() instanceof WalletItem || EnchantmentHelper.getEnchantments(stack).containsKey(EnsorcEnchantments.SOULBOUND.get())));
    }
}
