package org.glagan.avaj.simulator.Exceptions;

public class InvalidWeather extends Error {
    public String invalidType;

    public InvalidWeather(String invalidType) {
        super("Invalid Weather " + invalidType);
        this.invalidType = invalidType;
    }
}
