package com.cenla.models;

public class Conditions {
    private double averageTemperature;
    private double averageHumidity;
    private double waterAvailability;

    public Conditions(double averageTemperature, double averageHumidity, double waterAvailability) {
        this.averageTemperature = averageTemperature;
        this.averageHumidity = averageHumidity;
        this.waterAvailability = waterAvailability;
    }

    public Conditions() {
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    public double getWaterAvailability() {
        return waterAvailability;
    }

    public void setWaterAvailability(double waterAvailability) {
        this.waterAvailability = waterAvailability;
    }

    @Override
    public String toString() {
        return averageTemperature + ":" + averageHumidity + ":" + waterAvailability;
    }
}
