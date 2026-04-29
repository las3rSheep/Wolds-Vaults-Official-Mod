package xyz.iwolfking.woldsvaults.effect.mobeffects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import xyz.iwolfking.woldsvaults.init.ModEffects;
import xyz.iwolfking.woldsvaults.mixins.accessors.MobEffectInstanceAccessor;

public class EchoingEffectInstance extends MobEffectInstance {

    private Player attacker = null;
    float damage = 0;
    DamageSource source;

    public Player getAttacker() {
        return this.attacker;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
    public float getDamage() {
        return this.damage;
    }

    public DamageSource getSource() {
        return this.source;
    }

    public EchoingEffectInstance(EchoingEffectInstance echo) {
        this(echo.attacker, echo.damage, echo.source, echo.getDuration());
    }
    public EchoingEffectInstance(Player attacker, float damage, DamageSource source, int pDuration) {
        super(ModEffects.ECHOING, pDuration);
        this.attacker = attacker;
        this.damage = damage;
        this.source = source;
    }

    void setDetailsFrom(EchoingEffectInstance echo) {
        ((MobEffectInstanceAccessor) this).setDetailsFrom(echo);
        this.attacker = echo.attacker;
        this.damage = echo.damage;
        this.source = echo.source;
    }

    @Override
    public boolean tick(LivingEntity pEntity, Runnable pOnExpirationRunnable) {
        if (this.getDuration() > 0) {
            if (this.getEffect().isDurationEffectTick(this.getDuration(),0)) {
                this.applyEffect(pEntity);
            }
            MobEffectInstanceAccessor thiis = ((MobEffectInstanceAccessor) this);

            thiis.tickDownDuration();
            if (this.getDuration() == 0 && thiis.getHiddenEffect() != null) {
                this.setDetailsFrom((EchoingEffectInstance) thiis.getHiddenEffect());
                thiis.setHiddenEffect(((MobEffectInstanceAccessor) thiis.getHiddenEffect()).getHiddenEffect());
                pOnExpirationRunnable.run();
            }
        }

        return this.getDuration() > 0;
    }

    @Override
    public boolean update(MobEffectInstance other) {
        if(other instanceof EchoingEffectInstance oEcho) {
            boolean didUpdate = false;

            MobEffectInstanceAccessor thiis = (MobEffectInstanceAccessor) this;
            EchoingEffectInstance hidden = (EchoingEffectInstance) thiis.getHiddenEffect();

            if(oEcho.damage > this.damage) {
                if(this.getDuration() < oEcho.getDuration()) {
                    thiis.setHiddenEffect(new EchoingEffectInstance(this));
                    ((MobEffectInstanceAccessor) hidden).setHiddenEffect(hidden);
                }

                this.damage = oEcho.damage;
                thiis.setDuration(oEcho.getDuration());
                didUpdate = true;
            } else if (oEcho.getDuration() > this.getDuration()) {
                if (this.damage == oEcho.damage) {
                    thiis.setDuration(oEcho.getDuration());
                    didUpdate = true;
                } else if (hidden == null)
                    thiis.setHiddenEffect(new EchoingEffectInstance(oEcho));
                else
                    hidden.update(oEcho);
            }

            return didUpdate;
        }
        else
            return super.update(other);
    }
}
