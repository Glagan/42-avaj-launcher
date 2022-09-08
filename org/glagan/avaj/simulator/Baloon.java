package org.glagan.avaj.simulator;

import org.glagan.avaj.simulator.Exceptions.InvalidWeather;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        if (this.weatherTower != null) {
            String weather = this.weatherTower.getWeather(this.coordinates);
            if (!this.weatherChanged(weather)) {
                return;
            }
            switch (weather) {
                case "SUN":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
                    this.coordinates.setHeight(this.coordinates.getHeight() + 4);
                    System.out.println(this.identifier() + ": We're getting close Icarus.");
                    break;
                case "RAIN":
                    this.coordinates.setHeight(this.coordinates.getHeight() + 5);
                    System.out.println(this.identifier() + ": I thinks it's raining.");
                    break;
                case "FOG":
                    this.coordinates.setHeight(this.coordinates.getHeight() - 3);
                    System.out.println(this.identifier() + ": I can't see sh*t in this mist.");
                    break;
                case "SNOW":
                    this.coordinates.setHeight(this.coordinates.getHeight() - 15);
                    System.out.println(this.identifier() + ": Huh, I don't know if we're supposed to be here.");
                    break;
                default:
                    throw new InvalidWeather(weather);
            }
            if (this.coordinates.getHeight() == 0) {
                this.weatherTower.unregister(this);
                this.weatherTower = null;
                System.out.println(this.identifier() + " landing at " + this.position());
            }
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
    }
}
