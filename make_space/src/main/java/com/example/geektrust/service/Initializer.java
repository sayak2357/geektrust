package com.example.geektrust.service;

import com.example.geektrust.entity.Room;
import com.example.geektrust.repository.Rooms;

public class Initializer {
    public static Rooms getInitializedRooms(){
        Rooms rooms = new Rooms();
        Room room1 = new Room("C-Cave",3);
        Room room2 = new Room("D-Tower",7);
        Room room3 = new Room("G-Mansion",20);
        rooms.addRoom(room1);
        rooms.addRoom(room2);
        rooms.addRoom(room3);
        return rooms;
    }
}
