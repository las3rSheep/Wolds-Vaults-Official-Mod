package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.core.data.compound.UUIDList;
import iskallia.vault.core.event.CommonEvents;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.modifier.modifier.MobFrenzyModifier;
import iskallia.vault.core.vault.modifier.spi.EntityAttributeModifier;
import iskallia.vault.core.vault.modifier.spi.ModifierContext;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.core.vault.modifier.spi.predicate.IModifierImmunity;
import iskallia.vault.core.vault.objective.KillBossObjective;
import iskallia.vault.core.vault.objective.ObeliskObjective;
import iskallia.vault.core.world.storage.VirtualWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(value = MobFrenzyModifier.class, remap = false)
public class MixinFrenzyModifier extends VaultModifier<MobFrenzyModifier.Properties> {
    @Shadow @Final private EntityAttributeModifier<?> attackDamageAttributeModifier;
    @Shadow @Final private EntityAttributeModifier<?> movementSpeedAttributeModifier;

    public MixinFrenzyModifier(ResourceLocation id, MobFrenzyModifier.Properties properties, Display display) {
        super(id, properties, display);
    }

    /**
     * @author iwolfking
     * @reason Change frenzy to be health multiplier instead of setting value
     */
    @Overwrite
    public void initServer(VirtualWorld world, Vault vault, ModifierContext context) {
        CommonEvents.ENTITY_SPAWN.register(context.getUUID(), (event) -> {
            Entity patt2556$temp = event.getEntity();
            if (patt2556$temp instanceof LivingEntity entity) {
                if (!IModifierImmunity.of(entity).test((MobFrenzyModifier)(Object)this)) {
                    if (entity.level == world && !(entity instanceof Player)) {
                        long upperBits = context.getUUID().getMostSignificantBits();
                        long lowerBits = context.getUUID().getLeastSignificantBits();
                        this.attackDamageAttributeModifier.applyToEntity(entity, new UUID(upperBits++, lowerBits), context);
                        this.movementSpeedAttributeModifier.applyToEntity(entity, new UUID(upperBits, lowerBits), context);
                    }
                }
            }
        });
        CommonEvents.ENTITY_TICK.register(context.getUUID(), (event) -> {
            LivingEntity entity = event.getEntityLiving();
            if (entity.level == world && !(entity instanceof Player)) {
                if (!IModifierImmunity.of(entity).test((MobFrenzyModifier)(Object)this)) {
                    boolean isBoss = (Boolean) vault.map(Vault.OBJECTIVES, (objectives) -> {
                        return objectives.forEach(KillBossObjective.class, (objective) -> {
                            UUID bossId = (UUID) objective.get(KillBossObjective.BOSS_ID);
                            return event.getEntity().getUUID().equals(bossId);
                        }) || objectives.forEach(ObeliskObjective.class, (objective) -> {
                            ObeliskObjective.Wave[] waves = (ObeliskObjective.Wave[]) objective.get(ObeliskObjective.WAVES);
                            ObeliskObjective.Wave[] var3 = waves;
                            int var4 = waves.length;

                            for (int var5 = 0; var5 < var4; ++var5) {
                                ObeliskObjective.Wave wave = var3[var5];
                                if (((UUIDList) wave.get(ObeliskObjective.Wave.MOBS)).contains(entity.getUUID())) {
                                    return true;
                                }
                            }

                            return false;
                        });
                    }, false);
                    if (!isBoss && entity.getHealth() > ((MobFrenzyModifier.Properties) this.properties).getMaxHealth()) {
                        entity.setHealth((entity.getMaxHealth() * this.properties.getMaxHealth()));
                    }

                }
            }
        });
    }
}
