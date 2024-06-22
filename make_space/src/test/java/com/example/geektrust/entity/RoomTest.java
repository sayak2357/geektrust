package com.example.geektrust.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    private Room room;

    @BeforeEach
    void beforeEach() {
        this.room = new Room("test",10);
    }

    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals(room.getName(),"test");
    }

    @org.junit.jupiter.api.Test
    void roomIsGettingCreated() {
        assertNotNull(room);
    }

    @org.junit.jupiter.api.Test
    void getCapacity() {
        assertEquals(room.getCapacity(),10);
    }
}