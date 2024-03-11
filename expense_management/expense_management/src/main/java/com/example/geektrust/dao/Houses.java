package com.example.geektrust.dao;

import com.example.geektrust.model.House;

import java.util.HashMap;
import java.util.Map;

public class Houses {
    private Map<String, House> getById;
    public Houses(){
        getById = new HashMap<>();
    }
    public void addHouse(House h){
        getById.put(h.getId(),h);
    }
    public House getHouseById(String id){
        return getById.containsKey(id) ? getById.get(id):null;
    }
}
