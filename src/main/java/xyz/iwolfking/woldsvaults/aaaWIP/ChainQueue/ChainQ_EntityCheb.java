package xyz.iwolfking.woldsvaults.aaaWIP.ChainQueue;

import net.minecraft.world.entity.Entity;

public class ChainQ_EntityCheb<q> extends AbstractChainQueue<Entity,q> {
    public ChainQ_EntityCheb(float minSortValue) {
        super(minSortValue);
    }
    @Override
    public float calcDistance(Entity a, Entity b) {
        float x = (float) Math.abs(a.getX() - b.getX());
        float y = (float) Math.abs(a.getY() - b.getY());
        float z = (float) Math.abs(a.getZ() - b.getZ());
        return x>y ? x>z?x:z : y>z?y:z; //Math#max is only for two numbers >:c
    }
}
