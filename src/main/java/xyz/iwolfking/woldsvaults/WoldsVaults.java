package xyz.iwolfking.woldsvaults;

import com.mojang.logging.LogUtils;
import iskallia.vault.item.crystal.CrystalData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.iwolfking.vhapi.api.registry.gear.CustomVaultGearRegistryEntry;
import xyz.iwolfking.vhapi.api.registry.objective.CustomObjectiveRegistryEntry;
import xyz.iwolfking.woldsvaults.api.WoldDataLoaders;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.data.discovery.DiscoveredThemesData;
import xyz.iwolfking.woldsvaults.events.LivingEntityEvents;
import xyz.iwolfking.woldsvaults.events.RegisterCommandEventHandler;
import xyz.iwolfking.woldsvaults.init.*;
import xyz.iwolfking.woldsvaults.lib.network.PacketHandler;
import xyz.iwolfking.woldsvaults.models.AdditionalModels;
import xyz.iwolfking.woldsvaults.objectives.data.BrutalBossesRegistry;
import xyz.iwolfking.woldsvaults.objectives.data.EnchantedEventsRegistry;
import xyz.iwolfking.woldsvaults.objectives.speedrun.SpeedrunCrystalObjective;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("woldsvaults")
public class WoldsVaults {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "woldsvaults";
    public WoldsVaults() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WoldsVaultsConfig.COMMON_SPEC, "woldsvaults-common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, WoldsVaultsConfig.CLIENT_SPEC, "woldsvaults-client.toml");
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModParticles.register(modEventBus);

        modEventBus.addGenericListener(CustomObjectiveRegistryEntry.class, ModCustomVaultObjectiveEntries::registerCustomObjectives);
        modEventBus.addGenericListener(CustomVaultGearRegistryEntry.class, ModCustomVaultGearEntries::registerGearEntries);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::onPlayerLoggedIn);
        MinecraftForge.EVENT_BUS.addListener(RegisterCommandEventHandler::woldsvaults_registerCommandsEvent);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModCatalystModels.registerModels();
        ModInscriptionModels.registerModels();
        MinecraftForge.EVENT_BUS.addListener(WoldDataLoaders::initProcessors);
    }

    private void setup(final FMLCommonSetupEvent event) {
        if(WoldsVaultsConfig.COMMON.enableDebugMode.get()) {
            LOGGER.warn("Debug mode is enabled! Please disable this in woldsvaults-common.toml to prevent unnecessary log spam!");
            LOGGER.debug("Initializing FMLCommonSetup events!");
        }
        PacketHandler.init();

        LivingEntityEvents.init();
        new AdditionalModels();
        ModVaultFilterAttributes.initAttributes();
        CrystalData.OBJECTIVE.register("brb_speedrun", SpeedrunCrystalObjective.class, SpeedrunCrystalObjective::new);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        EnchantedEventsRegistry.addEvents();
        BrutalBossesRegistry.init();
    }

    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ModConfigs.AUGMENT_RECIPES.syncTo(ModConfigs.AUGMENT_RECIPES, (ServerPlayer) event.getPlayer());
        DiscoveredThemesData.get(((ServerPlayer) event.getPlayer()).getLevel()).syncTo((ServerPlayer) event.getPlayer());

    }






    public static ResourceLocation id(String name) {
        return new ResourceLocation("woldsvaults", name);
    }

    public static String sId(String name) {
        return "woldsvaults:" + name;
    }
}
