package com.example.geektrust.repository;

import com.example.geektrust.model.Ride;

import java.util.HashMap;
import java.util.Map;

public class RideRepo {
    private Map<String, Ride> rideMap;
    public RideRepo(){
        this.rideMap = new HashMap<>();
    }
    public boolean addRide(String rideId, Ride ride){
        if(rideMap.containsKey(rideId))
            return false;
        rideMap.put(rideId,ride);
        return true;
    }
    public Ride getRideById(String rideId){
        return rideMap.containsKey(rideId)? rideMap.get(rideId):null;
    }
}
