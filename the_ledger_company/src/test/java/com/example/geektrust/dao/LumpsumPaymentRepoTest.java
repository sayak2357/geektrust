package com.example.geektrust.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class LumpsumPaymentRepoTest {
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    @BeforeEach
    void setUp() {
        this.lumpsumPaymentRepo = new LumpsumPaymentRepo();
    }

    @Test
    void addLumpsum() {
        assertTrue(this.lumpsumPaymentRepo.addLumpsum(SAMPLE_USER,SAMPLE_BANK,SAMPLE_PRINCIPAL,SAMPLE_EMINUMBER));
    }

    @Test
    void getLumpsumPayments() {
        this.lumpsumPaymentRepo.addLumpsum(SAMPLE_USER,SAMPLE_BANK,SAMPLE_PRINCIPAL,SAMPLE_EMINUMBER);
        assertNotNull(this.lumpsumPaymentRepo.getLumpsumPayments(SAMPLE_USER,SAMPLE_BANK));
    }
}