package org.glagan.avaj.simulator;

import org.glagan.avaj.simulator.Exceptions.InvalidAircraftType;

public class AircraftFactory {
    static public Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws InvalidAircraftType {
        if (type.equals("Baloon")) {
            return new Baloon(name, new Coordinates(longitude, latitude, height));
        } else if (type.equals("Helicopter")) {
            return new Helicopter(name, new Coordinates(longitude, latitude, height));
        } else if (type.equals("JetPlane")) {
            return new JetPlane(name, new Coordinates(longitude, latitude, height));
        }
        throw new InvalidAircraftType(type);
    }
}
