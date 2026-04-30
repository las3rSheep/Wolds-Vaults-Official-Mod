package xyz.iwolfking.woldsvaults.abilities;

import com.google.gson.JsonObject;
import iskallia.vault.core.data.adapter.Adapters;
import iskallia.vault.core.net.BitBuffer;
import iskallia.vault.core.vault.challenge.curse.ChallengeCurseHelper;
import iskallia.vault.gear.attribute.VaultGearAttributeInstance;
import iskallia.vault.gear.etching.EtchingHelper;
import iskallia.vault.init.ModParticles;
import iskallia.vault.init.ModSounds;
import iskallia.vault.mana.ManaAction;
import iskallia.vault.skill.ability.effect.spi.core.Ability;
import iskallia.vault.skill.ability.effect.spi.core.HoldManaAbility;
import iskallia.vault.skill.base.SkillContext;
import java.util.Optional;

import iskallia.vault.util.calc.ManaCostHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import xyz.iwolfking.woldsvaults.init.ModEffects;
import xyz.iwolfking.woldsvaults.init.ModEtchingGearAttributes;

public class LevitateAbility extends HoldManaAbility {
    private int levitateStrength;
    private boolean falseStart = false;

    public LevitateAbility(
            int unlockLevel,
            int learnPointCost,
            int regretPointCost,
            int cooldownTicks,
            float manaCostPerSecond,
            int levitateStrength
    ) {
        super(unlockLevel, learnPointCost, regretPointCost, cooldownTicks, manaCostPerSecond);
        this.levitateStrength = levitateStrength;
    }

    public LevitateAbility() {}
    public float getLevitateSpeed() {
        return ( this.levitateStrength+1 ) * 0.9f;
    }
    public int getLevitateStrength() {
        return this.levitateStrength;
    }

    protected void doParticles(SkillContext context) {
        context.getSource()
                .as(ServerPlayer.class)
                .ifPresent(
                        player -> ((ServerLevel)player.level).sendParticles(ModParticles.NOVA_CLOUD.get(), player.getX(), player.getY(), player.getZ(), 2, 0.2, 0.5, 0.2, 0.05)
                );
    }

    @Override
    public Ability.TickResult doActiveTick(SkillContext context) {
        context.getSource().as(ServerPlayer.class).map(player -> {

            if(!this.isOnCooldown())
            {
                if(falseStart) {
                    super.onKeyDown(context);
                    this.falseStart = false;
                }

                Ability.TickResult result = tickMana(context);
                if (result == Ability.TickResult.PASS) {
                    MobEffectInstance newEffect = new MobEffectInstance(ModEffects.LEVITATEII, 100, levitateStrength, false, false, true);
                    player.addEffect(newEffect);
                    if (player.hasEffect(MobEffects.SLOW_FALLING) && player.getEffect(MobEffects.SLOW_FALLING).getAmplifier() == 2) {
                        player.removeEffect(MobEffects.SLOW_FALLING);
                    }
                    doParticles(context);
                }
                return result;
            }
            return TickResult.COOLDOWN;

        });

        return(TickResult.PASS);
    }

    private TickResult tickMana(SkillContext context) {
        return context.getSource().getMana().map((mana) -> {
            float cost = this.getManaCostPerSecond() / 20.0F;
            if (mana instanceof ServerPlayer player) {
                if (player.isCreative() || player.isSpectator()) {
                    return TickResult.PASS;
                }

                cost = ManaCostHelper.adjustManaCost(player, this, cost);
            }

            if (mana.decreaseMana(ManaAction.PLAYER_ACTION, cost) <= 0.0F) {
                this.doManaDepleted(context);
                return TickResult.COOLDOWN;
            } else {
                return TickResult.PASS;
            }
        }).orElse(TickResult.PASS);
    }

    @Override
    protected void doManaDepleted(SkillContext context) {
        this.stopFloat(context);
        this.falseStart = true;
        this.setCooldown(200,0);
    }

    private void stopFloat(SkillContext context) {
        context.getSource().as(ServerPlayer.class).map(player -> {
            VaultGearAttributeInstance<Boolean> levitateEtchingAttribute = EtchingHelper.getEtchings(player, ModEtchingGearAttributes.LEVITATE_SLOW_FALLING).stream().findFirst().orElse(null);
            player.removeEffect(ModEffects.LEVITATEII);
            if(levitateEtchingAttribute != null && levitateEtchingAttribute.getValue()) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 2, false, false, true));
            }
            return true;
        });
    }

    @Override
    public boolean onKeyUp(SkillContext context) {
        this.stopFloat(context);
        this.setActive(false);
        if (!this.isOnCooldown())
            this.setCooldown(1,9);
        this.falseStart = false;
        return super.onKeyUp(context);
    }

    @Override
    public boolean onKeyDown(SkillContext context) {
        return this.isUnlocked() ? context.getSource().as(ServerPlayer.class).map((player) -> {
            if (ChallengeCurseHelper.isAbilityDisabled(player.getUUID(), this.getClass().getSimpleName())) {
                player.level.playSound(null, player, ModSounds.ABILITY_ON_COOLDOWN, SoundSource.PLAYERS, 1.0F, 1.0F);
                this.setActive(false);
                this.falseStart = false;
                return false;
            } else {
                this.setActive(true);
                this.falseStart = true;
                return true;
            }
        }).orElse(true) : false;
    }

    @Override
    public void writeBits(BitBuffer buffer) {
        super.writeBits(buffer);
        Adapters.INT_SEGMENTED_7.writeBits(Integer.valueOf(this.levitateStrength), buffer);
    }

    @Override
    public void readBits(BitBuffer buffer) {
        super.readBits(buffer);
        this.levitateStrength = Adapters.INT_SEGMENTED_7.readBits(buffer).orElseThrow();
    }

    @Override
    public Optional<CompoundTag> writeNbt() {
        return super.writeNbt().map(nbt -> {
            Adapters.INT.writeNbt(Integer.valueOf(this.levitateStrength)).ifPresent(tag -> nbt.put("levitateStrength", tag));
            return nbt;
        });
    }

    @Override
    public void readNbt(CompoundTag nbt) {
        super.readNbt(nbt);
        this.levitateStrength = Adapters.INT.readNbt(nbt.get("levitateStrength")).orElse(0);
    }

    @Override
    public Optional<JsonObject> writeJson() {
        return super.writeJson().map(json -> {
            Adapters.INT.writeJson(Integer.valueOf(this.levitateStrength)).ifPresent(element -> json.add("levitateStrength", element));
            return json;
        });
    }

    @Override
    public void readJson(JsonObject json) {
        super.readJson(json);
        this.levitateStrength = Adapters.INT.readJson(json.get("levitateStrength")).orElse(0);
    }
}