package com.geektrust.racetrackManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RaceTrackBookingManagementServiceTest {
    private RaceTrackBookingManagementService raceTrackBookingManagementService;
    @BeforeEach
    void setUp() {
        this.raceTrackBookingManagementService = new RaceTrackBookingManagementService();
    }

    @Test
    void bookTrue() {
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test123","14:45"));
        assertTrue(this.raceTrackBookingManagementService.book("CAR","test1234","15:45"));
        assertTrue(this.raceTrackBookingManagementService.book("SUV","test12345","15:15"));
    }

    @Test
    void bookFalse() {
        assertTrue(this.raceTrackBookingManagementService.book("SUV","test123","15:15"));
        assertTrue(this.raceTrackBookingManagementService.book("SUV","test1234","14:15"));
        assertTrue(this.raceTrackBookingManagementService.book("SUV","test12345","16:15"));
        assertFalse(this.raceTrackBookingManagementService.book("SUV","test123456","15:55"));

        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test123","13:45"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test1234","13:05"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test1235","14:25"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test1236","15:05"));
        assertFalse(this.raceTrackBookingManagementService.book("BIKE","test1237","14:35"));
    }

    @Test
    void extendBookingImprovedTrue() {
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test123","13:00"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test124","13:05"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test125","14:25"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test126","16:00"));
        assertTrue(this.raceTrackBookingManagementService.extendBookingImproved("test123","17:00"));
    }

    @Test
    void extendBookingImprovedFalse() {
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test123","13:00"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test124","13:05"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test125","14:25"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test126","15:00"));
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test126","16:00"));
        assertFalse(this.raceTrackBookingManagementService.extendBookingImproved("test123","17:00"));
    }

    @Test
    void getrevenues() {
        assertTrue(this.raceTrackBookingManagementService.book("BIKE","test123","14:45"));
        assertTrue(this.raceTrackBookingManagementService.book("CAR","test1234","15:45"));
        assertTrue(this.raceTrackBookingManagementService.book("CAR","test12345","16:45"));
        assertTrue(this.raceTrackBookingManagementService.book("CAR","test12346","16:05"));
        assertEquals(this.raceTrackBookingManagementService.getrevenues().getRegularRevenue(),900);
        assertEquals(this.raceTrackBookingManagementService.getrevenues().getVipRevenue(),750);
    }
}