package xyz.iwolfking.woldsvaults.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(targets = "net/minecraft/world/level/storage/loot/functions/ApplyBonusCount$OreDrops")
public class MixinFortuneOreDropFormula {
    /**
     * @author iwolfking
     * @reason Modify fortune formula
     */
    @Overwrite
    public int calculateNewCount(Random rand, int baseCount, int eLevel) {
        if (eLevel > 0) {
            int bonus = rand.nextInt(Math.min(eLevel, 7) + 2) - 1;
            if (bonus < 0) {
                bonus = 0;
            }

            return baseCount * (bonus + 1);
        } else {
            return baseCount;
        }
    }
}
