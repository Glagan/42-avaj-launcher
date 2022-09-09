package org.glagan.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public class WeatherTower extends Tower {
    private List<WeatherCoordinates> coordinatesWeather;

    public WeatherTower() {
        super();
        this.coordinatesWeather = new ArrayList<WeatherCoordinates>();
    }

    public String getWeather(Coordinates coordinates) {
        // Check if the weather for the given coordinates where already computed
        for (WeatherCoordinates weatherCoordinates : this.coordinatesWeather) {
            if (weatherCoordinates.getCoordinates().getLongitude() == coordinates.getLongitude() &&
                    weatherCoordinates.getCoordinates().getLatitude() == coordinates.getLatitude() &&
                    weatherCoordinates.getCoordinates().getHeight() == coordinates.getHeight()) {
                return weatherCoordinates.getWeather();
            }
        }

        WeatherProvider provider = WeatherProvider.getProvider();
        String weather = provider.getCurrentWeather(coordinates);
        this.coordinatesWeather.add(new WeatherCoordinates(coordinates.clone(), weather));
        return weather;
    }

    public void changeWeather() {
        this.coordinatesWeather = new ArrayList<WeatherCoordinates>();
        this.conditionsChanged();
    }
}
