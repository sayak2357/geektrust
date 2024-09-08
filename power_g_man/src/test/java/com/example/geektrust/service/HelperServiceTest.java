package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperServiceTest {
    private HelperService helperService;
    private Integer posHorizontalGap, posVerticalGap,negHorizontalGap,negVerticalGap;
    @BeforeEach
    void setUp() {
        this.helperService = new HelperService();
        this.posHorizontalGap = 5;
        this.posVerticalGap = 4;
        this.negHorizontalGap = -4;
        this.negVerticalGap = -5;
    }

    @Test
    void findHorizontalTurnCost() {
        assertEquals(this.helperService.findHorizontalTurnCost(this.posHorizontalGap, Constants.directions.N),Constants.TURN_COST);
        assertEquals(this.helperService.findHorizontalTurnCost(this.negHorizontalGap, Constants.directions.E),2*Constants.TURN_COST);
    }

    @Test
    void findDirectionEnum() {
        assertEquals(Constants.directions.W,this.helperService.findDirectionEnum("W"));
    }

    @Test
    void calculateNetHorizontalCost() {
        assertEquals(this.helperService.calculateNetHorizontalCost(this.posHorizontalGap,Constants.directions.E).getCost(),Constants.DISTANCE_TRAVEL_COST*this.posHorizontalGap);
        assertEquals(this.helperService.calculateNetHorizontalCost(this.posHorizontalGap,Constants.directions.N).getCost(),Constants.DISTANCE_TRAVEL_COST*this.posHorizontalGap+
                                                                                                                                        Constants.TURN_COST);
        assertEquals(this.helperService.calculateNetHorizontalCost(this.posHorizontalGap,Constants.directions.E).getDir(),Constants.directions.E);
        assertEquals(this.helperService.calculateNetHorizontalCost(this.negHorizontalGap,Constants.directions.E).getDir(),Constants.directions.W);
    }

    @Test
    void calculateNetVerticalCost() {

        assertEquals(this.helperService.calculateNetVerticalCost(this.posVerticalGap,Constants.directions.N).getCost(),Constants.DISTANCE_TRAVEL_COST*this.posVerticalGap);
        assertEquals(this.helperService.calculateNetVerticalCost(this.posVerticalGap,Constants.directions.W).getCost(),Constants.DISTANCE_TRAVEL_COST*this.posVerticalGap+
                Constants.TURN_COST);
        assertEquals(this.helperService.calculateNetVerticalCost(this.posVerticalGap,Constants.directions.E).getDir(),Constants.directions.N);
        assertEquals(this.helperService.calculateNetVerticalCost(this.negVerticalGap,Constants.directions.E).getDir(),Constants.directions.S);
    }
}