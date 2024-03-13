package com.example.geektrust.repository;

import com.example.geektrust.model.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepo {
    private Map<String, Driver> driverMap;
    public boolean addDriver(String id, Integer x, Integer y){
        if(driverMap.containsKey(id))
            return false;
        driverMap.put(id,new Driver(id,x,y));
        return true;
    }
    public Driver getDriver(String id){
        if(driverMap.containsKey(id))
            return driverMap.get(id);
        return null;
    }
    public DriverRepo(){
        driverMap = new HashMap<>();
    }
    public List<Driver> getDrivers(){
        return new ArrayList<>(driverMap.values());
    }
}
