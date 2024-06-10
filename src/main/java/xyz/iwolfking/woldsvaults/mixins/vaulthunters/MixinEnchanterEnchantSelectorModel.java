package xyz.iwolfking.woldsvaults.mixins.vaulthunters;


import iskallia.vault.client.gui.framework.element.EnchanterEnchantSelectorElement;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.tool.ToolItem;
import iskallia.vault.util.EnchantmentEntry;
import iskallia.vault.util.function.ObservableSupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.data.BannedEnchantmentsData;

import java.util.*;
import java.util.function.Supplier;

@Mixin(value = EnchanterEnchantSelectorElement.EnchanterEnchantSelectorModel.class, remap = false)
public abstract class MixinEnchanterEnchantSelectorModel {
    @Shadow @Final private ObservableSupplier<ItemStack> inputSupplier;
    @Shadow @Final private Supplier<String> searchFilter;

    @Shadow
    public static boolean canCraft(ItemStack gearStack, EnchantmentEntry config) {
        return false;
    }

    /**
     * @author iwolfking
     * @reason Remove ensorcellation vanilla enchants from Vault Enchanter
     */
    @Overwrite
    public List<EnchantmentEntry> getEntries() {
        ItemStack input = (ItemStack) this.inputSupplier.get();
        if (input.isEmpty()) {
            return Collections.emptyList();
        } else {
            Player player = Minecraft.getInstance().player;
            if (player == null) {
                return Collections.emptyList();
            } else {
                String searchTerm = ((String) this.searchFilter.get()).toLowerCase(Locale.ROOT);
                Map<Enchantment, Integer> currentEnchantments = EnchantmentHelper.getEnchantments(input);
                List<EnchantmentEntry> out = new ArrayList();
                Iterator var6 = ForgeRegistries.ENCHANTMENTS.iterator();

                while (var6.hasNext()) {
                    Enchantment enchantment = (Enchantment) var6.next();
                    if (!enchantment.isCurse() && enchantment.canEnchant(input)) {
                        if(input.getItem() instanceof VaultGearItem || input.getItem() instanceof ToolItem || input.getItem().getRegistryName().toString().equals("the_vault:tool")) {
                            if(BannedEnchantmentsData.BANNED_ENCHANT_REGISTRY_NAMES.contains(enchantment.getRegistryName().toString())) {
                                continue;
                            }
                        }
                        else {
                            if(BannedEnchantmentsData.BANNED_ENCHANT_REGISTRY_NAMES.contains(enchantment.getRegistryName().toString()) && !enchantment.getRegistryName().toString().equals("minecraft:mending")) {
                                continue;
                            }
                        }
                        String enchantName = (new TranslatableComponent(enchantment.getDescriptionId())).getString();
                        if (enchantName.toLowerCase(Locale.ROOT).contains(searchTerm)) {
                            out.add(new EnchantmentEntry(enchantment, enchantment.getMaxLevel()));
                        }
                    }
                }

                Map<EnchantmentEntry, Boolean> canCraftLookup = new HashMap();
                out.forEach((enchantmentEntry) -> {
                    canCraftLookup.put(enchantmentEntry, canCraft(input, enchantmentEntry));
                });
                Map<EnchantmentEntry, Boolean> alreadyHasLookup = new HashMap();
                out.forEach((enchantmentEntry) -> {
                    alreadyHasLookup.put(enchantmentEntry, (Integer) currentEnchantments.getOrDefault(enchantmentEntry.getEnchantment(), 0) >= enchantmentEntry.getLevel());
                });
                out.sort(Comparator.comparing((o) -> {
                    return o.getEnchantment().getRegistryName().toString();
                }));
                out.sort((c1, c2) -> {
                    return -Boolean.compare((Boolean) canCraftLookup.get(c1), (Boolean) canCraftLookup.get(c2));
                });
                out.sort((c1, c2) -> {
                    return Boolean.compare((Boolean) alreadyHasLookup.get(c1), (Boolean) alreadyHasLookup.get(c2));
                });
                return out;
            }
        }
    }
}
