package xyz.iwolfking.woldsvaults.integration.vaultfilters;

import iskallia.vault.gear.attribute.VaultGearModifier;
import net.joseph.vaultfilters.attributes.abstracts.AffixAttribute;

public class UnusualPrefixAttribute extends AffixAttribute {
    public UnusualPrefixAttribute(String value) {
        super(value);
    }

    public VaultGearModifier.AffixType getAffixType() {
        return VaultGearModifier.AffixType.PREFIX;
    }

    public boolean shouldList(VaultGearModifier<?> modifier) {
        return modifier.hasCategory(VaultGearModifier.AffixCategory.valueOf("UNUSUAL"));
    }

    public boolean checkModifier(VaultGearModifier<?> modifier) {
        return modifier.hasCategory(VaultGearModifier.AffixCategory.valueOf("UNUSUAL")) && ((String)this.value).equals(getName(modifier));
    }

    public String getTranslationKey() {
        return "unusual_prefix";
    }

    public String getLegacyKey() {
        return "unusualPrefix";
    }
}
