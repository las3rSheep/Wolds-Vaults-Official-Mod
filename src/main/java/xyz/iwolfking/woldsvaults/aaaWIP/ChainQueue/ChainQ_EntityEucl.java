package xyz.iwolfking.woldsvaults.aaaWIP.ChainQueue;

import net.minecraft.world.entity.Entity;

public class ChainQ_EntityEucl<q> extends AbstractChainQueue<Entity,q> {
    public ChainQ_EntityEucl(float minSortValue) {
        super(minSortValue);
    }
    @Override
    public float calcDistance(Entity a, Entity b) {
        return (float) a.distanceToSqr(b);
    }
}
