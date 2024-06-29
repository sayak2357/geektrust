package com.example.geektrust.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    private Driver testDriver;
    @BeforeEach
    void initialize(){
        this.testDriver = new Driver("d1",1d,2d);
    }
    @Test
    void getId() {
        assertNotNull(testDriver.getId());
    }

    @Test
    void getX() {
        assertNotNull(testDriver.getX());
    }

    @Test
    void setX() {
        testDriver.setX(-30d);
        assertTrue(testDriver.getX()==-30d);
    }

    @Test
    void getY() {
        assertTrue(testDriver.getY()==2d);
    }

    @Test
    void setY() {
        testDriver.setY(2d);
        assertTrue(testDriver.getY()==2d);
    }

    @Test
    void isOnRide() {
        testDriver.setOnRide(true);
        assertTrue(testDriver.isOnRide());
    }

    @Test
    void setOnRide() {
        testDriver.setOnRide(true);
        assertTrue(testDriver.isOnRide());
    }

    @Test
    void setId() {
        testDriver.setId("d2");
        assertTrue(testDriver.getId()=="d2");
    }
}