package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.gear.modification.GearModification;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.api.gear.actions.unusual.UnusualGearModification;

public class ModGearModifications {
    public static final UnusualGearModification UNUSUAL_GEAR_MODIFICATION = new UnusualGearModification();

    public static void init(RegistryEvent.Register<GearModification> event) {
        IForgeRegistry<GearModification> registry = event.getRegistry();
        registry.register(UNUSUAL_GEAR_MODIFICATION);
    }
}