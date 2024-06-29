package com.example.geektrust.repository;

import com.example.geektrust.model.Rider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiderRepoTest {
    private RiderRepo riderRepo;
    private Rider r1;
    @BeforeEach
    void initialize(){
        this.riderRepo = new RiderRepo();
        this.r1 = new Rider("r-1",12.5,-45.23);
    }

    @Test
    void addRiderSuccess() {
        boolean newRider = riderRepo.addRider("r-1",11.9,-56.4);
        assertTrue(newRider);
    }

    @Test
    void addRiderFailure() {
        boolean oldRider = riderRepo.addRider("r-1",-11.9,-56.4);
        boolean newRider = riderRepo.addRider("r-1",-11.9,-56.4);
        assertFalse(newRider);
    }

    @Test
    void getRiderSuccess() {
        riderRepo.addRider(r1.getId(), r1.getX(), r1.getY());
        assertNotNull(riderRepo.getRider(r1.getId()));
    }

    @Test
    void getRiderFailure() {
        riderRepo.addRider(r1.getId(), r1.getX(), r1.getY());
        assertNull(riderRepo.getRider("r2"));
    }
}