package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.modifiers.vault.readers.WeaponTypeReader;

public class ModGearAttributeReaders {
    public static WeaponTypeReader weaponTypeReader(String modifierName, int rgbColor, String format) {
        return new WeaponTypeReader(modifierName, rgbColor, format);
    }
}