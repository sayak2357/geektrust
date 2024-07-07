package com.geektrust.racetrackManagement.service;

import com.geektrust.racetrackManagement.entity.RacetrackBooking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperServiceTest {
    private HelperService helperService;
    private RacetrackBooking racetrackBooking1,racetrackBooking2,racetrackBooking3,racetrackBooking4;
    @BeforeEach
    void setUp() {
        this.helperService = new HelperService();
        this.racetrackBooking1 = new RacetrackBooking("14:33","18:50","Test123","CAR");
        this.racetrackBooking2 = new RacetrackBooking("16:23","20:00","Test1234","CAR");
        this.racetrackBooking3 = new RacetrackBooking("19:00","20:00","Test12345","CAR");
        this.racetrackBooking4 = new RacetrackBooking("15:12","18:30","Test123455","BIKE");
    }

    @Test
    void startTimeCheckerTrue() {
        assertTrue(this.helperService.startTimeChecker("14:56"));
    }
    @Test
    void startTimeCheckerFalse() {
        assertFalse(this.helperService.startTimeChecker("14:5Tes"));
        assertFalse(this.helperService.startTimeChecker("21:33"));
    }

    @Test
    void endTimeCheckerTrue() {
        assertTrue(this.helperService.endTimeChecker("18:56"));
    }

    @Test
    void endTimeCheckerFalse() {
        assertFalse(this.helperService.endTimeChecker("20:11"));
        assertFalse(this.helperService.endTimeChecker("test"));
    }

    @Test
    void isOverlapTrue() {
        assertTrue(this.helperService.isOverlap(racetrackBooking1,racetrackBooking2));
    }
    @Test
    void isOverlapFalse() {
        assertFalse(this.helperService.isOverlap(racetrackBooking1,racetrackBooking3));
        assertFalse(this.helperService.isOverlap(racetrackBooking1,racetrackBooking4));
    }

    @Test
    void minutesdifference() {
        assertEquals(this.helperService.minutesdifference("13:45","14:25"),40);
    }

    @Test
    void extensionCostCalculator() {
        assertEquals(this.helperService.extensionCostCalculator("16:00","16:10",false),0);
        assertEquals(this.helperService.extensionCostCalculator("16:00","16:10",true),50);
        assertEquals(this.helperService.extensionCostCalculator("16:00","17:10",true),100);
    }


}