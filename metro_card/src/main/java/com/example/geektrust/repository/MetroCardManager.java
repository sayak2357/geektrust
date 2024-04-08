package com.example.geektrust.repository;

import com.example.geektrust.model.MetroCard;

import java.util.HashMap;
import java.util.Map;

public class MetroCardManager {
    private Map<String, MetroCard> cards;
    public MetroCardManager(){
        cards = new HashMap<>();
    }
    public void registerCard(String id, Double amount){
        MetroCard mc = new MetroCard(id,amount);
        cards.put(id,mc);
    }
    public void updateBalance(String id, Double newBalance){
        MetroCard mc = cards.get(id);
        if(mc == null)
            return;
        mc.setBalance(newBalance);
    }
    public Double getBalance(String id){
        MetroCard mc = cards.get(id);
        if(mc == null)
            return -1d;
        return mc.getBalance();
    }
    public Integer getTravelCount(String id){
        MetroCard mc = cards.get(id);
        if(mc == null)
            return -1;
        return mc.getTravelCount();
    }
    public boolean incrementTravelCount(String id){
        MetroCard mc = cards.get(id);
        if(mc==null)
            return false;
        mc.setTravelCount(mc.getTravelCount()+1);
        return true;
    }
}
