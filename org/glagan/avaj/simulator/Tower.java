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
        Output.write("Tower says: " + flyable.identifier() + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
        Output.write("Tower says: " + flyable.identifier() + " unregistered from weather tower.");
    }

    protected void conditionsChanged() {
        for (int i = 0; i < this.observers.size(); i++) {
            int previousSize = this.observers.size();
            Flyable flyable = this.observers.get(i);
            flyable.updateConditions();
            if (this.observers.size() != previousSize) {
                i -= 1;
            }
        }
    }
}
