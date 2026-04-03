package xyz.iwolfking.woldsvaults.api.util;

import iskallia.vault.entity.entity.EffectCloudEntity;
import iskallia.vault.gear.attribute.custom.effect.EffectCloudAttribute;
import iskallia.vault.util.calc.AreaOfEffectHelper;
import iskallia.vault.util.calc.EffectDurationHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;

import java.util.List;
import java.util.Random;

public class EffectCloudHelper {
    public static void spawnRandomCloud(ServerPlayer player, List<? extends EffectCloudAttribute> clouds, double x, double y, double z) {
        if (clouds.isEmpty()) return;

        var cloud = clouds.get(new Random().nextInt(clouds.size()));
        MobEffect effect = cloud.getPrimaryEffect();

        if (effect != null) {
            EffectCloudEntity cloudEntity = new EffectCloudEntity(
                    player.getLevel(),
                    x, y, z
            );
            cloud.apply(cloudEntity);
            cloudEntity.setRadius(AreaOfEffectHelper.adjustAreaOfEffect(player, null, cloudEntity.getRadius()));
            cloudEntity.setOwner(player);
            cloudEntity.setDuration(EffectDurationHelper.adjustEffectDurationFloor(player, cloudEntity.getDuration()));
            player.getLevel().addFreshEntity(cloudEntity);
        }
    }
}
