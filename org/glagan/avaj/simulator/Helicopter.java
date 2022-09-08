package org.glagan.avaj.simulator;

import org.glagan.avaj.simulator.Exceptions.InvalidWeather;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        if (this.weatherTower != null) {
            String weather = this.weatherTower.getWeather(this.coordinates);
            if (!this.weatherChanged(weather)) {
                return;
            }
            this.previousWeather = weather;
            switch (weather) {
                case "SUN":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
                    this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                    Output.write(this.identifier() + ": What a beautiful weather Johnson.");
                    break;
                case "RAIN":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
                    Output.write(this.identifier() + ": I love slicing rain drops.");
                    break;
                case "FOG":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
                    Output.write(this.identifier() + ": Pray to god that we don't hit something.");
                    break;
                case "SNOW":
                    this.coordinates.setLongitude(this.coordinates.getHeight() + 12);
                    Output.write(this.identifier() + ": Johnson, I lost control, sorry.");
                    break;
                default:
                    throw new InvalidWeather(weather);
            }
            if (this.coordinates.getHeight() == 0) {
                this.weatherTower.unregister(this);
                this.weatherTower = null;
                Output.write(this.identifier() + " landing at " + this.position());
            }
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
    }
}
