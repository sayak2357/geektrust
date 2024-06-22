package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BufferTimeTest {

    private BufferTime bufferTime;
    @BeforeEach
    void beforeEach() {
        this.bufferTime = new BufferTime();
    }
    @Test
    void getBufferSlots() {
        assertTrue(this.bufferTime.getBufferSlots().size()>0);
    }
}