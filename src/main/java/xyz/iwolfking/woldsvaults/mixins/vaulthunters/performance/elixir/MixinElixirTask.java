package xyz.iwolfking.woldsvaults.mixins.vaulthunters.performance.elixir;

import iskallia.vault.core.vault.objective.elixir.ElixirTask;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ElixirTask.class, remap = false)
public class MixinElixirTask {

//    /**
//     * @author a1qs
//     * @reason why are we making ENTITIES (replaces entities with particles)
//     */
//    @Overwrite
//    public void summonOrbs(VirtualWorld world, Vec3 pos, int amount) {
//        int size = (amount < 0 ? -1 : 1) * ModConfigs.ELIXIR.getSize(Math.abs(amount));
//
//        for(int i = 0; i < Math.abs(size) / 2 + 3; i++) {
//            ModNetwork.sendToAllClients(new ElixirParticleMessage(size, new BlockPos(pos)));
//        }
//
//        world.playSound(null, new BlockPos(pos), SoundEvents.HONEY_BLOCK_BREAK, SoundSource.BLOCKS, 0.4f, 0.7f);
//    }


}
