package com.example.geektrust.dao;

import com.example.geektrust.entity.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;


class LoanRepoTest {
    private LoanRepo loanRepo;
    private Loan SAMPLE_LOAN;
    @BeforeEach
    void setUp() {
        this.loanRepo = new LoanRepo();
        this.SAMPLE_LOAN = new Loan(SAMPLE_USER,SAMPLE_BANK,SAMPLE_PRINCIPAL,SAMPLE_TENURE,SAMPLE_INTEREST);
    }

    @Test
    void addLoan() {

        assertTrue(this.loanRepo.addLoan( SAMPLE_LOAN,SAMPLE_BANK, SAMPLE_USER));
        assertFalse(this.loanRepo.addLoan(SAMPLE_LOAN,SAMPLE_BANK, SAMPLE_USER));
    }


    @Test
    void getPrincipal() {

        this.loanRepo.addLoan(SAMPLE_LOAN,SAMPLE_BANK,SAMPLE_USER);
        assertEquals(this.loanRepo.getPrincipal(SAMPLE_USER, SAMPLE_BANK), SAMPLE_PRINCIPAL);
    }

    @Test
    void getTenure() {
        this.loanRepo.addLoan(SAMPLE_LOAN,SAMPLE_BANK,SAMPLE_USER);
        assertEquals(this.loanRepo.getTenure(SAMPLE_USER, SAMPLE_BANK), SAMPLE_TENURE);
    }

    @Test
    void getInterest() {
        this.loanRepo.addLoan(SAMPLE_LOAN,SAMPLE_BANK,SAMPLE_USER);
        assertEquals(this.loanRepo.getInterest(SAMPLE_USER, SAMPLE_BANK), SAMPLE_INTEREST);
    }
}