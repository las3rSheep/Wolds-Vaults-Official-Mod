package xyz.iwolfking.woldsvaults.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import iskallia.vault.gear.attribute.VaultGearAttributeInstance;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.reader.VaultGearModifierReader;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.iwolfking.woldsvaults.events.WoldActiveFlags;

public class MutableModifierReader<T> extends VaultGearModifierReader<T> {

    public MutableModifierReader(WoldActiveFlags useAlternate, VaultGearModifierReader<T> original, VaultGearModifierReader<T> alternate) {
        super(null,0);
        this.useAlternate = useAlternate;
        this.original = original;
        this.alternate = alternate;
    }

    private final WoldActiveFlags useAlternate;
    private final VaultGearModifierReader<T> original;
    private final VaultGearModifierReader<T> alternate;

    @Nullable
    public MutableComponent getValueDisplay(T t) {
        return useAlternate.isSet()
                ? alternate.getValueDisplay(t)
                : original.getValueDisplay(t);
    }

    protected void serializeTextElements(JsonArray out, VaultGearAttributeInstance<T> instance, VaultGearModifier.AffixType type) {}

    @Nullable
    public MutableComponent getDisplay(VaultGearAttributeInstance<T> vaultGearAttributeInstance, VaultGearModifier.AffixType affixType) {
        return useAlternate.isSet()
                ? alternate.getDisplay(vaultGearAttributeInstance, affixType)
                : original.getDisplay(vaultGearAttributeInstance, affixType);
    }

    @NotNull
    public JsonObject serializeDisplay(VaultGearAttributeInstance<T> instance, VaultGearModifier.AffixType type) {
        return original.serializeDisplay(instance, type);
    }

}
