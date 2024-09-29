package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainMergeServiceTest {
    private TrainMergeService trainMergeService;
    @BeforeEach
    void setUp() {
        this.trainMergeService = new TrainMergeService();
    }
    @Test
    void addToTrain() {
        assertTrue(trainMergeService.addToTrain("PNE",false));
        assertTrue(trainMergeService.addToTrain("PTA",false));

        assertTrue(trainMergeService.addToTrain("CHN",true));
        assertTrue(trainMergeService.addToTrain("NGP",true));

    }

    @Test
    void getArrivalList() {
        trainMergeService.addToTrain("PNE",false);
        trainMergeService.addToTrain("PTA",false);
        List<String> testList = trainMergeService.getArrivalList(false);
        assertTrue(testList.contains("PTA"));
        assertFalse(testList.contains("PNE"));


        trainMergeService.addToTrain("CHN",true);
        trainMergeService.addToTrain("NGP",true);
        testList = trainMergeService.getArrivalList(true);
        assertTrue(testList.contains("NGP"));
        assertFalse(testList.contains("CHN"));
    }

    @Test
    void getDeparatureList() {
        trainMergeService.addToTrain("CHN",true);
        trainMergeService.addToTrain("NGP",true);
        trainMergeService.addToTrain("PNE",false);
        trainMergeService.addToTrain("PTA",false);
        List<String> testDptList = this.trainMergeService.getDeparatureList();
        assertEquals(testDptList.get(0),"PTA");
    }
}