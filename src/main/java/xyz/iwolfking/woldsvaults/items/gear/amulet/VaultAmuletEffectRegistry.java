//package xyz.iwolfking.woldsvaults.items.gear.amulet;
//
//import com.google.common.collect.Streams;
//import iskallia.vault.VaultMod;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceKey;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.registries.ForgeRegistryEntry;
//import net.minecraftforge.registries.IForgeRegistry;
//import net.minecraftforge.registries.NewRegistryEvent;
//import net.minecraftforge.registries.RegistryBuilder;
//
//import javax.annotation.Nullable;
//import java.util.Comparator;
//import java.util.List;
//
//public class VaultAmuletEffectRegistry {
//    public static ResourceKey<Registry<VaultAmuletEffect<?>>> CHARM_REGISTRY_KEY = ResourceKey.createRegistryKey(VaultMod.id("amulet"));
//    private static IForgeRegistry<VaultAmuletEffect<?>> charmRegistry;
//
//    public VaultAmuletEffectRegistry() {
//    }
//
//    public static IForgeRegistry<VaultAmuletEffect<?>> getRegistry() {
//        return charmRegistry;
//    }
//
//    @Nullable
//    public static VaultAmuletEffect<?> getEffect(ResourceLocation key) {
//        return (VaultAmuletEffect)charmRegistry.getValue(key);
//    }
//
//    public static List<VaultAmuletEffect<?>> getOrderedEntries() {
//        return Streams.stream(getRegistry()).sorted(Comparator.comparing((set) -> {
//            return set.getRegistryName().getPath();
//        })).toList();
//    }
//
//    public static List<ResourceLocation> getOrderedKeys() {
//        return getOrderedEntries().stream().map(ForgeRegistryEntry::getRegistryName).toList();
//    }
//
//    @SubscribeEvent
//    public static void buildRegistry(NewRegistryEvent event) {
//        event.create((new RegistryBuilder<VaultAmuletEffect<?>>()).setName(CHARM_REGISTRY_KEY.location()).disableSaving().disableOverrides(), (registry) -> {
//                    charmRegistry = registry;
//                });
//
//    }
//}
