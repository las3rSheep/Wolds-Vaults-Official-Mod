package xyz.iwolfking.woldsvaults.items.gear.amulet;

import com.google.gson.annotations.Expose;
import iskallia.vault.core.vault.ClientVaults;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.attribute.VaultGearAttributeInstance;
import iskallia.vault.gear.attribute.VaultGearAttributeRegistry;
import iskallia.vault.item.gear.CharmItem;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.iwolfking.woldsvaults.config.VaultAmuletConfig;
import xyz.iwolfking.woldsvaults.init.ModConfigs;

import java.util.UUID;

public abstract class VaultAmuletEffect<T> extends ForgeRegistryEntry<VaultAmuletEffect
        <?>> {
    public VaultAmuletEffect(ResourceLocation name) {
        this.setRegistryName(name);
    }

    public abstract Class<T> getConfigClass();

    public abstract T getDefaultConfig();

    public VaultAmuletConfig.Charm getCharmConfig() {
        return ModConfigs.VAULT_AMULET.getCharmConfig(this);
    }

    public T getConfig() {
        return (T) this.getCharmConfig().getConfig();
    }

    public boolean isUsable(ItemStack trinket, Player player) {
        if (!CharmItem.isIdentified(trinket)) {
            return false;
        } else {
            Vault vault = player.level.isClientSide ? (Vault)ClientVaults.getActive().orElse((Vault) null) : (Vault)ServerVaults.get(player.level).orElse((Vault) null);
            return vault == null ? CharmItem.hasUsesLeft(trinket) : CharmItem.isUsableInVault(trinket, (UUID)vault.get(Vault.ID));
        }
    }

    public void onEquip(LivingEntity entity, ItemStack stack) {
    }

    public void onUnEquip(LivingEntity entity, ItemStack stack) {
    }

    public void onWornTick(LivingEntity entity, ItemStack stack) {
    }

    public static class Config<T extends Number> {
        @Expose
        private final ResourceLocation key;
        private final float value;

        public Config(VaultGearAttribute<T> attribute, float value) {
            this.key = attribute.getRegistryName();
            this.value = value;
        }

        public VaultGearAttribute<T> getAttribute() {
            return (VaultGearAttribute<T>) VaultGearAttributeRegistry.getAttribute(this.key);
        }

        public VaultGearAttributeInstance<?> toAttributeInstance() {
            return VaultGearAttributeInstance.cast(this.getAttribute(), this.value);
        }

        public VaultGearAttributeInstance<?> toAttributeInstance(float value) {
            return VaultGearAttributeInstance.cast(this.getAttribute(), value);
        }
    }
}
