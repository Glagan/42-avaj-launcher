package org.glagan.avaj.simulator;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinates coordinates) {
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
                    this.coordinates.setLatitude(this.coordinates.getLatitude() + 10);
                    this.coordinates.setHeight(this.coordinates.getHeight() + 2);
                    Output.write(this.identifier() + ": I can't feel the heat.");
                    break;
                case "RAIN":
                    this.coordinates.setLatitude(this.coordinates.getLatitude() + 5);
                    Output.write(this.identifier() + ": We're going past that rain.");
                    break;
                case "FOG":
                    this.coordinates.setLatitude(this.coordinates.getLatitude() + 1);
                    Output.write(this.identifier() + ": I'm even more invisible.");
                    break;
                case "SNOW":
                    this.coordinates.setHeight(this.coordinates.getHeight() + 7);
                    Output.write(this.identifier() + ": I just have to get higher.");
                    break;
                default:
                    System.out.println("Invalid weather " + weather);
                    // throw new InvalidWeather(weather);
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
