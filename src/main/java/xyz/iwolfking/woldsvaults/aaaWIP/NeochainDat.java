package xyz.iwolfking.woldsvaults.aaaWIP;

public class NeochainDat<hi> {
    float dist;
    hi from;

    public NeochainDat() {
        this.dist = 0;
        this.from = null;
    }
    public NeochainDat(float dist, hi from) {
        this.dist = dist;
        this.from = from;
    }

    public float getDist() {
        return dist;
    }
    public void setDist(float dist) {
        this.dist = dist;
    }

    public hi getFrom() {
        return from;
    }
    public void setFrom(hi from) {
        this.from = from;
    }
}
