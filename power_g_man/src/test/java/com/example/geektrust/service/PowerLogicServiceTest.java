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
    }

    @Test
    void getRemainingPower() {
        this.source = new Coordinate(2,1);
        this.destination = new Coordinate(4,3);
        this.dir = Constants.directions.E;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),155);

        this.source = new Coordinate(3,1);
        this.destination = new Coordinate(5,1);
        this.dir = Constants.directions.W;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),170);


        this.source = new Coordinate(1,1);
        this.destination = new Coordinate(1,2);
        this.dir = Constants.directions.S;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),180);


        this.source = new Coordinate(0,5);
        this.destination = new Coordinate(6,1);
        this.dir = Constants.directions.W;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),90);


        this.source = new Coordinate(5,5);
        this.destination = new Coordinate(1,2);
        this.dir = Constants.directions.E;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),120);

        this.source = new Coordinate(1,4);
        this.destination = new Coordinate(5,2);
        this.dir = Constants.directions.W;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),130);


        this.source = new Coordinate(0,0);
        this.destination = new Coordinate(6,6);
        this.dir = Constants.directions.W;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),70);

        this.source = new Coordinate(5,5);
        this.destination = new Coordinate(3,3);
        this.dir = Constants.directions.N;
        assertEquals(this.powerLogicService.getRemainingPower(this.source,this.destination,this.dir),150);
    }
}