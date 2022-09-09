package org.glagan.avaj.simulator;

public class WeatherCoordinates {
    private Coordinates coordinates;
    private String weather;

    public WeatherCoordinates(Coordinates coordinates, String weather) {
        this.coordinates = coordinates;
        this.weather = weather;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getWeather() {
        return weather;
    }
}
