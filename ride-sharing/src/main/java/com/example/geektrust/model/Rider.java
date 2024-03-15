package com.example.geektrust.model;

public class Rider {
    private String id;
    private boolean onRide;

    private Double x;
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOnRide() {
        return onRide;
    }

    public void setOnRide(boolean onRide) {
        this.onRide = onRide;
    }

    public Rider(String id, Double x, Double y){
        this.id = id;
        this.onRide = false;
        this.x = x;
        this.y = y;
    }
}

