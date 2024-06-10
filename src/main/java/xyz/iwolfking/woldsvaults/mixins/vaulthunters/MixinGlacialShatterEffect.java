package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import atomicstryker.infernalmobs.common.InfernalMobsCore;
import iskallia.vault.effect.GlacialShatterEffect;
import iskallia.vault.entity.champion.ChampionLogic;
import iskallia.vault.event.ActiveFlags;
import iskallia.vault.event.ActiveFlagsCheck;
import iskallia.vault.init.ModEffects;
import iskallia.vault.util.ServerScheduler;
import iskallia.vault.util.damage.AttackScaleHelper;
import iskallia.vault.util.damage.CritHelper;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = GlacialShatterEffect.class, remap = false)
public class MixinGlacialShatterEffect {
    /**
     * @author iwolfking
     * @reason Make Glacial Shatter do 1/4 of Boss health
     */
    @Overwrite
    public static void on(LivingHurtEvent event) {
        Entity var2 = event.getEntity();
        if (var2 instanceof Mob mob) {
            if (!ActiveFlags.IS_AOE_ATTACKING.isSet()) {
                if (!ActiveFlags.IS_TOTEM_ATTACKING.isSet()) {
                    if (!ActiveFlags.IS_CHARMED_ATTACKING.isSet()) {
                        if (!ActiveFlags.IS_DOT_ATTACKING.isSet()) {
                            if (!ActiveFlags.IS_REFLECT_ATTACKING.isSet()) {
                                if (!ActiveFlags.IS_EFFECT_ATTACKING.isSet()) {
                                    Entity var3 = event.getSource().getEntity();
                                    if (var3 instanceof ServerPlayer) {
                                        ServerPlayer player = (ServerPlayer) var3;
                                        if (ActiveFlagsCheck.checkIfFullSwingAttack() && !ActiveFlags.IS_CHAINING_ATTACKING.isSet()) {
                                            if (CritHelper.getCrit(player)) {
                                                return;
                                            }

                                            if (AttackScaleHelper.getLastAttackScale(player) < 1.0F) {
                                                return;
                                            }
                                        }

                                        MobEffectInstance effectInstance = mob.getEffect(ModEffects.GLACIAL_SHATTER);
                                        if (effectInstance != null) {
                                            ServerScheduler.INSTANCE.schedule(1, () -> {
                                                Entity entity = event.getEntity();
                                                if (event.getEntity() != null) {
                                                    BlockParticleOption particle = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.PACKED_ICE.defaultBlockState());
                                                    ((ServerLevel) event.getEntity().getLevel()).sendParticles(particle, entity.position().x, entity.position().y + (double) (mob.getBbHeight() / 2.0F), entity.position().z, 200, (double) (mob.getBbWidth() / 2.0F), (double) (mob.getBbHeight() / 2.0F), (double) (mob.getBbWidth() / 2.0F), 1.5);
                                                    event.getEntity().getLevel().playSound((Player) null, event.getEntity(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 0.75F);
                                                    if (ChampionLogic.isChampion(event.getEntity()) || InfernalMobsCore.getMobModifiers(event.getEntityLiving()) != null && InfernalMobsCore.getMobModifiers(event.getEntityLiving()).getModSize() != 0) {
                                                        ActiveFlags.IS_GLACIAL_SHATTER_ATTACKING.runIfNotSet(() -> {
                                                            event.getEntity().hurt(DamageSource.playerAttack(player), ((Mob) event.getEntity()).getMaxHealth() * 0.25F);
                                                        });
                                                        if (event.getEntity().isAlive()) {
                                                            event.getEntityLiving().removeEffect(ModEffects.GLACIAL_SHATTER);
                                                        }
                                                    } else {
                                                        ActiveFlags.IS_GLACIAL_SHATTER_ATTACKING.runIfNotSet(() -> {
                                                            event.getEntity().hurt(DamageSource.playerAttack(player), ((Mob) event.getEntity()).getMaxHealth() * 1.5F);
                                                        });
                                                    }

                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
