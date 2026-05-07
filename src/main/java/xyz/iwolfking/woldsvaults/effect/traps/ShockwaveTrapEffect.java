package xyz.iwolfking.woldsvaults.effect.traps;

import com.google.gson.annotations.Expose;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.world.storage.VirtualWorld;
import iskallia.vault.world.vault.chest.VaultChestEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;

public class ShockwaveTrapEffect extends VaultChestEffect {
   @Expose private final float force;
   @Expose private final float upwardForce;

   public ShockwaveTrapEffect(String name, float force, float upwardForce) {
      super(name);
      this.force = force;
      this.upwardForce = upwardForce;
   }

   @Override
   public void apply(VirtualWorld world, Vault vault, ServerPlayer player, BlockPos chestPos) {
      Vec3 chestCenter = new Vec3(chestPos.getX() + 0.5, chestPos.getY() + 0.5, chestPos.getZ() + 0.5);
      Vec3 playerPos = player.position();

      Vec3 dir = playerPos.subtract(chestCenter).normalize();

      player.push(dir.x * force, upwardForce, dir.z * force);

      player.hurtMarked = true; 

      for (int i = 0; i < 15; i++) {
         world.sendParticles(ParticleTypes.EXPLOSION, 
            chestCenter.x, chestCenter.y, chestCenter.z, 
            1, world.random.nextGaussian() * 0.5, 0.1, world.random.nextGaussian() * 0.5, 0.2);
      }
      
      world.playSound(null, chestPos, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F, 1.5F);
      world.playSound(null, chestPos, SoundEvents.ANVIL_FALL, SoundSource.BLOCKS, 0.8F, 0.5F);
   }
}