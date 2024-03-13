package xyz.iwolfking.woldsvaults.mixins;

import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.p3pp3rf1y.sophisticatedstorage.block.IAdditionalDropDataBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageBlockBase;
import net.p3pp3rf1y.sophisticatedstorage.block.WoodStorageBlockBase;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.data.VaultBlockFamilies;
import xyz.iwolfking.woldsvaults.data.VaultWoodTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(value = WoodStorageBlockBase.class, remap = false)
public abstract class MixinWoodenStorageBlockBase extends StorageBlockBase implements IAdditionalDropDataBlock {
    @Mutable
    @Shadow @Final public static Map<WoodType, BlockFamily> CUSTOM_TEXTURE_WOOD_TYPES;
     private static Map<WoodType, BlockFamily> VAULT_WOOD_TYPES;

    static {
        Map<WoodType, BlockFamily> copy = new HashMap<>(CUSTOM_TEXTURE_WOOD_TYPES);
        VAULT_WOOD_TYPES = Map.of(VaultWoodTypes.VAULT_PLANKS, VaultBlockFamilies.VAULT_PLANKS, VaultWoodTypes.CHROMATIC, VaultBlockFamilies.CHROMATIC, VaultWoodTypes.DRIFTWOOD, VaultBlockFamilies.DRIFTWOOD, VaultWoodTypes.OVERGROWN, VaultBlockFamilies.OVERGROWN, VaultWoodTypes.TENOS, VaultBlockFamilies.TENOS, VaultWoodTypes.VELARA, VaultBlockFamilies.VELARA);
        copy.putAll(VAULT_WOOD_TYPES);
        CUSTOM_TEXTURE_WOOD_TYPES = copy;
    }

    protected MixinWoodenStorageBlockBase(Properties properties, Supplier<Integer> numberOfInventorySlotsSupplier, Supplier<Integer> numberOfUpgradeSlotsSupplier) {
        super(properties, numberOfInventorySlotsSupplier, numberOfUpgradeSlotsSupplier);
    }

//
//    /**
//     * @author iwolfking
//     * @reason fix?
//     */
//    @Overwrite
//    private boolean isBasicTier() {
//        return this.getRegistryName().equals(new ResourceLocation("sophisticatedstorage", "barrel")) || this.getRegistryName().equals(new ResourceLocation("sophisticatedstorage", "chest")) || this.getRegistryName().equals(new ResourceLocation("sophisticatedstorage", "limited_barrel_1")) || this.getRegistryName().equals(new ResourceLocation("sophisticatedstorage", "limited_barrel_2")) || this.getRegistryName().equals(new ResourceLocation("sophisticatedstorage", "limited_barrel_3")) || this.getRegistryName().equals(new ResourceLocation("sophisticatedstorage", "limited_barrel_4"));
//    }
}
