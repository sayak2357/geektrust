package com.example.geektrust.service;

import com.example.geektrust.repository.DriverRepo;
import com.example.geektrust.repository.MatchRepo;
import com.example.geektrust.repository.RiderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchServiceTest {
    private MatchService matchService;
    @BeforeEach
    void setUp() {
        DriverRepo driverRepo = new DriverRepo();
        driverRepo.addDriver("d1",1d,2d);
        driverRepo.addDriver("d2",2d,3d);
        RiderRepo riderRepo = new RiderRepo();
        riderRepo.addRider("r1",3d,4d);
        this.matchService = new MatchService(driverRepo,riderRepo,new DistanceFinderService(),new MatchRepo());
    }

    @Test
    void showNearestDrivers() {
        assertEquals(this.matchService.showNearestDrivers("r1").size(),2);
    }
}