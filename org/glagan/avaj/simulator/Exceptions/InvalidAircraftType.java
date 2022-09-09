package org.glagan.avaj.simulator.Exceptions;

public class InvalidAircraftType extends Exception {
    public String invalidType;

    public InvalidAircraftType(String invalidType) {
        super("Invalid Aircraft type " + invalidType);
        this.invalidType = invalidType;
    }
}
