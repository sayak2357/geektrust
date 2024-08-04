package com.example.geektrust.dao;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.Lumpsum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        assertTrue(this.loanRepo.addLoan(USER,BANK,PRINCIPAL,TENURE,INTEREST));
        assertFalse(this.loanRepo.addLoan(USER,BANK,PRINCIPAL,TENURE,INTEREST));
    }

    @Test
    void addLumpsum() {
        this.loanRepo.addLoan(BANK,USER,PRINCIPAL,TENURE,INTEREST);
        this.loanRepo.addLumpsum(USER,BANK,PRINCIPAL,1);
        assertTrue(this.loanRepo.getLumpsumPayments(USER,BANK).size()==1);
    }

    @Test
    void getLumpsumPayments() {
        this.loanRepo.addLoan(BANK,USER,PRINCIPAL,TENURE,INTEREST);
        this.loanRepo.addLumpsum(USER,BANK,PRINCIPAL,1);
        List<Lumpsum> lumpsums = this.loanRepo.getLumpsumPayments(USER,BANK);
        assertEquals(lumpsums.size(),1);
    }

    @Test
    void getMonthlyEmiAmount() {
        this.loanRepo.addLoan(BANK,USER,PRINCIPAL,TENURE,INTEREST);
        assertEquals(this.loanRepo.getMonthlyEmiAmount(USER,BANK),9);
    }

    @Test
    void getNetAmount() {
        this.loanRepo.addLoan(BANK,USER,PRINCIPAL,TENURE,INTEREST);
        assertEquals(this.loanRepo.getNetAmount(USER,BANK),105);
    }
}