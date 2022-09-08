package org.glagan.avaj.simulator;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public String identifier() {
        return "Helicopter#" + this.name + "(" + this.id + ")";
    }

    @Override
    public void updateConditions() {
        if (this.weatherTower != null) {
            String weather = this.weatherTower.getWeather(this.coordinates);
            switch (weather) {
                case "SUN":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 10);
                    this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                    break;
                case "RAIN":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 5);
                    break;
                case "FOG":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 1);
                    break;
                case "SNOW":
                    this.coordinates.setLongitude(this.coordinates.getHeight() + 12);
                    break;
                default:
                    throw new Error("Invalid weather " + weather + ", removing the god nut.");
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
