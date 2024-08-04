package com.example.geektrust.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    private Loan loan;
    @BeforeEach
    void setUp() {
        this.loan = new Loan("testUser","testBank",100d,1,5d);
    }

    @Test
    void getPrincipal() {
        assertEquals(this.loan.getPrincipal(),100d);
    }

    @Test
    void getMonthlyEmi() {
        assertEquals(this.loan.getMonthlyEmi(),9);
    }

    @Test
    void getLumpsumPayments() {
        assertEquals(this.loan.getLumpsumPayments().size(),0);
    }

    @Test
    void getNetAmount() {
        assertEquals(this.loan.getNetAmount(),105);
    }

    @Test
    void getUser() {
        assertEquals(this.loan.getUser(),"testUser");
    }


    @Test
    void getInterest() {
        assertEquals(this.loan.getInterest(),5);
    }

    @Test
    void getNumber_of_years_tenure() {
        assertEquals(this.loan.getNumber_of_years_tenure(),1);
    }

    @Test
    void getBank() {
        assertEquals(this.loan.getBank(),"testBank");
    }

    @Test
    void addLumpsumPayment() {
        this.loan.addLumpsumPayment(new Lumpsum(10d,10));
        assertEquals(this.loan.getLumpsumPayments().size(),1);
    }
}