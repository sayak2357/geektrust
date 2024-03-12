package com.example.geektrust.dao;

import com.example.geektrust.model.House;
import com.example.geektrust.model.User;

import java.util.HashMap;
import java.util.List;
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
    public void moveUserOut(String houseId, String uname){
        House house = getById.containsKey(houseId) ? getById.get(houseId):null;
        if(house == null){
            System.out.println("invalid house id");
            return;
        }
        List<User> users = house.getUsers();
        boolean flag = false;
        for(User u:users){
            if(u.getName().equals(uname)){
                users.remove(uname);
                flag = true;
                break;
            }
        }
        if(!flag)
            System.out.println("user "+uname+" not present in "+houseId );
        return;
    }
}
