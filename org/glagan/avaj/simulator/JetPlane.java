package org.glagan.avaj.simulator;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public String identifier() {
        return "JetPlanet#" + this.name + "(" + this.id + ")";
    }

    @Override
    public void updateConditions() {
        if (this.weatherTower != null) {
            String weather = this.weatherTower.getWeather(this.coordinates);
            switch (weather) {
                case "SUN":
                    this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
                    this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                    break;
                case "RAIN":
                    this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
                    break;
                case "FOG":
                    this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
                    break;
                case "SNOW":
                    this.coordinates.setHeight(this.coordinates.getHeight() + 7);
                    break;
                default:
                    throw new Error("Invalid weather " + weather + ", crashing the plane.");
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
