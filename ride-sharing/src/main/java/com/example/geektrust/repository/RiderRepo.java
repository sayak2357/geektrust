package com.example.geektrust.repository;

import com.example.geektrust.model.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderRepo {
    private Map<String, Rider> riderMap;
    public boolean addRider(String id, Double x, Double y){
        if(riderMap.containsKey(id))
            return false;
        riderMap.put(id,new Rider(id,x,y));
        return true;
    }
    public Rider getRider(String id){
        if(riderMap.containsKey(id))
            return riderMap.get(id);
        return null;
    }

    public RiderRepo(){
        riderMap = new HashMap<>();
    }
}
