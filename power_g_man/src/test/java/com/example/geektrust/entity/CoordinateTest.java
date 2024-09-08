package com.example.geektrust.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    private Coordinate test;
    @BeforeEach
    void setUp() {
        this.test = new Coordinate(1,2);
    }

    @Test
    void getY() {
        assertEquals(this.test.getY(),2);
    }

    @Test
    void setY() {
        this.test.setY(-5);
        assertEquals(this.test.getY(),-5);
    }

    @Test
    void getX() {
        assertEquals(this.test.getX(),1);
    }

    @Test
    void setX() {
        this.test.setX(100);
        assertEquals(this.test.getX(),100);
    }
}