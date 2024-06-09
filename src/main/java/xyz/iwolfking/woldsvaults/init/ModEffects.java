package xyz.iwolfking.woldsvaults.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.event.RegistryEvent;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.effect.mobeffects.GrowingPotionEffect;
import xyz.iwolfking.woldsvaults.effect.mobeffects.ReavingPotionEffect;
import xyz.iwolfking.woldsvaults.effect.mobeffects.ShrinkingPotionEffect;
import xyz.iwolfking.woldsvaults.lib.CustomScaleTypes;

public class ModEffects {
    public static final MobEffect GROWING = new GrowingPotionEffect(MobEffectCategory.NEUTRAL, 0xc90000, CustomScaleTypes.SIZE, new ResourceLocation(WoldsVaults.MOD_ID, "growing"));
    public static final MobEffect SHRINKING = new ShrinkingPotionEffect(MobEffectCategory.NEUTRAL, 0xcca468, CustomScaleTypes.SIZE, new ResourceLocation(WoldsVaults.MOD_ID, "shrinking"));
    public static final MobEffect REAVING = new ReavingPotionEffect(MobEffectCategory.BENEFICIAL, 0xcca468, new ResourceLocation(WoldsVaults.MOD_ID, "reaving"));
    public static void register(RegistryEvent.Register<MobEffect> event) {
        event.getRegistry().registerAll(new MobEffect[]{SHRINKING, GROWING, REAVING});
    }
}
