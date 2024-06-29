package com.example.geektrust.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiderTest {
    private Rider r1;
    @BeforeEach
    void initialize(){
        this.r1 = new Rider("r1",12d,-45d);
    }
    @Test
    void getX() {
        assertTrue(this.r1.getX()==12d);
    }

    @Test
    void setX() {
        this.r1.setX(49d);
        assertTrue(this.r1.getX()==49d);
    }

    @Test
    void getY() {
        assertTrue(this.r1.getY()==-45d);
    }

    @Test
    void setY() {
        this.r1.setY(8d);
        assertTrue(this.r1.getY()==8d);
    }

    @Test
    void getId() {
        assertTrue(this.r1.getId()=="r1");
    }

    @Test
    void setId() {
        this.r1.setId("r2");
        assertTrue(this.r1.getId()=="r2");
    }

    @Test
    void isOnRideTrue() {
        this.r1.setOnRide(true);
        assertTrue(this.r1.isOnRide());
    }
    @Test
    void isOnRideFalse() {
        assertFalse(this.r1.isOnRide());
    }

    @Test
    void setOnRide() {
        this.r1.setOnRide(true);
        assertTrue(this.r1.isOnRide());
    }
}