package xyz.iwolfking.woldsvaults.entities.astral;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PowerableMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class NebulaSentinelEntity extends IronGolem implements PowerableMob {
    public NebulaSentinelEntity(EntityType<? extends IronGolem> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean isPlayerCreated() {
        return false;
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.tickCount % 100 == 0 && !this.level.isClientSide) {
            double range = 10.0D;
            List<Player> players = this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(range));

            for (Player player : players) {
                Vec3 pullVec = this.position().subtract(player.position());
                double distance = pullVec.length();

                if (distance > 2.0D) {
                    Vec3 motion = pullVec.normalize().scale(0.85D).add(0, 0.2D, 0);

                    player.setDeltaMovement(motion.x, motion.y, motion.z);

                    player.hurtMarked = true;

                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 0.5F);
                }
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return IronGolem.createAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    @Override
    public boolean isPowered() {
        return true;
    }
}