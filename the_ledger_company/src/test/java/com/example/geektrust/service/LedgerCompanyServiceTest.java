package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LedgerCompanyServiceTest {
    private LedgerCompanyService ledgerCompanyService;
    @BeforeEach
    void setUp() {
        this.ledgerCompanyService = new LedgerCompanyService();
    }

    @Test
    void run() {
        assertTrue(this.ledgerCompanyService.run("sample_input/input2.txt"));
    }
}