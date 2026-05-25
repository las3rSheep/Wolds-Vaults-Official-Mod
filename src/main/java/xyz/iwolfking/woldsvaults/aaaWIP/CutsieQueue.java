package xyz.iwolfking.woldsvaults.aaaWIP;

import xyz.iwolfking.woldsvaults.WoldsVaults;

import java.util.HashMap;

/// a simple queue that allows cutsies, sorting elements according to a float supplied alongside the data, as long as the float is greater than a certain minimum value
public class CutsieQueue<k,v> {
    ///minimum value required to sort the elements instead of queueing them
    final float minSortValue;

    //doubly-linked "queue" with an extra spot where the order flips from FIFO to sorted
    Node qFront;
    Node midqueue;
    Node qBack;

    //hashmap for better searching
    HashMap<k, Node> map = new HashMap<>();

    public CutsieQueue(k rootKey, v rootVal, float minSortValue) {
        this.minSortValue = minSortValue;
        this.qFront = new Node(rootKey, rootVal, 0);
        this.midqueue = this.qFront;
        this.qBack = this.qFront;
    }
    public CutsieQueue(float minSortValue) {
        this.minSortValue = minSortValue;
        this.qFront = null;
        this.midqueue = null;
        this.qBack = null;
    }

    //public methods
    public v find(k target) {
        return findNode(target).val;
    }
    public float checkSortVal(k target) {
        Node node = findNode(target);
        if(node != null)
            return node.dist;
        else
            return Float.NaN;
    }
    public void add(k key, v val, float sortVal) {
        Node node = new Node(key, val, sortVal);
        map.put(key, node);
        qAdd(node);
    }
    public void move(k kScion, v val, float sortVal) {
//        WoldsVaults.LOGGER.info("trymove");
        if(sortVal <= minSortValue)
            return;
//        Node scion = findNode(kScion);
//        qRemove(scion);
        qRemove(findNode(kScion));
//        scion.val = val;
//        scion.dist = sortVal;
//        qAdd(scion);
        qAdd(new Node(kScion, val, sortVal));
    }
    public ReturnData poll() {
        if(qFront == null)
            return null;
        Node polled = qFront;
        qFront = qFront.toBak;
        qRemove(polled);
        map.remove(polled.key);
        return new ReturnData(polled);
    }
    public boolean isEmpty() {
        return qFront == null;
    }
    public void esplod() {
        map.clear();
        qFront = null;
        midqueue = null;
        qBack = null;
    }

    //internal methods
    protected Node findNode(k target) {
//        Node it = qFront;
//        while(it != null && it.key != target)
//            it = it.toBak;
//        return it;
        return map.get(target);
    }

    private void qAdd(Node node) {
//        WoldsVaults.LOGGER.info("qAdd {} with {}, at {}",node.key, node.val, node.dist);
        float dist = node.dist;
        if(dist <= minSortValue) {
            if(midqueue != null) {
                qInsert(node, midqueue);
                midqueue = node;
            }
            else {
                qInsert(qFront, node);
                midqueue = node;
                qFront = node;
            }
        } else {
            Node search = qBack;
            while(search != null && dist < search.dist)
                search = search.toFwd;
            if(search != null)
                qInsert(node, search);
            else {
                qInsert(qFront, node);
                qFront = node;
            }
        }
        if(qBack == null)
            qBack = node;
    }
    private void qInsert(Node qMe, Node behindThis) {
        Node infrontOfThis = null;

        if(behindThis != null) {
            infrontOfThis = behindThis.toBak;
            behindThis.toBak = qMe;
        }
        if(qMe != null) {
            qMe.toFwd = behindThis;
            qMe.toBak = infrontOfThis;
        }
        if(infrontOfThis != null)
           infrontOfThis.toFwd = qMe;
        if(behindThis == qBack)
            qBack = qMe;
    }

    private void qRemove(Node node) {
        if(node == midqueue)
            midqueue = node.toFwd;
        if(node == qFront)
            qFront = node.toBak;
        if(node == qBack)
            qBack = node.toFwd;

        if(node.toFwd != null)
            node.toFwd.toBak = node.toBak;
        if(node.toBak != null)
            node.toBak.toFwd = node.toFwd;
        node.toFwd = null;
        node.toBak = null;
    }

    //classes
    protected class Node {
        k key;
        v val;
        Node toFwd = null;
        Node toBak = null;
        float dist;

        public Node(k key, v val, float dist) {
            this.key = key;
            this.val = val;
            this.dist = dist;
        }
    }

    public class ReturnData {
        public final k key;
        public final v val;
        public final float dist;

        protected ReturnData(Node node) {
            this.key = node.key;
            this.val = node.val;
            this.dist = node.dist;
        }
    }
}
