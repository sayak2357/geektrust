package com.example.geektrust.model;

public class Rider {
    private String id;
    private boolean onRide;

    private Integer x,y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
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

    public Rider(String id, Integer x, Integer y){
        this.id = id;
        this.onRide = false;
        this.x = x;
        this.y = y;
    }
}

