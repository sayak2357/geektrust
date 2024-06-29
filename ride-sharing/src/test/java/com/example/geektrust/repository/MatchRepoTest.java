package com.example.geektrust.repository;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.DriverDistancePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MatchRepoTest {
    private MatchRepo matchRepo;
    private List<DriverDistancePair> driverDistancePairList;
    @BeforeEach
    void setUp() {
        this.matchRepo = new MatchRepo();
        DriverDistancePair dp = new DriverDistancePair(new Driver("d1",0d,0d),50d);
        this.driverDistancePairList = new ArrayList<>();
        this.driverDistancePairList.add(dp);
    }

    @Test
    void putNearestDrivers() {
        assertTrue(this.matchRepo.putNearestDrivers("r1",this.driverDistancePairList));
    }

    @Test
    void getNearestDrivers() {
        this.matchRepo.putNearestDrivers("r1",this.driverDistancePairList);
        assertNotNull(this.matchRepo.getNearestDrivers("r1"));
    }
    @Test
    void getNearestDriversFailure() {
        this.matchRepo.putNearestDrivers("r1",this.driverDistancePairList);
        assertNull(this.matchRepo.getNearestDrivers("r2"));
    }
}