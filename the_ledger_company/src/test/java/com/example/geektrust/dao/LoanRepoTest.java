package com.example.geektrust.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;


class LoanRepoTest {
    private LoanRepo loanRepo;
    @BeforeEach
    void setUp() {
        this.loanRepo = new LoanRepo();
    }

    @Test
    void addLoan() {

        assertTrue(this.loanRepo.addLoan( SAMPLE_BANK, SAMPLE_USER, SAMPLE_PRINCIPAL, SAMPLE_TENURE, SAMPLE_INTEREST));
        assertFalse(this.loanRepo.addLoan(SAMPLE_BANK, SAMPLE_USER,SAMPLE_PRINCIPAL, SAMPLE_TENURE, SAMPLE_INTEREST));
    }


    @Test
    void getPrincipal() {
        this.loanRepo.addLoan(SAMPLE_BANK,SAMPLE_USER, SAMPLE_PRINCIPAL, SAMPLE_TENURE, SAMPLE_INTEREST);
        assertEquals(this.loanRepo.getPrincipal(SAMPLE_USER, SAMPLE_BANK), SAMPLE_PRINCIPAL);
    }

    @Test
    void getTenure() {
        this.loanRepo.addLoan(SAMPLE_BANK,SAMPLE_USER, SAMPLE_PRINCIPAL, SAMPLE_TENURE, SAMPLE_INTEREST);
        assertEquals(this.loanRepo.getTenure(SAMPLE_USER, SAMPLE_BANK), SAMPLE_TENURE);
    }

    @Test
    void getInterest() {
        this.loanRepo.addLoan(SAMPLE_BANK,SAMPLE_USER, SAMPLE_PRINCIPAL, SAMPLE_TENURE, SAMPLE_INTEREST);
        assertEquals(this.loanRepo.getInterest(SAMPLE_USER, SAMPLE_BANK), SAMPLE_INTEREST);
    }
}