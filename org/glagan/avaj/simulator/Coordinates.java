package org.glagan.avaj.simulator;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setHeight(height);
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        if (longitude < 0) {
            this.longitude = 0;
        } else {
            this.longitude = longitude;
        }
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        if (latitude < 0) {
            this.latitude = 0;
        } else {
            this.latitude = latitude;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 0) {
            this.height = 0;
        } else if (height > 100) {
            this.height = 100;
        } else {
            this.height = height;
        }
    }

    public Coordinates clone() {
        return new Coordinates(this.longitude, this.latitude, this.height);
    }
}
