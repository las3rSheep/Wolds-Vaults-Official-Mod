package xyz.iwolfking.woldsvaults.aaaWIP.ChainQueue;

import net.minecraft.core.BlockPos;

public class ChainQ_BlockManh<q> extends AbstractChainQueue<BlockPos,q> {
    public ChainQ_BlockManh(float minSortValue) {
        super(minSortValue);
    }
    @Override
    public float calcDistance(BlockPos a, BlockPos b) {
        return a.distManhattan(b);
    }
}
