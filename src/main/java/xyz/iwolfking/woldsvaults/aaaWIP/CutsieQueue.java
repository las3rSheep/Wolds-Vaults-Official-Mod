package xyz.iwolfking.woldsvaults.aaaWIP;

/// a simple queue that allows cutsies, sorting elements according to a float supplied alongside the data, as long as the float is greater than a certain minimum value
public class CutsieQueue<k,v> {
    ///minimum value required to sort the elements instead of queueing them
    final float minSortValue;

    //doubly-linked "queue" with an extra spot where the order flips from FIFO to sorted
    Node qFront;
    Node midqueue;
    Node qBack;

    public CutsieQueue(k rootKey, v rootVal, float minSortValue) {
        this.minSortValue = minSortValue;
        this.qFront = new Node(rootKey, rootVal, 0);
        this.midqueue = this.qFront;
        this.qBack = this.qFront;
    }

    //public methods
    public v find(k target) {
        return findNode(target).val;
    }
    public void add(k key, v val, float sortVal) {
        qAdd( new Node(key, val, sortVal));
    }
    public void move(k kScion, v val, float sortVal) {
        Node scion = findNode(kScion);
        qRemove(scion);
        scion.val = val;
        scion.dist = sortVal;
        qAdd(scion);
    }
    public returnData poll() {
        if(qFront == null)
            return null;
        Node polled = qFront;
        qFront = qFront.toBak;
        qRemove(polled);
        return new returnData(polled);
    }

    //internal methods
    protected Node findNode(k target) {
        Node it = qFront;
        while(it != null && it.key != target)
            it = it.toBak;
        return it;
    }

    private void qAdd(Node node) {
        float dist = node.dist;
        if(dist <= minSortValue) {
            if(midqueue != null) {
                qInsert(node, midqueue);
                midqueue = node;
            }
            else {
                qInsert(qFront, node);
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
        qMe.toFwd = behindThis;
        qMe.toBak = infrontOfThis;
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

    public class returnData {
        public final k key;
        public final v val;

        protected returnData(Node node) {
            this.key = node.key;
            this.val = node.val;
        }
    }
}
