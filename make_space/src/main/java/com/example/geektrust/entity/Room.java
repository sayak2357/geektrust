package com.example.geektrust.entity;

public class Room {
    private String name;
    private int capacity;

    public Room(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }

}
