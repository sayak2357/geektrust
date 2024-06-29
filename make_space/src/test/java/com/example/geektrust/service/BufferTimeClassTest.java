package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BufferTimeClassTest {

    private BufferTimeClass bufferTimeClass;
    @BeforeEach
    void beforeEach() {
        this.bufferTimeClass = new BufferTimeClass();
    }
    @Test
    void getBufferSlots() {
        assertTrue(this.bufferTimeClass.getBufferSlots().size()>0);
    }

    @Test
    void bufferSlotOverlap() {
        assertTrue(bufferTimeClass.bufferSlotOverlap("09:10","10:10"));
        assertTrue(bufferTimeClass.bufferSlotOverlap("13:30","14:10"));
        assertTrue(bufferTimeClass.bufferSlotOverlap("18:50","20:30"));
    }

    @Test
    void bufferSlotNotOverlap() {
        assertFalse(bufferTimeClass.bufferSlotOverlap("09:15","10:10"));
        assertFalse(bufferTimeClass.bufferSlotOverlap("12:30","13:15"));
        assertFalse(bufferTimeClass.bufferSlotOverlap("18:10","18:45"));
    }
}