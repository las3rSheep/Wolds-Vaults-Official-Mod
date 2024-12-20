package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.modifiers.vault.readers.ThemeModifierReader;

public class ModGearAttributeReaders {
    public static ThemeModifierReader themeReader(String modifierName, int rgbColor, String format) {
        return new ThemeModifierReader(modifierName, rgbColor, format);
    }
}
