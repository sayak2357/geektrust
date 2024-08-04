package com.example.geektrust.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LumpsumTest {
    private Lumpsum lumpsum;
    @BeforeEach
    void setUp() {
        this.lumpsum = new Lumpsum(50d,5);
    }
    @Test
    void getAmount() {
        assertEquals(this.lumpsum.getAmount(),50d);
    }

    @Test
    void setAmount() {
        this.lumpsum.setAmount(10d);
        assertEquals(this.lumpsum.getAmount(),10d);
    }

    @Test
    void getEmiNumber() {
        assertEquals(this.lumpsum.getEmiNumber(),5);
    }

}