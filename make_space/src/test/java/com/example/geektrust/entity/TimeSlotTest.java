package com.example.geektrust.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    private TimeSlot timeSlot;
    @BeforeEach
    void beforeEach() {
        this.timeSlot = new TimeSlot("10:00","12:00");
    }

    @Test
    void getStart() {
        assertEquals("10:00",timeSlot.getStart());
    }

    @Test
    void getEnd() {
        assertEquals("12:00",timeSlot.getEnd());
    }

    @Test
    void checkNotNull() {
        assertNotNull(this.timeSlot);
    }
}