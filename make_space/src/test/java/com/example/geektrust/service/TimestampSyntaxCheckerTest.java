package com.example.geektrust.service;

import com.example.geektrust.entity.Room;
import com.example.geektrust.entity.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimestampSyntaxCheckerTest {
    private TimestampSyntaxChecker timestampSyntaxChecker;
    @BeforeEach
    void beforeEach() {
        this.timestampSyntaxChecker = new TimestampSyntaxChecker();

    }
    @Test
    void isValidTimestamps() {
            boolean correct = timestampSyntaxChecker.isValidTimestamps("10:00","12:00");
            assertTrue(correct);
    }

    @Test
    void isInValidTimestamps() {
        boolean incorrect = timestampSyntaxChecker.isValidTimestamps("10:00","09:00");
        assertFalse(incorrect);
    }

    @Test
    void isInValidTimestampInterval() {
        boolean incorrect = timestampSyntaxChecker.isValidTimestamps("10:00","10:25");
        assertFalse(incorrect);
    }
}