package org.glagan.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private List<Flyable> observers;

    public Tower() {
        this.observers = new ArrayList<Flyable>();
    }

    public void register(Flyable flyable) {
        this.observers.add(flyable);
        System.out.println("Tower says: " + flyable.identifier() + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
        System.out.println("Tower says: " + flyable.identifier() + " unregistered from weather tower.");
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}
