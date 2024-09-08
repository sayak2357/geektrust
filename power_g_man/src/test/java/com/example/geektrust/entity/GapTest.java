package com.example.geektrust.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GapTest {
    private Gap gap;
    @BeforeEach
    void setUp() {
        this.gap = new Gap(5,-10);
    }

    @Test
    void getHorizontal() {
        assertEquals(this.gap.getHorizontal(),5);
    }

    @Test
    void getVertical() {
        assertEquals(this.gap.getVertical(),-10);
    }
}