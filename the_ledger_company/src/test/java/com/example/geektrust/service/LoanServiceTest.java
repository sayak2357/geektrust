package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {
    private LoanService loanService;
    @BeforeEach
    void setUp() {
        this.loanService = new LoanService(new LoanRepo());
    }

    @Test
    void addLoanSuccess() {
        assertTrue(this.loanService.addLoan(SAMPLE_BANK,SAMPLE_USER,SAMPLE_PRINCIPAL,SAMPLE_TENURE,SAMPLE_INTEREST));
    }

    @Test
    void addLoanFailure() {
        this.loanService.addLoan(SAMPLE_BANK,SAMPLE_USER,SAMPLE_PRINCIPAL,SAMPLE_TENURE,SAMPLE_INTEREST);
        assertFalse(this.loanService.addLoan(SAMPLE_BANK,SAMPLE_USER,SAMPLE_PRINCIPAL,SAMPLE_TENURE,SAMPLE_INTEREST));
    }
}