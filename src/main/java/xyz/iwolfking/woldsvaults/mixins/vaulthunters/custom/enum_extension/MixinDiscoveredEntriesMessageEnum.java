package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom.enum_extension;

import iskallia.vault.network.message.transmog.DiscoveredEntriesMessage;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(targets = "iskallia.vault.network.message.transmog.DiscoveredEntriesMessage$Type", remap = false)
public class MixinDiscoveredEntriesMessageEnum {
    @Shadow
    @Final
    @Mutable
    private static DiscoveredEntriesMessage.Type[] $VALUES;

    private static final DiscoveredEntriesMessage.Type THEME = enumExpansion$addVariant("THEME");

    @Invoker("<init>")
    public static DiscoveredEntriesMessage.Type enumExpansion$invokeInit(String internalName, int internalId) {
        throw new AssertionError();
    }

    @Unique
    private static  DiscoveredEntriesMessage.Type enumExpansion$addVariant(String internalName) {
        ArrayList<DiscoveredEntriesMessage.Type> variants = new ArrayList<DiscoveredEntriesMessage.Type>(Arrays.asList(MixinDiscoveredEntriesMessageEnum.$VALUES));
        DiscoveredEntriesMessage.Type type = enumExpansion$invokeInit(internalName, variants.get(variants.size() - 1).ordinal() + 1);
        variants.add(type);
        MixinDiscoveredEntriesMessageEnum.$VALUES = variants.toArray(new  DiscoveredEntriesMessage.Type[0]);
        return type;
    }
}
