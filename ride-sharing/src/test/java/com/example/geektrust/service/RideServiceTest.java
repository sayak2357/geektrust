package com.example.geektrust.service;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Rider;
import com.example.geektrust.repository.DriverRepo;
import com.example.geektrust.repository.RiderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RideServiceTest {
    private RideService rideService;
    private Rider r1;
    private Driver d1;
    @BeforeEach
    void setUp() {
        RiderRepo riderRepo = new RiderRepo();
        DriverRepo driverRepo = new DriverRepo();
        this.r1 = new Rider("r1",0d,0d);
        this.d1 = new Driver("d1",1d,1d);
        riderRepo.addRider(r1.getId(),r1.getX(),r1.getY());
        driverRepo.addDriver(d1.getId(), d1.getX(), d1.getY());
        this.rideService = new RideService(riderRepo,driverRepo);
    }

    @Test
    void startRideSuccess() {
        assertTrue(this.rideService.startRide("r-001",d1,r1));
    }

    @Test
    void startRideFailure() {
        this.rideService.startRide("r-001",d1,r1);
        assertFalse(this.rideService.startRide("r-001",new Driver("d2",0d,0d),new Rider("r2",1d,1d)));
    }


    @Test
    void stopRideSuccess() {
        this.rideService.startRide("r-001",d1,r1);
        assertTrue(rideService.stopRide("r-001",5d,5d,20d));
    }

    @Test
    void stopRideFailure() {
        assertFalse(rideService.stopRide("r-001",5d,5d,20d));
    }

    @Test
    void generateBillSuccess() {
        this.rideService.startRide("r-001",d1,r1);
        this.rideService.stopRide("r-001",5d,5d,20d);
        assertTrue(this.rideService.generateBill("r-001"));
    }

    @Test
    void generateBillFailure() {
        assertFalse(this.rideService.generateBill("r-001"));
    }
}