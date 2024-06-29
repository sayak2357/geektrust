package com.example.geektrust.repository;

import com.example.geektrust.model.Ride;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideRepoTest {
    private RideRepo rideRepo;
    private Ride testRide;

    @BeforeEach
    void initialize(){
        this.rideRepo = new RideRepo();
        this.testRide = new Ride("ride-1001","rider1");
    }
    @Test
    void addRideSuccess() {
        boolean newRide = rideRepo.addRide("ride-1001",testRide);
        assertTrue(newRide);
    }

    @Test
    void addRideFailure() {
        boolean oldRide = rideRepo.addRide("ride-1001",testRide);
        boolean newRide = rideRepo.addRide("ride-1001",new Ride("ride-1001","rider2"));
        assertFalse(newRide);
    }

    @Test
    void getRideById() {
    }
}