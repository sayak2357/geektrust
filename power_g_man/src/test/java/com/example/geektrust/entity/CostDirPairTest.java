package com.example.geektrust.entity;

import com.example.geektrust.constants.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CostDirPairTest {
    private CostDirPair costDirPair;

    @BeforeEach
    void setUp() {
        this.costDirPair = new CostDirPair(50, Constants.directions.S);
    }
    @Test
    void getCost() {
        assertEquals(this.costDirPair.getCost(),50);
    }

    @Test
    void getDir() {
        assertEquals(this.costDirPair.getDir(),Constants.directions.S);
    }


}