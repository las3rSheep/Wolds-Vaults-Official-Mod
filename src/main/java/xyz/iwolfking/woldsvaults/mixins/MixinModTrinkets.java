//package xyz.iwolfking.woldsvaults.mixins;
//
//import com.llamalad7.mixinextras.sugar.Local;
//import com.llamalad7.mixinextras.sugar.ref.LocalRef;
//import iskallia.vault.VaultMod;
//import iskallia.vault.gear.trinket.TrinketEffect;
//import iskallia.vault.gear.trinket.effects.PotionEffectTrinket;
//import iskallia.vault.init.ModTrinkets;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.registries.IForgeRegistry;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import xyz.iwolfking.woldsvaults.effects.trinkets.HeadlampTrinketEffect;
//
//@Mixin(value = ModTrinkets.class, remap = false)
//public class MixinModTrinkets {
//
//    @Unique
//    private static PotionEffectTrinket MINERS_LAMP;
//
//    //@Unique
//   // private static PotionEffectTrinket RUNNING_SHOES;
//
//
//    @Inject(method = "init", at = @At("TAIL"))
//    private static void init(RegistryEvent.Register<TrinketEffect<?>> event, CallbackInfo ci, @Local LocalRef<IForgeRegistry<TrinketEffect<?>>> registry) {
//        registry.get().register(MINERS_LAMP);
//        //registry.get().register(RUNNING_SHOES);
//    }
//
//    static {
//        MINERS_LAMP =  new HeadlampTrinketEffect(VaultMod.id("miners_headlamp"), MobEffects.NIGHT_VISION, 1);
//        //RUNNING_SHOES =  new PotionEffectTrinket(VaultMod.id("running_shoes"), Effects.INEXHAUSTIBLE, 1);
//
//    }
//}
