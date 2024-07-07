package com.example.geektrust.repository;

import com.example.geektrust.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomsTest {
    private Rooms rooms;
    @BeforeEach
    void setUp() {
        this.rooms = new Rooms();
    }

    @Test
    void addRoom() {
        assertTrue(rooms.addRoom(new Room("a",4)));
    }

    @Test
    void getAllRooms() {
        assertTrue(rooms.addRoom(new Room("a",4)));
        assertTrue(rooms.addRoom(new Room("b",2)));
        assertEquals(rooms.getAllRooms().get(0).getCapacity(),2);
        assertEquals(rooms.getAllRooms().get(1).getCapacity(),4);
    }
}