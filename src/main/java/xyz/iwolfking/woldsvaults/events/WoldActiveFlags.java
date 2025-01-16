package xyz.iwolfking.woldsvaults.events;

public enum WoldActiveFlags {
    IS_REAVING_ATTACKING,
    IS_ECHOING_ATTACKING;

    private final ThreadLocal<Integer> activeReferences = ThreadLocal.withInitial(() -> 0);

    public boolean isSet() {
        return this.activeReferences.get() > 0;
    }

    public synchronized void runIfNotSet(Runnable run) {
        if (!this.isSet()) {
            this.push();

            try {
                run.run();
            } finally {
                this.pop();
            }
        }
    }

    public synchronized void push() {
        this.activeReferences.set(this.activeReferences.get() + 1);
    }
    public synchronized void pop() {
        this.activeReferences.set(this.activeReferences.get() - 1);
    }
}