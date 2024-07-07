package com.geektrust.racetrackManagement.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RacetrackBookingTest {
    private RacetrackBooking racetrackBooking;
    @BeforeEach
    void setUp() {
        this.racetrackBooking = new RacetrackBooking("13:00","16:00","TEST123","BIKE");
    }

    @Test
    void setTrack() {
        this.racetrackBooking.setTrack("VIP");
        assertEquals(this.racetrackBooking.getTrack(),"VIP");
    }

    @Test
    void getTrack() {
        assertNull(this.racetrackBooking.getTrack());
        this.racetrackBooking.setTrack("VIP");
        assertEquals(this.racetrackBooking.getTrack(),"VIP");
    }

    @Test
    void setIsExtendedFalse() {
        assertFalse(this.racetrackBooking.isExtended());
    }
    @Test
    void setIsExtendedTrue() {
        this.racetrackBooking.setExtended();
        assertTrue(this.racetrackBooking.isExtended());
    }

    @Test
    void getStartTime() {
        assertEquals(this.racetrackBooking.getStartTime(),"13:00");
    }

    @Test
    void getVehicleType() {
        assertEquals(this.racetrackBooking.getVehicleType(),"BIKE");
    }

    @Test
    void getVehicleNumber() {
        assertEquals(this.racetrackBooking.getVehicleNumber(),"TEST123");
    }

    @Test
    void isExtended() {
        assertFalse(this.racetrackBooking.isExtended());
    }

    @Test
    void getEndTime() {
        assertEquals(this.racetrackBooking.getEndTime(),"16:00");
    }

    @Test
    void setEndTime() {
        this.racetrackBooking.setEndTime("17:45");
        assertEquals(this.racetrackBooking.getEndTime(),"17:45");
    }

    @Test
    void setStartTime() {
        this.racetrackBooking.setStartTime("13:30");
        assertEquals(this.racetrackBooking.getStartTime(),"13:30");
    }



    @Test
    void testClone() {
        RacetrackBooking cloned = this.racetrackBooking.clone();
        assertEquals(this.racetrackBooking.getStartTime(),cloned.getStartTime());
        assertEquals(this.racetrackBooking.getEndTime(),cloned.getEndTime());
        assertEquals(this.racetrackBooking.getVehicleType(),cloned.getVehicleType());
    }
}