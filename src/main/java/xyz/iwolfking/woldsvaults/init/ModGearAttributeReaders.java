package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.modifiers.vault.readers.ObjectiveModifierReader;
import xyz.iwolfking.woldsvaults.modifiers.vault.readers.ThemeModifierReader;

public class ModGearAttributeReaders {
    public static ThemeModifierReader themeReader(String modifierName, int rgbColor, String format) {
        return new ThemeModifierReader(modifierName, rgbColor, format);
    }

    public static ObjectiveModifierReader objectiveReader(String modifierName, int rgbColor, String format) {
        return new ObjectiveModifierReader(modifierName, rgbColor, format);
    }
}
