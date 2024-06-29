package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceFinderServiceTest {
    private DistanceFinderService distanceFinderService;
    @BeforeEach
    void setUp() {
        this.distanceFinderService = new DistanceFinderService();
    }

    @Test
    void findDistance() {
        assertTrue(this.distanceFinderService.findDistance(0d,0d,3d,4d)==5d);
    }
}