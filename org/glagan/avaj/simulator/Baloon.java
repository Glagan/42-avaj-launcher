package org.glagan.avaj.simulator;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public String identifier() {
        return "Baloon#" + this.name + "(" + this.id + ")";
    }

    @Override
    public void updateConditions() {
        if (this.weatherTower != null) {
            String weather = this.weatherTower.getWeather(this.coordinates);
            switch (weather) {
                case "SUN":
                    this.coordinates.setLongitude(this.coordinates.getLongitude() + 2);
                    this.coordinates.setHeight(this.coordinates.getHeight() + 4);
                    break;
                case "RAIN":
                    this.coordinates.setHeight(this.coordinates.getHeight() + 5);
                    break;
                case "FOG":
                    this.coordinates.setHeight(this.coordinates.getHeight() - 3);
                    break;
                case "SNOW":
                    this.coordinates.setHeight(this.coordinates.getHeight() - 15);
                    break;
                default:
                    throw new Error("Invalid weather " + weather + ", making holes in the baloon.");
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
