package org.glagan.avaj.simulator;

import java.util.List;

public class WeatherProvider {
    static private WeatherProvider weatherProvider = new WeatherProvider();

    private List<String> weather;

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return "SUN";
    }
}
