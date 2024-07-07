package com.geektrust.racetrackManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceTrackConstraintCheckerTest {
    private RaceTrackConstraintChecker raceTrackConstraintChecker;
    @BeforeEach
    void setUp() {
        this.raceTrackConstraintChecker = new RaceTrackConstraintChecker();
    }

    @Test
    void getAllowedVehicleCount() {
        assertEquals(this.raceTrackConstraintChecker.getAllowedVehicleCount("REGULAR","BIKE"),4);
        assertEquals(this.raceTrackConstraintChecker.getAllowedVehicleCount("REGULAR","CAR"),2);
        assertEquals(this.raceTrackConstraintChecker.getAllowedVehicleCount("VIP","CAR"),1);
        assertEquals(this.raceTrackConstraintChecker.getAllowedVehicleCount("REGULAR","SUV"),2);
        assertEquals(this.raceTrackConstraintChecker.getAllowedVehicleCount("VIP","SUV"),1);
        assertEquals(this.raceTrackConstraintChecker.getAllowedVehicleCount("VIP","BIKE"),0);
    }

    @Test
    void getVehicleBookingCost() {
        assertEquals(this.raceTrackConstraintChecker.getVehicleBookingCost("REGULAR","BIKE"),60);
        assertEquals(this.raceTrackConstraintChecker.getVehicleBookingCost("REGULAR","CAR"),120);
        assertEquals(this.raceTrackConstraintChecker.getVehicleBookingCost("REGULAR","SUV"),200);
        assertEquals(this.raceTrackConstraintChecker.getVehicleBookingCost("VIP","CAR"),250);
        assertEquals(this.raceTrackConstraintChecker.getVehicleBookingCost("VIP","SUV"),300);
    }
}