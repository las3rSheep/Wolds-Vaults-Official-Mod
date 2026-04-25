package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.attribute.config.ConfigurableAttributeGenerator;
import iskallia.vault.gear.attribute.custom.effect.EffectGearAttribute;
import iskallia.vault.gear.attribute.custom.effect.EffectTrialAttribute;
import iskallia.vault.gear.attribute.type.VaultGearAttributeType;
import iskallia.vault.gear.comparator.VaultGearAttributeComparator;
import iskallia.vault.gear.reader.VaultGearModifierReader;
import iskallia.vault.init.ModGearAttributeGenerators;
import iskallia.vault.init.ModGearAttributeReaders;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.api.util.UniqueEffectGearAttribute;
import xyz.iwolfking.woldsvaults.modifiers.gear.ParticleTrailAttribute;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = WoldsVaults.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEtchingGearAttributes {
    //Utility Modifiers
    public static final VaultGearAttribute<Float> CONCENTRATE_DRAIN = woldsAttr("concentrate_drain", VaultGearAttributeType.floatType(), ModGearAttributeGenerators.floatRange(), ModGearAttributeReaders.none(), VaultGearAttributeComparator.floatComparator());
    public static final VaultGearAttribute<Float> COLOSSUS_TITAN_RESISTANCE = woldsAttr("colossus_titan_resistance", VaultGearAttributeType.floatType(), ModGearAttributeGenerators.floatRange(), ModGearAttributeReaders.none(), VaultGearAttributeComparator.floatComparator());
    public static final VaultGearAttribute<Boolean> LEVITATE_SLOW_FALLING = woldsAttr("levitate_slow_falling", VaultGearAttributeType.booleanType(), ModGearAttributeGenerators.booleanFlag(), ModGearAttributeReaders.none(), VaultGearAttributeComparator.booleanComparator());

    @SubscribeEvent
    public static void init(RegistryEvent.Register<VaultGearAttribute<?>> event) {
        IForgeRegistry<VaultGearAttribute<?>> registry = event.getRegistry();
        registry.register(CONCENTRATE_DRAIN);
        registry.register(COLOSSUS_TITAN_RESISTANCE);
        registry.register(LEVITATE_SLOW_FALLING);
    }

    private static <T> VaultGearAttribute<T> attr(String name, VaultGearAttributeType<T> type,ConfigurableAttributeGenerator<T, ?> generator,VaultGearModifierReader<T> reader,@Nullable VaultGearAttributeComparator<T> comparator) {
        return new VaultGearAttribute<>(VaultMod.id(name), type, generator, reader, comparator);
    }

    private static <T> VaultGearAttribute<T> woldsAttr(String name, VaultGearAttributeType<T> type,ConfigurableAttributeGenerator<T, ?> generator,VaultGearModifierReader<T> reader,@Nullable VaultGearAttributeComparator<T> comparator) {
        return new VaultGearAttribute<>(WoldsVaults.id(name), type, generator, reader, comparator);
    }

    private static <T> VaultGearAttribute<T> attr(String name, VaultGearAttributeType<T> type,ConfigurableAttributeGenerator<T, ?> generator,VaultGearModifierReader<T> reader) {
        return attr(name, type, generator, reader, null);
    }

    private static <T> VaultGearAttribute<T> woldsAttr(String name, VaultGearAttributeType<T> type,ConfigurableAttributeGenerator<T, ?> generator,VaultGearModifierReader<T> reader) {
        return woldsAttr(name, type, generator, reader, null);
    }
}
