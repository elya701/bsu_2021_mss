package com.company.model;

public class Optical_Driver extends Device {
    private String dimension;
    private int speedOfReading;

    public Optical_Driver () {
    }

    public Optical_Driver (String type, int speedOfReading) {
        this.type = type;
        this.speedOfReading = speedOfReading;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public int getSpeedOfReading() {
        return speedOfReading;
    }

    public void setSpeedOfReading(int speedOfReading) {
        this.speedOfReading = speedOfReading;
    }
}