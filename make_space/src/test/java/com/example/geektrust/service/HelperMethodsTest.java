package com.example.geektrust.service;

import com.example.geektrust.entity.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperMethodsTest {

    private HelperMethods helperMethods;
    @BeforeEach
    void beforeEach() {
        this.helperMethods = new HelperMethods();
    }

    @Test
    void isOverlap() {
        TimeSlot ts1 = new TimeSlot("10:00","11:00");
        TimeSlot ts2 = new TimeSlot("10:45","12:00");
        assertTrue(helperMethods.isOverlap(ts1,ts2));
    }

    @Test
    void isNotOverlap() {
        TimeSlot ts1 = new TimeSlot("10:00","11:00");
        TimeSlot ts2 = new TimeSlot("12:45","13:00");
        assertFalse(helperMethods.isOverlap(ts1,ts2));
    }
}