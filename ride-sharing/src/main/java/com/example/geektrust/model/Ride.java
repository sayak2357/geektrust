package com.example.geektrust.model;

public class Ride {
    private String rideId, riderId;
    private Integer destX, destY, time;
    private Double bill;
    private boolean finished;
    public Ride(String rideId, String riderId){
        this.rideId = rideId;
        this.rideId = riderId;
        this.finished = false;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public Double getBill() {
        return bill;
    }

    public Integer getDestX() {
        return destX;
    }

    public void setDestX(Integer destX) {
        this.destX = destX;
    }

    public Integer getDestY() {
        return destY;
    }

    public void setDestY(Integer destY) {
        this.destY = destY;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public void setBill(Double bill){
        this.finished = true;
        this.bill = bill;
    }
}
