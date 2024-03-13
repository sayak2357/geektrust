package com.example.geektrust.repository;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.DriverDistancePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchRepo {
    private Map<String, List<DriverDistancePair>> nearestDrivers;
    public MatchRepo(){
        this.nearestDrivers = new HashMap<>();
    }
    public void putNearestDrivers(String riderId,List<DriverDistancePair> driverDistances){
        nearestDrivers.put(riderId,driverDistances);
    }
    public List<DriverDistancePair> getNearestDrivers(String riderId){
        return nearestDrivers.containsKey(riderId)? nearestDrivers.get(riderId):null;
    }
}
