package com.example.geektrust.service;

import com.example.geektrust.dao.LumpsumPaymentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class LumpsumPaymentServiceTest {
    private LumpsumPaymentService lumpsumPaymentService;
    @BeforeEach
    void setUp() {
        this.lumpsumPaymentService = new LumpsumPaymentService(new LumpsumPaymentRepo());
    }

    @Test
    void addLumpsum() {
        assertTrue(this.lumpsumPaymentService.addLumpsum(SAMPLE_USER,SAMPLE_BANK,SAMPLE_PRINCIPAL,SAMPLE_EMINUMBER));
    }
}