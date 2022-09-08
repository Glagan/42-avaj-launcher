package org.glagan.avaj.simulator.Exceptions;

public class InvalidAircraftType extends Error {
    public String invalidType;

    public InvalidAircraftType(String invalidType) {
        super("Invalid Aircraft type " + invalidType);
        this.invalidType = invalidType;
    }
}
