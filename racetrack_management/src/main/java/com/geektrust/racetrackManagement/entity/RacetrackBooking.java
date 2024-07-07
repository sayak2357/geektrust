package com.geektrust.racetrackManagement.entity;

public class RacetrackBooking {
    private String startTime;
    private String endTime;
    private boolean isExtended;
    private String vehicleNumber;
    private String vehicleType;
    private String track;
    public RacetrackBooking(String startTime,String endTime,String vehicleNumber,String vehicleType){
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.isExtended = false;
        this.track = null;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    public void setIsExtended() {
        this.isExtended=true;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public boolean isExtended() {
        return isExtended;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setExtended() {
        isExtended = true;
    }
    public RacetrackBooking clone(){
        return new RacetrackBooking(this.startTime,this.endTime,this.vehicleNumber,this.vehicleType);
    }
}
