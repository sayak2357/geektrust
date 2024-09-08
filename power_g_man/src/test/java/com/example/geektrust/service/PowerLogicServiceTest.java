package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.entity.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerLogicServiceTest {
    private PowerLogicService powerLogicService;
    private Coordinate source,destination;
    private Constants.directions dir;
    @BeforeEach
    void setUp() {
        this.powerLogicService = new PowerLogicService();
        this.source = new Coordinate(2,1);
        this.destination = new Coordinate(4,3);
        this.dir = Constants.directions.E;
    }

    @Test
    void getRemainingPower() {
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),155);
    }
}