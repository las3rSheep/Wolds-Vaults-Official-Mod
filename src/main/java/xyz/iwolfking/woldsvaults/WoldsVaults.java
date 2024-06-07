package xyz.iwolfking.woldsvaults;

import com.mojang.logging.LogUtils;
import iskallia.vault.init.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.capability.ICurio;
import xyz.iwolfking.woldsvaults.api.registry.CustomCatalystModelRegistry;
import xyz.iwolfking.woldsvaults.config.forge.OptionsHolder;
import xyz.iwolfking.woldsvaults.curios.ShardPouchCurio;
import xyz.iwolfking.woldsvaults.events.RegisterCommandEventHandler;
import xyz.iwolfking.woldsvaults.lib.network.PacketHandler;
import xyz.iwolfking.woldsvaults.objectives.data.BrutalBossesRegistry;
import xyz.iwolfking.woldsvaults.objectives.data.EnchantedEventsRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("woldsvaults")
public class WoldsVaults {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "woldsvaults";
    public WoldsVaults() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, OptionsHolder.COMMON_SPEC, "woldsvaults-common.toml");
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);

        MinecraftForge.EVENT_BUS.addListener(RegisterCommandEventHandler::woldsvaults_registerCommandsEvent);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        if(OptionsHolder.COMMON.enableDebugMode.get()) {
            LOGGER.warn("Debug mode is enabled! Please disable this in woldsvaults-common.toml to prevent unnecessary log spam!");
            LOGGER.debug("Initializing FMLCommonSetup events!");
        }
        PacketHandler.init();
        CustomCatalystModelRegistry.registerModels();
    }


    private void enqueueIMC(final InterModEnqueueEvent event) {
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("shard_pouch").icon(new ResourceLocation(MOD_ID + ":slot/shard_pouch")).size(1).build());
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        EnchantedEventsRegistry.addEvents();
        BrutalBossesRegistry.init();

    }






    public static ResourceLocation id(String name) {
        return new ResourceLocation("woldsvaults", name);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class Events {
        @SubscribeEvent
        public static void attachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
            ItemStack stack = event.getObject();

            if(stack.getItem() == ModItems.SHARD_POUCH) {
                ICurio curioShardPouch = new ShardPouchCurio(stack);

                event.addCapability(CuriosCapability.ID_ITEM, new ICapabilityProvider() {
                    final LazyOptional<ICurio> curio = LazyOptional.of(() -> curioShardPouch);
                    @NotNull
                    @Override
                    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
                        return CuriosCapability.ITEM.orEmpty(cap, curio);
                    }
                });
            }
        }
    }
}
