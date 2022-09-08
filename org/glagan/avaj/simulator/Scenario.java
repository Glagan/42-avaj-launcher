package org.glagan.avaj.simulator;

public class Scenario {
    private WeatherTower weatherTower;
    private int iterations;

    public Scenario(int iterations) {
        this.weatherTower = new WeatherTower();
        this.iterations = iterations;
    }

    public void registerAircraft(Flyable flyable) {
        flyable.registerTower(this.weatherTower);
    }

    public void run() {
        for (int i = 0; i < iterations; i++) {
            this.weatherTower.changeWeather();
        }
    }
}
