package com.example.geektrust.repository;

import com.example.geektrust.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
    List<Room> allRooms;
    public Rooms(){
        allRooms = new ArrayList<>();
    }
    public boolean addRoom(Room room){
        this.allRooms.add(room);
        return true;
    }
    public List<Room> getAllRooms(){
        allRooms.sort((a,b)-> a.getCapacity()-b.getCapacity());
        return allRooms;
    }
}
