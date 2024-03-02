package xyz.iwolfking.woldsvaults.events;

import cofh.core.init.CoreMobEffects;
import iskallia.vault.world.data.ServerVaults;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.WoldsVaults;

@Mod.EventBusSubscriber(
        modid = WoldsVaults.MOD_ID
)
public class LivingEntityEvents {

    @SubscribeEvent
    public static void capLightningDamageInVaults(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof Player player && event.getSource().equals(DamageSource.LIGHTNING_BOLT)) {
            ServerVaults.get(player.level).ifPresent(vault -> {
                event.setAmount( Math.min(event.getAmount(), player.getMaxHealth() * 0.2F));
                player.addEffect(new MobEffectInstance(CoreMobEffects.SHOCKED.get(), 80, 0));
            });
        }
    }
}
