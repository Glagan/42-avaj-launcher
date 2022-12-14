package org.glagan.avaj.simulator;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected String previousWeather;
    private static long idCounter = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
        this.previousWeather = "";
    }

    public String identifier() {
        return this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ")";
    }

    public String position() {
        return this.coordinates.getLatitude() + "," + this.coordinates.getLongitude();
    }

    public boolean weatherChanged(String weather) {
        boolean changed = !this.previousWeather.equals(weather);
        this.previousWeather = weather;
        return changed;
    }

    private long nextId() {
        long id = Aircraft.idCounter;
        Aircraft.idCounter += 1;
        return id;
    }
}
