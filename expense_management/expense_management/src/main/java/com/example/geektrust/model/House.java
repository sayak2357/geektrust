package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;
public class House {
    private final String id;
    private List<User> users;
    public House(String id){
        this.id = id;
        users = new ArrayList<>();
    }
    public Integer getUserCount(){
        return users.size();
    }
    public void addUser(User u){
        users.add(u);
    }

    public String getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
