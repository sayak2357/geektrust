package com.example.geektrust.service;

import com.example.geektrust.repository.Rooms;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializerTest {
    private Rooms rooms;
    @BeforeEach
    void beforeEach() {
        this.rooms = Initializer.getInitializedRooms();
    }
    @Test
    void getInitializedRooms() {
        assertNotNull(rooms);
        assertTrue(rooms.getAllRooms().size()>0);
    }
}