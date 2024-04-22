package xyz.iwolfking.woldsvaults;

import com.mojang.logging.LogUtils;
import iskallia.vault.init.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.capability.ICurio;
import xyz.iwolfking.woldsvaults.curios.ShardPouchCurio;
import xyz.iwolfking.woldsvaults.events.RegisterCommandEventHandler;
import xyz.iwolfking.woldsvaults.lib.network.PacketHandler;
import xyz.iwolfking.woldsvaults.objectives.data.BrutalBossesRegistry;
import xyz.iwolfking.woldsvaults.objectives.data.EnchantedEventsRegistry;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("woldsvaults")
public class WoldsVaults {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "woldsvaults";
    public WoldsVaults() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.addListener(RegisterCommandEventHandler::woldsvaults_registerCommandsEvent);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
    }


    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("shard_pouch").icon(new ResourceLocation(MOD_ID + ":slot/shard_pouch")).size(1).build());
        InterModComms.sendTo("woldsvaults", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        EnchantedEventsRegistry.addEvents();
        BrutalBossesRegistry.init();

    }





    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        }
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
