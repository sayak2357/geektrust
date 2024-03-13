package com.example.geektrust.model;

public class DriverDistancePair {
    private Driver driver;
    private Double distance;
    public DriverDistancePair(Driver driver, Double distance){
        this.driver = driver;
        this.distance = distance;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
