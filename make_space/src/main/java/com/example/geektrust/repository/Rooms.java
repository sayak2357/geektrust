package com.example.geektrust.repository;

import com.example.geektrust.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class Rooms {
    List<Room> allRooms;
    public Rooms(){
        allRooms = new ArrayList<>();
    }
    public void addRoom(Room room){
        this.allRooms.add(room);
    }
    public List<Room> getAllRooms(){
        allRooms.sort((a,b)-> a.getCapacity()-b.getCapacity());
        return allRooms;
    }
}
