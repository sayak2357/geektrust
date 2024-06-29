package com.example.geektrust.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideTest {
    private Ride rTest;
    @BeforeEach
    void initialize(){
        this.rTest = new Ride("r-001","rider-1001");
    }
    @Test
    void getDriverId() {
        this.rTest.setDriverId("d-001");
        assertTrue(this.rTest.getDriverId()=="d-001");
    }

    @Test
    void setDriverId() {
        this.rTest.setDriverId("d-001");
        assertTrue(this.rTest.getDriverId()=="d-001");
    }

    @Test
    void getRiderId() {
        assertTrue(this.rTest.getRiderId()=="rider-1001");
    }

    @Test
    void setRiderId() {
        this.rTest.setRiderId("r-002");
        assertTrue(this.rTest.getRiderId()=="r-002");
    }

    @Test
    void getBill() {
        this.rTest.setBill(100d);
        assertTrue(this.rTest.getBill()==100d);
    }

    @Test
    void getDestX() {
        this.rTest.setDestX(45d);
        assertTrue(this.rTest.getDestX()==45d);
    }

    @Test
    void setDestX() {
        this.rTest.setDestX(45d);
        assertTrue(this.rTest.getDestX()==45d);
    }

    @Test
    void getDestY() {
        this.rTest.setDestY(-15d);
        assertTrue(this.rTest.getDestY()==-15d);
    }

    @Test
    void setDestY() {
        this.rTest.setDestY(-15d);
        assertTrue(this.rTest.getDestY()==-15d);
    }

    @Test
    void getTime() {
        this.rTest.setTime(100d);
        assertTrue(this.rTest.getTime()==100d);
    }

    @Test
    void setTime() {
        this.rTest.setTime(100d);
        assertTrue(this.rTest.getTime()==100d);
    }

    @Test
    void isFinished() {
        this.rTest.setFinished(true);
        assertTrue(this.rTest.isFinished());
    }

    @Test
    void setFinished() {
        this.rTest.setFinished(true);
        assertTrue(this.rTest.isFinished());
    }

    @Test
    void setBill() {
        this.rTest.setBill(100d);
        assertTrue(this.rTest.getBill()==100d);
    }
}