package org.glagan.avaj.simulator;

public class Simulator {
    public static void main(String[] args) {
        int iterations = 25;

        WeatherTower weatherTower = new WeatherTower();
        AircraftFactory factory = new AircraftFactory();
        factory.newAircraft("Baloon", "B1", 2, 3, 20).registerTower(weatherTower);
        factory.newAircraft("Baloon", "B2", 1, 8, 66).registerTower(weatherTower);
        factory.newAircraft("JetPlane", "J1", 23, 44, 32).registerTower(weatherTower);
        factory.newAircraft("Helicopter", "H1", 654, 33, 20).registerTower(weatherTower);
        factory.newAircraft("Helicopter", "H2", 22, 33, 44).registerTower(weatherTower);
        factory.newAircraft("Helicopter", "H3", 98, 68, 99).registerTower(weatherTower);
        factory.newAircraft("Baloon", "B3", 102, 22, 34).registerTower(weatherTower);
        factory.newAircraft("JetPlane", "J2", 11, 99, 768).registerTower(weatherTower);
        factory.newAircraft("Helicopter", "H4", 223, 23, 54).registerTower(weatherTower);

        for (int i = 0; i < iterations; i++) {
            weatherTower.changeWeather();
        }
    }
}
