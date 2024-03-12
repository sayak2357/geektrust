package com.example.geektrust.service;

import com.example.geektrust.dao.Houses;
import com.example.geektrust.model.House;
import com.example.geektrust.model.User;

import java.util.List;

public class HouseService {
    private Houses houses;
    public  HouseService(){
        this.houses = new Houses();
    }
    public boolean addUserToHouse(String houseId, User user){
        House house = this.houses.getHouseById(houseId);
        if(house==null){
            System.out.println("invalid house id provided");
            return false;
        }
        Integer userCount = house.getUserCount();
        if(userCount==3){
            System.out.println("HOUSEFUL");
            return false;
        }
        house.addUser(user);
        System.out.println("SUCCESS");
        return true;
    }
    public Integer getUserCountInHouse(String houseId){
        House house = this.houses.getHouseById(houseId);
        if(house==null){
            System.out.println("invalid house id provided");
            return 0;
        }
        Integer userCount = house.getUserCount();
        return userCount;
    }
    public List<User> getAllUsers(String houseId){
        House house = this.houses.getHouseById(houseId);
        if(house==null){
            System.out.println("invalid house id provided");
            return null;
        }
        return house.getUsers();
    }
    public void addHouse(House house){
        houses.addHouse(house);
    }
    public House getHouseById(String houseId){
        return houses.getHouseById(houseId);
    }
    public void moveOut(String houseId, String uname){
       houses.moveUserOut(houseId,uname);

    }
}
