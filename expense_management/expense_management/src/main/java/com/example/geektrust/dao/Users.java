package com.example.geektrust.dao;

import com.example.geektrust.model.User;

import java.util.HashMap;
import java.util.Map;

public class Users {
    private Map<String, User> getByName;
    public Users(){
        getByName = new HashMap<>();
    }
    public boolean addUser(User u){
        if(getByName.containsKey(u.getName()))
            return false;
        getByName.put(u.getName(),u);
        return true;
    }
    public void removeByName(String uname){
        getByName.remove(uname);
    }
    public User findByName(String name){
        return getByName.containsKey(name) ? getByName.get(name) : null;
    }
}
