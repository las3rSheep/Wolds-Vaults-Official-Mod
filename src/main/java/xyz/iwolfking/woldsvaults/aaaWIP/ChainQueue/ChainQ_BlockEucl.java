package xyz.iwolfking.woldsvaults.aaaWIP.ChainQueue;

import net.minecraft.core.BlockPos;

public class ChainQ_BlockEucl<q> extends AbstractChainQueue<BlockPos,q> {
    public ChainQ_BlockEucl(BlockPos rootKey, q rootVal, float minSortValue) {
        super(rootKey, rootVal, minSortValue);
    }
    public ChainQ_BlockEucl(float minSortValue) {
        super(minSortValue);
    }

    @Override
    public float calcDistance(BlockPos a, BlockPos b) {
        float x = a.getX() - b.getX();
        float y = a.getY() - b.getY();
        float z = a.getZ() - b.getZ();
        return x*x + y*y + z*z;
    }
}
