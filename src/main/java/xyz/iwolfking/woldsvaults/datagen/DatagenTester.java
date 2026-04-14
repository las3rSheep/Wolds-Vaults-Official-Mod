package xyz.iwolfking.woldsvaults.datagen;

import me.fallenbreath.conditionalmixin.api.mixin.ConditionTester;

public class DatagenTester implements ConditionTester {
    @Override public boolean isSatisfied(String mixinClassName) {
        return System.getenv("WV_DATAGEN") != null;
    }
}
