package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.integration.vaultfilters.HasUnusualAffixAttribute;
import xyz.iwolfking.woldsvaults.integration.vaultfilters.UnusualPrefixAttribute;
import xyz.iwolfking.woldsvaults.integration.vaultfilters.UnusualSuffixAttribute;

public class ModVaultFilterAttributes {
    public static void initAttributes() {
        new HasUnusualAffixAttribute(true).register(HasUnusualAffixAttribute::new);
        new UnusualPrefixAttribute("Attack Damage").register(UnusualPrefixAttribute::new);
        new UnusualSuffixAttribute("Attack Damage").register(UnusualSuffixAttribute::new);
    }
}
