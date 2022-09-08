package org.glagan.avaj.simulator;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    public Aircraft(String name, Coordinates coordinates) {
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    public String position() {
        return this.coordinates.getLatitude() + ", " + this.coordinates.getLongitude() + " : "
                + this.coordinates.getHeight();
    }

    private long nextId() {
        long id = Aircraft.idCounter;
        Aircraft.idCounter += 1;
        return id;
    }
}
