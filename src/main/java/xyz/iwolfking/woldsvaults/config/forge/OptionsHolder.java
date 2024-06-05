package xyz.iwolfking.woldsvaults.config.forge;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

public class OptionsHolder
{
    public static class Common
    {
        public final ForgeConfigSpec.ConfigValue<Boolean> disableFlightInVaults;
        public final ForgeConfigSpec.ConfigValue<Boolean> enableDebugMode;
        public Common(ForgeConfigSpec.Builder builder)
        {
            builder.push("Vault Settings");
            this.disableFlightInVaults= builder.comment("Controls whether Creative flight should be blocked while inside a vault.")
                    .define("disableFlightInVaults", true);
            builder.pop();
            builder.push("Developer Settings");
            this.enableDebugMode= builder.comment("Don't recommend turning on unless asked, enables debug messages for development.")
                    .define("enableDebugMode", false);
            builder.pop();
        }
    }

    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static //constructor
    {
        Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
    }

    public static String getConfigString(String categoryName, String keyName) {
        ForgeConfigSpec.ConfigValue<String> value = COMMON_SPEC.getValues().get(Arrays.asList(categoryName, keyName));
        return value.get();
    }
}