package xyz.iwolfking.woldsvaults.objectives.events;

import iskallia.vault.core.random.JavaRandom;
import iskallia.vault.core.random.RandomSource;
import iskallia.vault.core.util.WeightedList;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

import javax.annotation.Nullable;

public class SpawnEntityEnchantedEvent extends BasicEnchantedEvent {

    private final WeightedList<EntityType<?>> entities;
    private final WeightedList<Integer> amounts;


    public SpawnEntityEnchantedEvent(String eventName, String eventDescription, ChatFormatting primaryColor, ChatFormatting secondaryColor, WeightedList<EntityType<?>> entities, WeightedList<Integer> amounts) {
        super(eventName, eventDescription, primaryColor, secondaryColor);
        this.entities = entities;
        this.amounts = amounts;
    }

    @Override
    public void triggerEvent(BlockPos pos, ServerPlayer player, Vault vault) {
        JavaRandom javaRandom = JavaRandom.ofNanoTime();
        for(int i = 0; i < amounts.getRandom().get(); i++) {
            doSpawn((VirtualWorld) player.level, pos, javaRandom);
        }

        super.triggerEvent(pos, player, vault);
    }

    public LivingEntity doSpawn(VirtualWorld world, BlockPos pos, RandomSource random) {
        double min = 10.0;
        double max = 13.0;

        LivingEntity spawned;
        int x;
        int z;
        int y;
        for(spawned = null; spawned == null; spawned = spawnMob(world, pos.getX() + x, pos.getY() + y, pos.getZ() + z, random)) {
            double angle = 6.283185307179586 * random.nextDouble();
            double distance = Math.sqrt(random.nextDouble() * (max * max - min * min) + min * min);
            x = (int)Math.ceil(distance * Math.cos(angle));
            z = (int)Math.ceil(distance * Math.sin(angle));
            double xzRadius = Math.sqrt((double)(x * x + z * z));
            double yRange = Math.sqrt(max * max - xzRadius * xzRadius);
            y = random.nextInt((int)Math.ceil(yRange) * 2 + 1) - (int)Math.ceil(yRange);
        }

        return spawned;
    }

    @Nullable
    public LivingEntity spawnMob(VirtualWorld world, int x, int y, int z, RandomSource random) {
        Entity entity = null;
        EntityType<?> type = null;
        if(entities.getRandom().isPresent()) {
           type = entities.getRandom().get();
        }

        if(type != null) {
            entity = type.create(world);
        }

        entity.setGlowingTag(true);
        BlockState state = world.getBlockState(new BlockPos(x, y - 1, z));
        if (!state.isValidSpawn(world, new BlockPos(x, y - 1, z), entity.getType())) {
            return null;
        } else {
            AABB entityBox = entity.getType().getAABB((double)x + 0.5, (double)y, (double)z + 0.5);
            if (!world.noCollision(entityBox)) {
                return null;
            } else {
                entity.moveTo((double)((float)x + 0.5F), (double)((float)y + 0.2F), (double)((float)z + 0.5F), (float)(random.nextDouble() * 2.0 * Math.PI), 0.0F);
               // entity.finalizeSpawn(world, new DifficultyInstance(Difficulty.PEACEFUL, 13000L, 0L, 0.0F), MobSpawnType.STRUCTURE, (SpawnGroupData)null, (CompoundTag)null);
                world.addWithUUID(entity);

                return (LivingEntity) entity;
            }
        }
    }
}
