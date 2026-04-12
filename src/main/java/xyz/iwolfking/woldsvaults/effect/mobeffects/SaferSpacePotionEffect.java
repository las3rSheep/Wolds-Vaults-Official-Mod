package xyz.iwolfking.woldsvaults.effect.mobeffects;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.init.ModNetwork;
import xyz.iwolfking.woldsvaults.network.message.SaferSpaceParticleMessage;

@Mod.EventBusSubscriber(
        modid = WoldsVaults.MOD_ID
)
public class SaferSpacePotionEffect extends MobEffect {
    public SaferSpacePotionEffect(MobEffectCategory mobEffectCategory, int color, ResourceLocation id) {
        super(mobEffectCategory, color);
        this.setRegistryName(id);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {}
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) { return false;}

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent()
    public static void onSafeSpaceExpire(PotionEvent.PotionExpiryEvent event) {
        //procs when saferspaces reenables *or* when its total duration runs out

        MobEffectInstance effect = event.getPotionEffect();
        if(effect != null
        && effect.getEffect() instanceof SaferSpacePotionEffect
        && effect.isVisible())
            ModNetwork.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(event::getEntityLiving)
                , new SaferSpaceParticleMessage(event.getEntityLiving().getUUID()
                    , SaferSpaceParticleMessage.Reason.REMOVE));
    }

    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent()
    public static void onSafeSpaceAdded(PotionEvent.PotionAddedEvent event) {
        //procs when saferspaces refreshes its duration, or when it blocks damage

        if(event.getPotionEffect().getEffect() instanceof SaferSpacePotionEffect) {
            if(event.getPotionEffect().isVisible()) {
                ModNetwork.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(event::getEntityLiving)
                    , new SaferSpaceParticleMessage(event.getEntityLiving().getUUID()
                        , SaferSpaceParticleMessage.Reason.MAKE_REFRESH));//activate, refresh, reactivate
            } else
                ModNetwork.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(event::getEntityLiving)
                    , new SaferSpaceParticleMessage(event.getEntityLiving().getUUID()
                        , SaferSpaceParticleMessage.Reason.HIDE));//block
        }
    }
}
