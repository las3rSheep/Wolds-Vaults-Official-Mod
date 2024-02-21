package xyz.iwolfking.woldsvaults.objectives.data;

import com.cursedcauldron.wildbackport.common.registry.WBMobEffects;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import iskallia.vault.core.util.WeightedList;
import iskallia.vault.init.ModEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import samebutdifferent.ecologics.registry.ModMobEffects;
import vazkii.quark.content.mobs.module.WraithModule;
import xyz.iwolfking.woldsvaults.init.ModItems;
import xyz.iwolfking.woldsvaults.objectives.events.*;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

import java.util.List;


public class EnchantedEventsRegistry {

    private static final WeightedList<BasicEnchantedEvent> ENCHANTED_EVENTS = new WeightedList<>();
    private static final WeightedList<BasicEnchantedEvent> OMEGA_ENCHANTED_EVENTS = new WeightedList<>();

    private static final WeightedList<BasicEnchantedEvent> SPAWN_ENTITY_ENCHANTED_EVENTS = new WeightedList<>();
    private static final WeightedList<BasicEnchantedEvent> MODIFIER_ENCHANTED_EVENTS = new WeightedList<>();


    public static final VaultModifierEnchantedEvent COMMON_POSITIVE_MODIFER_EVENT;
    public static final VaultModifierEnchantedEvent LEECHING_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent COMMON_NEGATIVE_MODIFER_EVENT;
    public static final VaultModifierEnchantedEvent RARE_NEGATIVE_MODIFER_EVENT;
    public static final VaultModifierEnchantedEvent RARE_POSITIVE_MODIFER_EVENT;
    public static final VaultModifierEnchantedEvent OMEGA_NEGATIVE_MODIFER_EVENT;
    public static final VaultModifierEnchantedEvent OMEGA_POSITIVE_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent OMEGA_GOD_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent MOB_ONHITS_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent CHAOS_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent CURSES_MODIFIER_EVENT;
    public static final PotionEffectEnchantedEvent SLIPPERY_FLOORS_EVENT;
    public static final PotionEffectEnchantedEvent SUNBIRD_CURSE_EVENT;
    public static final PotionEffectEnchantedEvent SUNBIRD_BLESSING_EVENT;
    public static final PotionEffectEnchantedEvent CLINGING_EVENT;
    public static final PotionEffectEnchantedEvent DARKNESS_EVENT;
    public static final PotionEffectEnchantedEvent INSTAKILL_EVENT;

    public static final MultiPotionEffectEnchantedEvent BOLSTERED_EVENT;
    public static final MultiPotionEffectEnchantedEvent CHEMICAL_BATH_EVENT;
    public static final MultiPotionEffectEnchantedEvent HOLY_BLESSING_EVENT;

    public static final MultiPotionEffectEnchantedEvent HYPERSPEED_EVENT;
    public static final SpawnEntityEnchantedEvent COW_EVENT;
    public static final SpawnEntityEnchantedEvent BAT_EVENT;
    public static final SpawnEntityEnchantedEvent CREEPER_EVENT;
    public static final SpawnEntityEnchantedEvent ZOMBOID_EVENT;
    public static final SpawnEntityEnchantedEvent ARACHNOPHOBIA_EVENT;
    public static final SpawnEntityEnchantedEvent GHOSTY_EVENT;
    public static final SpawnEntityEnchantedEvent TNT_EVENT;
    public static final SpawnEntityEnchantedEvent TURTLES_EVENT;
    public static final SpawnEntityEnchantedEvent ZOO_EVENT;
    public static final SpawnEntityEnchantedEvent VOID_ZOO_EVENT;
    public static final SpawnEntityEnchantedEvent DWELLER_EVENT;
    public static final GiftItemEnchantedEvent WOLD_SANTA_BOX_EVENT;
    public static final GiftItemEnchantedEvent CONSOLATION_PRIZE;
    public static final InceptionEnchantedEvent PANDAMONIUM_EVENT;
    public static final InceptionEnchantedEvent X_RANDOM_EVENT;
    public static final InceptionEnchantedEvent X_OMEGA_RANDOM_EVENT;
    public static final InceptionEnchantedEvent X_MODIFIER_RANDOM_EVENT;
    public static final InceptionEnchantedEvent HORDE_EVENT;
    public static final InceptionEnchantedEvent VAMPIRE_SURVIVORS;

    public static void addEvents() {
        //Vault Modifier events
        register(COMMON_POSITIVE_MODIFER_EVENT, 50.0, false);
        register(COMMON_NEGATIVE_MODIFER_EVENT, 50.0, false);
        register(RARE_POSITIVE_MODIFER_EVENT, 35.0, false);
        register(RARE_NEGATIVE_MODIFER_EVENT, 35.0, false);
        register(OMEGA_POSITIVE_MODIFIER_EVENT, 6.0, true);
        register(OMEGA_NEGATIVE_MODIFER_EVENT, 6.0, true);
        register(OMEGA_GOD_MODIFIER_EVENT, 4.0, true);
        register(CHAOS_MODIFIER_EVENT, 6.0, true);
        register(MOB_ONHITS_MODIFIER_EVENT, 12.0, false);
        register(CURSES_MODIFIER_EVENT, 4.0, true);

        //Potion Effect events
        register(SLIPPERY_FLOORS_EVENT, 22.0, false);
        register(SUNBIRD_CURSE_EVENT, 12.0, false);
        register(SUNBIRD_BLESSING_EVENT, 12.0, false);
        register(CLINGING_EVENT, 12.0, false);
        register(DARKNESS_EVENT, 14.0, false);
        register(INSTAKILL_EVENT, 16.0, false);

        //Multi-Potion Effect events
        register(BOLSTERED_EVENT, 16.0, false);
        register(CHEMICAL_BATH_EVENT, 10.0, false);
        register(HOLY_BLESSING_EVENT, 14.0, false);
        register(HYPERSPEED_EVENT, 16.0, false);

        //Mob Spawn events
        register(COW_EVENT, 12.0, false);
        register(CREEPER_EVENT, 16.0, false);
        register(ZOMBOID_EVENT, 18.0, false);
        register(ARACHNOPHOBIA_EVENT, 18.0, false);
        register(GHOSTY_EVENT, 16.0, false);
        register(ZOO_EVENT, 16.0, false);
        register(DWELLER_EVENT, 22.0, false);
        register(VOID_ZOO_EVENT, 6.0, false);
        register(TURTLES_EVENT, 16.0, false);

        //Item Gift events
        register(WOLD_SANTA_BOX_EVENT, 16.0, true);
        register(CONSOLATION_PRIZE, 16.0, false);

        //Inception Events
        register(PANDAMONIUM_EVENT, 5.0, false);
        register(HORDE_EVENT, 10.0, true);
        register(X_RANDOM_EVENT, 7.0, true);
        register(X_MODIFIER_RANDOM_EVENT, 8.0, true);
        register(X_OMEGA_RANDOM_EVENT, 1.0, true);
        register(VAMPIRE_SURVIVORS, 10.0, false);

        ENCHANTED_EVENTS.forEach((basicEnchantedEvent, aDouble) -> {
            if(basicEnchantedEvent instanceof SpawnEntityEnchantedEvent) {
                SPAWN_ENTITY_ENCHANTED_EVENTS.add(basicEnchantedEvent, aDouble);
            }
        });

        ENCHANTED_EVENTS.forEach((basicEnchantedEvent, aDouble) -> {
            if(basicEnchantedEvent instanceof VaultModifierEnchantedEvent) {
                MODIFIER_ENCHANTED_EVENTS.add(basicEnchantedEvent, aDouble);
            }
        });
    }

    private static void register(BasicEnchantedEvent event, Double weight, boolean isOmega) {
        ENCHANTED_EVENTS.add(event, weight);
        if(isOmega) {
            OMEGA_ENCHANTED_EVENTS.add(event, 1.0);
        }
    }

    public static WeightedList<BasicEnchantedEvent> getEvents() {
        return ENCHANTED_EVENTS;
    }

    public static WeightedList<BasicEnchantedEvent> getOmegaEvents() {
        return OMEGA_ENCHANTED_EVENTS;
    }

    static {
        COMMON_POSITIVE_MODIFER_EVENT = new VaultModifierEnchantedEvent("Common Positive", "Adds a modifier from a common pool of positive modifiers.", ChatFormatting.YELLOW, ChatFormatting.GRAY, "basic_positive");
        LEECHING_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Leeching", "Adds leeching vault modifier.", ChatFormatting.RED, ChatFormatting.GRAY, "leeching");
        COMMON_NEGATIVE_MODIFER_EVENT = new VaultModifierEnchantedEvent("Common Negative", "Adds a modifier from a pool of common negatives modifiers.", ChatFormatting.RED, ChatFormatting.RED, "basic_negative");
        RARE_POSITIVE_MODIFER_EVENT = new VaultModifierEnchantedEvent("Rare Positive", "Adds a modifier from a pool of rare positive modifiers", ChatFormatting.GREEN, ChatFormatting.RED, "medium_positive");
        RARE_NEGATIVE_MODIFER_EVENT = new VaultModifierEnchantedEvent("Rare Negative", "Adds a modifier from a pool of rare negative modifiers", ChatFormatting.DARK_RED, ChatFormatting.RED, "medium_negative");
        OMEGA_NEGATIVE_MODIFER_EVENT = new VaultModifierEnchantedEvent("Omega Negative", "Adds a modifier from a pool of omega negative modifiers", ChatFormatting.DARK_RED, ChatFormatting.RED, "omega_negative");
        OMEGA_POSITIVE_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Omega Positive", "Adds one very good random modifier.", ChatFormatting.GREEN, ChatFormatting.DARK_GREEN, "omega_positive");
        OMEGA_GOD_MODIFIER_EVENT = new VaultModifierEnchantedEvent("God's Blessing", "Adds one favour modifier from the gods.", ChatFormatting.BLUE, ChatFormatting.DARK_GREEN, "gods_omega_blessing");
        MOB_ONHITS_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Mob On-hits", "Adds one modifier that adds an on-hit effect to mobs.", ChatFormatting.DARK_BLUE, ChatFormatting.DARK_GREEN, "mob_onhits");
        CHAOS_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Mob On-hits", "Adds one modifier that adds an on-hit effect to mobs.", ChatFormatting.DARK_BLUE, ChatFormatting.DARK_GREEN, "mob_onhits");
        CURSES_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Curses", "Adds one modifier from a pool of curses.", ChatFormatting.DARK_BLUE, ChatFormatting.DARK_GREEN, "curses");
        SLIPPERY_FLOORS_EVENT = new PotionEffectEnchantedEvent("Slippery Floors", "Who forgot to dry the floor!?", ChatFormatting.AQUA, ChatFormatting.DARK_AQUA,  ModMobEffects.SLIPPERY.get(), 1800, 10);
        SUNBIRD_CURSE_EVENT = new PotionEffectEnchantedEvent("Curse of the Sunbird", "It seems gravity is higher...", ChatFormatting.GOLD, ChatFormatting.RED,  AMEffectRegistry.SUNBIRD_CURSE, 1200, 1);
        SUNBIRD_BLESSING_EVENT = new PotionEffectEnchantedEvent("Blessing of the Sunbird", "Glide to safety", ChatFormatting.GOLD, ChatFormatting.RED,  AMEffectRegistry.SUNBIRD_BLESSING, 1800, 0);
        CLINGING_EVENT = new PotionEffectEnchantedEvent("Topsy Turvy", "Walk on ceilings o.o", ChatFormatting.LIGHT_PURPLE, ChatFormatting.DARK_PURPLE,  AMEffectRegistry.CLINGING, 1200, 0);
        DARKNESS_EVENT = new PotionEffectEnchantedEvent("Lights Out", "Who turned out the lights!?", ChatFormatting.BLACK, ChatFormatting.YELLOW,  WBMobEffects.DARKNESS.get(), 600, 200);
        INSTAKILL_EVENT = new PotionEffectEnchantedEvent("Insta-Kill", "Grants strength x 255", ChatFormatting.YELLOW, ChatFormatting.YELLOW,  MobEffects.DAMAGE_BOOST, 600 , 255);
        HYPERSPEED_EVENT = new MultiPotionEffectEnchantedEvent("Hyper-Speed", "Grants speed x 10", ChatFormatting.AQUA, ChatFormatting.YELLOW,  List.of(MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED), 900, 9);
        BOLSTERED_EVENT = new MultiPotionEffectEnchantedEvent("Barricade", "I feel a lot tougher!", ChatFormatting.DARK_GRAY, ChatFormatting.YELLOW, List.of(MobEffects.DAMAGE_RESISTANCE, MobEffects.FIRE_RESISTANCE), 900, 2);
        CHEMICAL_BATH_EVENT = new MultiPotionEffectEnchantedEvent("Chemical Bath", "A myriad of negative potion effects!", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, List.of(MobEffects.POISON, MobEffects.WITHER, MobEffects.HUNGER, MobEffects.REGENERATION, MobEffects.WEAKNESS, MobEffects.GLOWING, AMEffectRegistry.BUG_PHEROMONES, MobEffects.BLINDNESS), 300, 1);
        HOLY_BLESSING_EVENT = new MultiPotionEffectEnchantedEvent("Holy Blessing", "A myriad of positive potion effects!", ChatFormatting.GOLD, ChatFormatting.YELLOW, List.of(MobEffects.GLOWING, MobEffects.REGENERATION, MobEffects.SATURATION, MobEffects.DAMAGE_RESISTANCE, MobEffects.DAMAGE_BOOST, MobEffects.DIG_SPEED, MobEffects.MOVEMENT_SPEED, MobEffects.NIGHT_VISION, MobEffects.FIRE_RESISTANCE), 600, 4);
        COW_EVENT = new SpawnEntityEnchantedEvent("Barnyard Bash", "Attack of the cows", ChatFormatting.GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.AGGRESSIVE_COW, 6.0), new WeightedList<Integer>().add(8, 10).add(16, 20));
        BAT_EVENT = new SpawnEntityEnchantedEvent("Bat Swarm", "Bats everywhere!?", ChatFormatting.DARK_GRAY, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(EntityType.BAT, 6.0), new WeightedList<Integer>().add(12, 10).add(16, 20));
        CREEPER_EVENT = new SpawnEntityEnchantedEvent("Jeepers Creepers", "Attack of the creepers", ChatFormatting.GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.T1_CREEPER, 6.0).add(ModEntities.T2_CREEPER, 4.0).add(ModEntities.T3_CREEPER, 2.0), new WeightedList<Integer>().add(8, 10).add(12, 10));
        ZOMBOID_EVENT = new SpawnEntityEnchantedEvent("Project Zomboid", "Attack of the zombies", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.T1_ZOMBIE, 6.0).add(ModEntities.T2_ZOMBIE, 4.0).add(ModEntities.T3_ZOMBIE, 2.0), new WeightedList<Integer>().add(8, 10).add(12, 10));
        ARACHNOPHOBIA_EVENT = new SpawnEntityEnchantedEvent("Arachnophobia", "Attack of the spidders", ChatFormatting.DARK_PURPLE, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.VAULT_SPIDER, 6.0).add(ModEntities.VAULT_SPIDER_BABY, 4.0).add(ModEntities.DUNGEON_SPIDER, 2.0), new WeightedList<Integer>().add(8, 10).add(12, 10));
        GHOSTY_EVENT = new SpawnEntityEnchantedEvent("Happy Halloween", "All ghost costumes this year?", ChatFormatting.LIGHT_PURPLE, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(WraithModule.wraithType, 6.0), new WeightedList<Integer>().add(8, 10).add(12, 10));
        TNT_EVENT = new SpawnEntityEnchantedEvent("Whoops", "Who left all this tnt around?", ChatFormatting.RED, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(EntityType.TNT, 6.0), new WeightedList<Integer>().add(8, 10).add(12, 10));
        TURTLES_EVENT = new SpawnEntityEnchantedEvent("Ninja Turtles", "Attack of the turtles", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(AMEntityRegistry.ALLIGATOR_SNAPPING_TURTLE.get(), 6.0), new WeightedList<Integer>().add(8, 10).add(16, 20));
        ZOO_EVENT = new SpawnEntityEnchantedEvent("Escaped Zoo", "Who's Alex anyway?", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(AMEntityRegistry.CROCODILE.get(), 6.0).add(AMEntityRegistry.GORILLA.get(), 6.0).add(AMEntityRegistry.TIGER.get(), 6.0).add(AMEntityRegistry.ELEPHANT.get(), 4.0).add(AMEntityRegistry.KOMODO_DRAGON.get(), 2.0).add(AMEntityRegistry.SNOW_LEOPARD.get(), 3.0), new WeightedList<Integer>().add(7, 10).add(12, 10));
        VOID_ZOO_EVENT = new SpawnEntityEnchantedEvent("Void Invasion", "Ender what now?", ChatFormatting.LIGHT_PURPLE, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(AMEntityRegistry.ENDERIOPHAGE.get(), 12.0).add(AMEntityRegistry.VOID_WORM.get(), 1.0).add(AMEntityRegistry.COSMIC_COD.get(), 6.0).add(AMEntityRegistry.MIMICUBE.get(), 5.0).add(ModEntities.T1_ENDERMAN, 8.0).add(ModEntities.T2_ENDERMAN, 9.0), new WeightedList<Integer>().add(4, 10).add(8, 10));
        DWELLER_EVENT = new SpawnEntityEnchantedEvent("Dweller Duel", "Attack of the dwellers", ChatFormatting.YELLOW, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.VAULT_FIGHTER_TYPES.get(1), 6.0).add(ModEntities.VAULT_FIGHTER_TYPES.get(2), 6.0).add(ModEntities.VAULT_FIGHTER_TYPES.get(3), 6.0).add(ModEntities.VAULT_FIGHTER_TYPES.get(4), 6.0), new WeightedList<Integer>().add(8, 10).add(12, 10));
        WOLD_SANTA_BOX_EVENT = new GiftItemEnchantedEvent("Wold's Box Giveaway", "And they said I wasn't nice", ChatFormatting.LIGHT_PURPLE, ChatFormatting.YELLOW, new WeightedList<ItemStack>().add(new ItemStack(ModItems.SUPPLY_BOX.asItem(), 1), 4.0).add(new ItemStack(ModItems.GEM_BOX.asItem(), 1), 4.0).add(new ItemStack(iskallia.vault.init.ModItems.MOD_BOX.asItem(), 1), 4.0).add(new ItemStack(iskallia.vault.init.ModItems.MYSTERY_BOX.asItem(), 1), 4.0));
        CONSOLATION_PRIZE = new GiftItemEnchantedEvent("Consolation Prize", "At least you tried...", ChatFormatting.LIGHT_PURPLE, ChatFormatting.YELLOW, new WeightedList<ItemStack>().add(new ItemStack(iskallia.vault.init.ModItems.VELVET.asItem(), 8), 4.0).add(new ItemStack(iskallia.vault.init.ModItems.ORNATE_INGOT.asItem(), 8), 4.0).add(new ItemStack(iskallia.vault.init.ModItems.GILDED_INGOT.asItem(), 8), 4.0).add(new ItemStack(iskallia.vault.init.ModItems.VAULT_MEAT.asItem(), 8), 4.0));
        PANDAMONIUM_EVENT = new InceptionEnchantedEvent("PANDAMONIUM", "Unleash the horde", ChatFormatting.YELLOW, ChatFormatting.YELLOW, SPAWN_ENTITY_ENCHANTED_EVENTS, true, 5);
        X_RANDOM_EVENT = new InceptionEnchantedEvent("5 Random Events", "Open pandora's box", ChatFormatting.GOLD, ChatFormatting.YELLOW, ENCHANTED_EVENTS, true, 5);
        X_OMEGA_RANDOM_EVENT = new InceptionEnchantedEvent("3 Random Omega Events", "3 random omega events, did you get lucky?", ChatFormatting.GOLD, ChatFormatting.YELLOW, OMEGA_ENCHANTED_EVENTS, true, 3);
        X_MODIFIER_RANDOM_EVENT = new InceptionEnchantedEvent("3 Random Omega Events", "3 random modifier events", ChatFormatting.GOLD, ChatFormatting.YELLOW, MODIFIER_ENCHANTED_EVENTS, true, 3);
        HORDE_EVENT = new InceptionEnchantedEvent("Horde Night", "3 Random Mob spawn events", ChatFormatting.YELLOW, ChatFormatting.YELLOW, SPAWN_ENTITY_ENCHANTED_EVENTS, true, 3);
        VAMPIRE_SURVIVORS = new InceptionEnchantedEvent("Vampire Survivors", "Grants leeching and spawns bats", ChatFormatting.DARK_GRAY, ChatFormatting.YELLOW, new WeightedList<BasicEnchantedEvent>().add(LEECHING_MODIFIER_EVENT, 1.0).add(BAT_EVENT, 1.0), false, 0);
    }
}
