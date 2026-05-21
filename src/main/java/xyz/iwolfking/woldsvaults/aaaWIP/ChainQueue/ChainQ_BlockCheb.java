package xyz.iwolfking.woldsvaults.aaaWIP.ChainQueue;

import net.minecraft.core.BlockPos;

public class ChainQ_BlockCheb<q> extends AbstractChainQueue<BlockPos,q> {
    public ChainQ_BlockCheb(float minSortValue) {
        super(minSortValue);
    }
    @Override
    public float calcDistance(BlockPos a, BlockPos b) {
        float x = Math.abs(a.getX() - b.getX());
        float y = Math.abs(a.getY() - b.getY());
        float z = Math.abs(a.getZ() - b.getZ());
        return x>y ? x>z?x:z : y>z?y:z; //Math#max is only for two numbers >:c
    }
}
