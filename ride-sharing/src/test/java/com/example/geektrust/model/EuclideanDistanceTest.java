package com.example.geektrust.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuclideanDistanceTest {
    private EuclideanDistance ed;
    @BeforeEach
    void initialize(){
        this.ed = new EuclideanDistance();
    }
    @Test
    void findDistance() {
        assertTrue(this.ed.findDistance(3d,4d,0d,0d)==5d);
    }
}