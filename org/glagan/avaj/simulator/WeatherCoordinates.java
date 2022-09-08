package org.glagan.avaj.simulator;

public class WeatherCoordinates {
    private Coordinates coordinates;
    private String weather;

    public WeatherCoordinates(Coordinates coordinates, String weather) {
        this.coordinates = coordinates.clone();
        this.weather = weather;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

}
