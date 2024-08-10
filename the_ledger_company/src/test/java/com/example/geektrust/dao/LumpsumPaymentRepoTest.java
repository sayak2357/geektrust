package com.example.geektrust.dao;

import com.example.geektrust.entity.Lumpsum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class LumpsumPaymentRepoTest {
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    private Lumpsum SAMPLE_LUMPSUM;
    @BeforeEach
    void setUp() {
        this.lumpsumPaymentRepo = new LumpsumPaymentRepo();
        this.SAMPLE_LUMPSUM = new Lumpsum(SAMPLE_PRINCIPAL,SAMPLE_EMINUMBER);
    }

    @Test
    void addLumpsum() {
        assertTrue(this.lumpsumPaymentRepo.addLumpsum(SAMPLE_LUMPSUM,SAMPLE_USER,SAMPLE_BANK));
    }

    @Test
    void getLumpsumPayments() {
        this.lumpsumPaymentRepo.addLumpsum(SAMPLE_LUMPSUM,SAMPLE_USER,SAMPLE_BANK);
        assertNotNull(this.lumpsumPaymentRepo.getLumpsumPayments(SAMPLE_USER,SAMPLE_BANK));
    }
}