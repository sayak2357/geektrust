package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicatonServiceTest {
    private ApplicatonService applicatonService;
    @BeforeEach
    void setUp() {
        this.applicatonService = new ApplicatonService();
    }

    @Test
    void run() {
        assertTrue(this.applicatonService.run("sample_input/input1.txt"));
    }

    @Test
    void generateResponseArrival() {
        List<String> sampleBoggies = new ArrayList<>();
        String sampleTrain = "SAMPLE_TRAIN";
        sampleBoggies.add("SAMPLE");
        String expected = sampleTrain+" ENGINE "+sampleBoggies.get(0)+" ";
        assertEquals(expected,this.applicatonService.generateResponseArrival(sampleTrain,sampleBoggies));
    }
}