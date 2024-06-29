package com.example.geektrust.repository;

import com.example.geektrust.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverRepoTest {
    private DriverRepo driverRepo;
    @BeforeEach
    void initialize(){
        driverRepo = new DriverRepo();
    }

    @Test
    void addDriverSuccess() {
        boolean d1 = driverRepo.addDriver("d1",12.5,-16.44);
        assertTrue(d1);
    }
    @Test
    void addDriverFailure() {
        boolean d1 = driverRepo.addDriver("d1",12.5,-16.44);
        boolean d2 = driverRepo.addDriver("d1",12.5,-16.44);
        assertFalse(d2);
    }

    @Test
    void getDriverTrue() {
        driverRepo.addDriver("d1",12.5,-16.44);
        Driver d = driverRepo.getDriver("d1");
        assertNotNull(d);
    }

    @Test
    void getDriverFalse() {
        driverRepo.addDriver("d1",12.5,-16.44);
        Driver d = driverRepo.getDriver("d2");
        assertNull(d);
    }

    @Test
    void getDrivers() {
        driverRepo.addDriver("d1",12.5,-16.44);
        driverRepo.addDriver("d2",1.25,7.78);
        List<Driver> driverList = driverRepo.getDrivers();
        assertTrue(driverList.size()==2);
    }
}