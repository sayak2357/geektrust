package com.example.geektrust.service;

import com.example.geektrust.helper.Pair;
import com.example.geektrust.repository.LocationDataManager;
import com.example.geektrust.repository.MetroCardManager;

import java.util.*;

public class SummaryService {
    private LocationDataManager locationDataManager;
    private MetroCardManager metroCardManager;
    public SummaryService(LocationDataManager locationDataManager, MetroCardManager metroCardManager){
        this.locationDataManager = locationDataManager;
        this.metroCardManager = metroCardManager;
    }
    public void summary(){
        if(locationDataManager.getTotalCollection("CENTRAL")>0d){
            String place = "CENTRAL";
            System.out.println("TOTAL_COLLECTION "+place+" "+locationDataManager.getTotalCollection(place).intValue()+" "+locationDataManager.getTotalDiscountGiven(place).intValue());
            summarizePassengers(place);
        }
        if(locationDataManager.getTotalCollection("AIRPORT")>0d){
            String place = "AIRPORT";
            System.out.println("TOTAL_COLLECTION "+place+" "+locationDataManager.getTotalCollection(place).intValue()+" "+locationDataManager.getTotalDiscountGiven(place).intValue());
            summarizePassengers(place);
        }
    }
    private void summarizePassengers(String station){
        System.out.println("PASSENGER_TYPE_SUMMARY");
        List<String> passengerTypes = locationDataManager.getListOfPassengerTypes(station);
        Map<String, Integer> count = new HashMap<>();
        for(String type:passengerTypes){
            count.put(type,count.getOrDefault(type,0)+1);
        }
        List<Pair> classCountList = new ArrayList<>();
        for(String type: count.keySet()){
            int val = count.get(type);
            Pair temp = new Pair(type,val);
            classCountList.add(temp);
        }
        Collections.sort(classCountList,(a, b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()):b.getValue()-a.getValue());
        for(Pair p:classCountList){
            System.out.println(p.getKey()+" "+p.getValue());
        }
    }
}
