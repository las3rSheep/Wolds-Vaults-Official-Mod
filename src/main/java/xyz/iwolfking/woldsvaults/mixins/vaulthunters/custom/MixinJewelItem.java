package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.config.VaultRecyclerConfig;
import iskallia.vault.config.entry.ChanceItemStackEntry;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.init.ModConfigs;
import iskallia.vault.init.ModItems;
import iskallia.vault.item.tool.JewelItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(value = JewelItem.class, remap = false)
public class MixinJewelItem {

    @Unique
    private static final VaultRecyclerConfig.RecyclerOutput woldsVaults$jewelOutput = new VaultRecyclerConfig.RecyclerOutput(new ChanceItemStackEntry(new ItemStack(ModItems.SILVER_SCRAP), 5, 8, 1.0F), new ChanceItemStackEntry(new ItemStack(ModItems.GEMSTONE), 1, 1, 1.0F), new ChanceItemStackEntry(ItemStack.EMPTY, 1, 1, 0.0F));

    /**
     * @author iwolfking
     * @reason Return a gemstone always when a jewel has a legendary
     */
    @Overwrite
    public VaultRecyclerConfig.RecyclerOutput getOutput(ItemStack input) {
        VaultGearData data = VaultGearData.read(input);
        AtomicBoolean hasLegendary = new AtomicBoolean(false);
        data.getAllModifierAffixes().forEach(vaultGearModifier -> {
            if(vaultGearModifier.hasCategory(VaultGearModifier.AffixCategory.LEGENDARY)) {
                hasLegendary.set(true);
            }
        });

        if(hasLegendary.get()) {
            return woldsVaults$jewelOutput;
        }
        return ModConfigs.VAULT_RECYCLER.getJewelRecyclingOutput();
    }
}
