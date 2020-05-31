package cn.xpbootcamp.locker_robot;

public class Box {
    private Bag bag;

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    boolean isAvailable() {
        return bag == null;
    }

    boolean storeBag(Bag bag) {
        if (isAvailable()) {
            setBag(bag);
            return true;
        } else {
            return false;
        }
    }

    Bag takeOut() {
        Bag bag = getBag();
        setBag(null);
        return bag;
    }
}
