package com.example.geektrust.constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {
    private Constants constants;
    @BeforeEach
    void setUp() {
        this.constants = new Constants();
    }

    @Test
    void getFromHydDistance() {
        assertEquals(this.constants.getFromHydDistance().get("NGP"),400);
    }

    @Test
    void getTrainAStationsToHyd() {
        List<String> testList = this.constants.getTrainAStationsToHyd();
        assertEquals(testList.get(0),"CHN");
    }

    @Test
    void getTrainBStationsToHyd() {
        List<String> testList = this.constants.getTrainBStationsToHyd();
        assertEquals(testList.get(0),"TVC");
    }
}