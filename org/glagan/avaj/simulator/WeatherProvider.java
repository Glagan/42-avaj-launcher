package org.glagan.avaj.simulator;

import java.util.Random;

public class WeatherProvider {
    static private WeatherProvider weatherProvider = new WeatherProvider();
    static private String[] weather = {
            "SUN",
            "RAIN",
            "FOG",
            "SNOW"
    };

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        Random random = new Random();
        int index = random.nextInt(WeatherProvider.weather.length);
        return WeatherProvider.weather[index];
    }
}
