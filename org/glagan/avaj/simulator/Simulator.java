package org.glagan.avaj.simulator;

public class Simulator {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AircraftFactory factory = new AircraftFactory();
        Flyable test = factory.newAircraft("Baloon", "Balooooon", 2, 3, 100);
        System.out.println(test);
        WeatherTower weatherTower = new WeatherTower();
        test.registerTower(weatherTower);
        test.updateConditions();
    }
}
