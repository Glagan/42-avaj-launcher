package org.glagan.avaj.simulator;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        WeatherProvider provider = WeatherProvider.getProvider();
        return provider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        WeatherProvider provider = WeatherProvider.getProvider();

    }
}
