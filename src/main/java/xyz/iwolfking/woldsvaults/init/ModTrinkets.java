package xyz.iwolfking.woldsvaults.init;

import com.alrex.parcool.common.potion.Effects;
import iskallia.vault.VaultMod;
import iskallia.vault.gear.trinket.TrinketEffect;
import iskallia.vault.gear.trinket.effects.PotionEffectTrinket;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.spongepowered.asm.mixin.Unique;
import xyz.iwolfking.woldsvaults.effect.HeadlampTrinketEffect;

public class ModTrinkets {
    @Unique
    private static HeadlampTrinketEffect MINERS_LAMP;

    //@Unique
    private static final PotionEffectTrinket RUNNING_SHOES;


    public static void init(RegistryEvent.Register<TrinketEffect<?>> event) {
        IForgeRegistry<TrinketEffect<?>> registry = event.getRegistry();
        registry.register(MINERS_LAMP);
        registry.register(RUNNING_SHOES);
    }

    static {
        MINERS_LAMP =  new HeadlampTrinketEffect(VaultMod.id("miners_headlamp"), MobEffects.NIGHT_VISION, 1);
        RUNNING_SHOES =  new PotionEffectTrinket(VaultMod.id("running_shoes"), Effects.INEXHAUSTIBLE, 1);

    }
}
