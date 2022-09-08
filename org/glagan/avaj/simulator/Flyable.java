package org.glagan.avaj.simulator;

public interface Flyable {
    public String identifier();

    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);
}
