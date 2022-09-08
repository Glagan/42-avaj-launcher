package org.glagan.avaj.simulator;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (type == "Baloon") {
            return new Baloon(name, new Coordinates(longitude, latitude, height));
        } else if (type == "Helicopter") {
            return new Helicopter(name, new Coordinates(longitude, latitude, height));
        } else if (type == "JetPlace") {
            return new JetPlane(name, new Coordinates(longitude, latitude, height));
        }
        throw new Error("Invalid type " + type);
    }
}
