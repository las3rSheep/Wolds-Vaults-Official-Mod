package xyz.iwolfking.woldsvaults.objectives.data;

import com.cursedcauldron.wildbackport.common.registry.WBMobEffects;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import iskallia.vault.core.util.WeightedList;
import iskallia.vault.init.ModEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import samebutdifferent.ecologics.registry.ModMobEffects;
import xyz.iwolfking.woldsvaults.objectives.events.MultiPotionEffectEnchantedEvent;
import xyz.iwolfking.woldsvaults.objectives.events.PotionEffectEnchantedEvent;
import xyz.iwolfking.woldsvaults.objectives.events.SpawnEntityEnchantedEvent;
import xyz.iwolfking.woldsvaults.objectives.events.VaultModifierEnchantedEvent;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

import java.util.List;


public class EnchantedEventsRegistry {

    private static final WeightedList<BasicEnchantedEvent> ENCHANTED_EVENTS = new WeightedList<>();


    public static final VaultModifierEnchantedEvent HAUNTED_BRAZIER;
    public static final VaultModifierEnchantedEvent RANDOM_NEGATIVE_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent OMEGA_POSITIVE_MODIFIER_EVENT;
    public static final VaultModifierEnchantedEvent BRAZIER_MODIFIER_EVENT;

    public static final PotionEffectEnchantedEvent SLIPPERY_FLOORS_EVENT;
    public static final PotionEffectEnchantedEvent SUNBIRD_CURSE_EVENT;
    public static final PotionEffectEnchantedEvent SUNBIRD_BLESSING_EVENT;
    public static final PotionEffectEnchantedEvent CLINGING_EVENT;
    public static final PotionEffectEnchantedEvent DARKNESS_EVENT;
    public static final MultiPotionEffectEnchantedEvent BOLSTERED_EVENT;
    public static final MultiPotionEffectEnchantedEvent CHEMICAL_BATH_EVENT;
    public static final MultiPotionEffectEnchantedEvent HOLY_BLESSING_EVENT;
    public static final SpawnEntityEnchantedEvent COW_EVENT;
    public static final SpawnEntityEnchantedEvent TURTLES_EVENT;
    public static final SpawnEntityEnchantedEvent ZOO_EVENT;
    public static final SpawnEntityEnchantedEvent VOID_ZOO_EVENT;
    public static final SpawnEntityEnchantedEvent DWELLER_EVENT;

    public static void addEvents() {
        register(HAUNTED_BRAZIER, 10000.0);
        register(RANDOM_NEGATIVE_MODIFIER_EVENT, 10000.0);
        register(OMEGA_POSITIVE_MODIFIER_EVENT, 10000.0);
        register(BRAZIER_MODIFIER_EVENT, 10000.0);
        register(SLIPPERY_FLOORS_EVENT, 8.0);
        register(SUNBIRD_CURSE_EVENT, 8.0);
        register(SUNBIRD_BLESSING_EVENT, 8.0);
        register(CLINGING_EVENT, 8.0);
        register(DARKNESS_EVENT, 8.0);
        register(BOLSTERED_EVENT, 8.0);
        register(CHEMICAL_BATH_EVENT, 8.0);
        register(HOLY_BLESSING_EVENT, 8.0);
        register(COW_EVENT, 800000.0);
        register(ZOO_EVENT, 800000.0);
        register(VOID_ZOO_EVENT, 800000.0);
        register(TURTLES_EVENT, 800000.0);
    }

    private static void register(BasicEnchantedEvent event, Double weight) {
        ENCHANTED_EVENTS.add(event, weight);

    }

    public static WeightedList<BasicEnchantedEvent> getEvents() {
        return ENCHANTED_EVENTS;
    }

    static {
        HAUNTED_BRAZIER = new VaultModifierEnchantedEvent("Haunted Brazier", "Adds modifiers from a Haunted Brazier.", ChatFormatting.DARK_GRAY, ChatFormatting.GRAY, "haunted_brazier");
        RANDOM_NEGATIVE_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Negative", "Adds modifiers from a pool of random negatives.", ChatFormatting.DARK_RED, ChatFormatting.RED, "random_negative");
        OMEGA_POSITIVE_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Omega Positive", "Adds one very good random modifier.", ChatFormatting.GREEN, ChatFormatting.DARK_GREEN, "omega_positive");
        BRAZIER_MODIFIER_EVENT = new VaultModifierEnchantedEvent("Brazier", "Adds modifier from a Brazier.", ChatFormatting.YELLOW, ChatFormatting.DARK_GRAY, "brazier");
        SLIPPERY_FLOORS_EVENT = new PotionEffectEnchantedEvent("Slippery Floors", "Who forgot to dry the floor!?", ChatFormatting.AQUA, ChatFormatting.DARK_AQUA,  ModMobEffects.SLIPPERY.get(), 1800, 10);
        SUNBIRD_CURSE_EVENT = new PotionEffectEnchantedEvent("Curse of the Sunbird", "It seems gravity is higher...", ChatFormatting.GOLD, ChatFormatting.RED,  AMEffectRegistry.SUNBIRD_CURSE, 1200, 1);
        SUNBIRD_BLESSING_EVENT = new PotionEffectEnchantedEvent("Blessing of the Sunbird", "Glide to safety", ChatFormatting.GOLD, ChatFormatting.RED,  AMEffectRegistry.SUNBIRD_BLESSING, 1800, 0);
        CLINGING_EVENT = new PotionEffectEnchantedEvent("Topsy Turvy", "Walk on ceilings o.o", ChatFormatting.LIGHT_PURPLE, ChatFormatting.DARK_PURPLE,  AMEffectRegistry.CLINGING, 1200, 0);
        DARKNESS_EVENT = new PotionEffectEnchantedEvent("Lights Out", "Who turned out the lights!?", ChatFormatting.BLACK, ChatFormatting.YELLOW,  WBMobEffects.DARKNESS.get(), 600, 0);
        BOLSTERED_EVENT = new MultiPotionEffectEnchantedEvent("Barricade", "I feel a lot tougher!", ChatFormatting.DARK_GRAY, ChatFormatting.YELLOW, List.of(MobEffects.DAMAGE_RESISTANCE, MobEffects.FIRE_RESISTANCE), 900, 2);
        CHEMICAL_BATH_EVENT = new MultiPotionEffectEnchantedEvent("Chemical Bath", "A myriad of negative potion effects!", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, List.of(MobEffects.POISON, MobEffects.WITHER, MobEffects.HUNGER, MobEffects.REGENERATION, MobEffects.WEAKNESS, MobEffects.GLOWING, AMEffectRegistry.BUG_PHEROMONES, MobEffects.BLINDNESS), 300, 1);
        HOLY_BLESSING_EVENT = new MultiPotionEffectEnchantedEvent("Holy Blessing", "A myriad of positive potion effects!", ChatFormatting.GOLD, ChatFormatting.YELLOW, List.of(MobEffects.GLOWING, MobEffects.REGENERATION, MobEffects.SATURATION, MobEffects.DAMAGE_RESISTANCE, MobEffects.DAMAGE_BOOST, MobEffects.DIG_SPEED, MobEffects.MOVEMENT_SPEED, MobEffects.NIGHT_VISION, MobEffects.FIRE_RESISTANCE), 600, 4);
        COW_EVENT = new SpawnEntityEnchantedEvent("Barnyard", "Attack of the cows", ChatFormatting.GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.AGGRESSIVE_COW, 6.0), new WeightedList<Integer>().add(8, 10).add(16, 20));
        TURTLES_EVENT = new SpawnEntityEnchantedEvent("Ninja Turtles", "Attack of the turtles", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(AMEntityRegistry.ALLIGATOR_SNAPPING_TURTLE.get(), 6.0), new WeightedList<Integer>().add(8, 10).add(16, 20));
        ZOO_EVENT = new SpawnEntityEnchantedEvent("Escaped Zoo", "Who's Alex anyway?", ChatFormatting.DARK_GREEN, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(AMEntityRegistry.CROCODILE.get(), 6.0).add(AMEntityRegistry.GORILLA.get(), 6.0).add(AMEntityRegistry.TIGER.get(), 6.0).add(AMEntityRegistry.ELEPHANT.get(), 4.0).add(AMEntityRegistry.KOMODO_DRAGON.get(), 2.0).add(AMEntityRegistry.SNOW_LEOPARD.get(), 3.0), new WeightedList<Integer>().add(8, 10).add(16, 20));
        VOID_ZOO_EVENT = new SpawnEntityEnchantedEvent("Void Invasion", "Ender what now?", ChatFormatting.LIGHT_PURPLE, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(AMEntityRegistry.ENDERIOPHAGE.get(), 12.0).add(AMEntityRegistry.VOID_WORM.get(), 1.0).add(AMEntityRegistry.COSMIC_COD.get(), 6.0).add(AMEntityRegistry.MIMICUBE.get(), 5.0).add(ModEntities.T1_ENDERMAN, 8.0).add(ModEntities.T2_ENDERMAN, 9.0), new WeightedList<Integer>().add(4, 10).add(8, 20));
        DWELLER_EVENT = new SpawnEntityEnchantedEvent("Dweller Duel", "Attack of the dwellers", ChatFormatting.GRAY, ChatFormatting.YELLOW, new WeightedList<EntityType<?>>().add(ModEntities.FIGHTER, 6.0), new WeightedList<Integer>().add(8, 10).add(16, 20));

    }
}
