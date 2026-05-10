package xyz.iwolfking.woldsvaults.aaaWIP;

public class InsertableQueue<obj> {
    final float midqueueTarget;
    private Node front;
    private Node midqueue;

    public InsertableQueue(obj root, float mqT) {
        front = new Node(root, 0, null);
        midqueue = front;
        midqueueTarget = mqT;
    }
    public InsertableQueue(float mqT) {
        front = null;
        midqueue = null;
        midqueueTarget = mqT;
    }

    public obj poll() {
        Node pollist = front;
        front = front.next;
        return pollist.nodeval;
    }
    public boolean isEmpty() {
        return this.front == null;
    }

    public void add(obj val, float sortval) {
        if(sortval > midqueueTarget) {
            Node nNode = new Node(val, sortval);
            nNode.recurse(midqueue == null ? front : midqueue);
        } else {
            if(midqueue == null) {
                midqueue = new Node(val, sortval, front);
                front = midqueue;
            }
            else {
                Node nNode = new Node(val, sortval, midqueue.next);
                midqueue.next = nNode;
                midqueue = nNode;
            }
        }
    }
    public void forceAdd(obj val, float sortval) {
        front = new Node(val, sortval, front);
        if(midqueue == null
                && sortval <= midqueueTarget)
            midqueue = front;
    }
    public void clear() {
        this.front = null;
        this.midqueue = null;
    }

    private class Node {
        obj nodeval;
        Node next;
        float sortval;

        Node(obj nodeval, float sortval, Node next) {
            this.nodeval = nodeval;
            this.sortval = sortval;
            this.next = next;
        }
        Node(obj nodeval, float sortval) {
            this.nodeval = nodeval;
            this.sortval = sortval;
            this.next = null;
        }
        private void recurse(Node rNode) {
            if(rNode.next == null
            || rNode.next.sortval >= this.sortval) {
                this.next = rNode.next;
               rNode.next = this;
            } else
                this.recurse(rNode.next);
        }
    }
}
