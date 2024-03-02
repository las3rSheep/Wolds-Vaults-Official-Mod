package xyz.iwolfking.woldsvaults.mixins;

import cofh.ensorcellation.init.EnsorcEnchantments;
import io.github.lightman314.lightmanscurrency.common.items.WalletItem;
import iskallia.vault.container.SpiritExtractorContainer;
import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.gear.data.AttributeGearData;
import iskallia.vault.init.ModGearAttributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SpiritExtractorContainer.class)
public class MixinSpiritExtractor {
    /**
     * @author iwolfking
     * @reason Remove wallets from spirit extractor
     */
    @Overwrite(remap = false)
    private static boolean shouldAddItem(ItemStack stack) {
        return !stack.isEmpty() && (!AttributeGearData.hasData(stack) || !(Boolean) AttributeGearData.read(stack).get(ModGearAttributes.SOULBOUND, VaultGearAttributeTypeMerger.anyTrue()) && !(stack.getItem() instanceof WalletItem) && !(EnchantmentHelper.getEnchantments(stack).containsKey(EnsorcEnchantments.SOULBOUND.get())));
    }
}
