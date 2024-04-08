package com.example.geektrust.repository;

import java.util.*;

public class LocationDataManager {
    private Map<String, Double> totalCollections;
    private Map<String, Double> totalDiscountsGiven;
    private Map<String,List<String>> passengers;
    public LocationDataManager(){
        totalCollections = new HashMap<>();
        totalDiscountsGiven = new HashMap<>();
        passengers = new HashMap<>();
    }
    public void addToCollection(String station, Double amount){
        if(!totalCollections.containsKey(station)){
            totalCollections.put(station,0d);
        }
        if(!totalDiscountsGiven.containsKey(station)){
            totalDiscountsGiven.put(station,0d);
        }
        Double existing = totalCollections.get(station);
        existing += amount;
        totalCollections.put(station,existing);

    }
    public void addToTotalDiscountsGiven(String station, Integer amount){
        if(!totalDiscountsGiven.containsKey(station)){
            totalDiscountsGiven.put(station,0d);
        }
        if(!totalCollections.containsKey(station)){
            totalCollections.put(station,0d);
        }
        Double existing = totalDiscountsGiven.get(station);
        existing += amount;
        totalDiscountsGiven.put(station,existing);
    }
    public void addPassengerType(String station, String passengerType){
        if(!passengers.containsKey(station)){
            passengers.put(station,new ArrayList<>());
        }
        List<String> existing = passengers.get(station);
        existing.add(passengerType);
        passengers.put(station,existing);
    }
    public Double getTotalCollection(String station){
        return totalCollections.containsKey(station)? totalCollections.get(station):0d;
    }
    public Double getTotalDiscountGiven(String station){
        return totalDiscountsGiven.containsKey(station)? totalDiscountsGiven.get(station):0d;
    }
    public List<String> getListOfPassengerTypes(String station){
        return passengers.containsKey(station) ? passengers.get(station):null;
    }
}
