package xyz.iwolfking.woldsvaults.aaaWIP.ChainQueue;

import xyz.iwolfking.woldsvaults.aaaWIP.CutsieQueue;

public abstract class AbstractChainQueue<k,v> extends CutsieQueue<k,v> {
    protected AbstractChainQueue(k rootKey, v rootVal, float minSortValue) {
        super(rootKey, rootVal, minSortValue);
    }
    protected AbstractChainQueue(float minSortValue) {
        super(minSortValue);
    }
    public abstract float calcDistance(k a, k b);
}

