package com.example.geektrust.service;

import com.example.geektrust.entity.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class HelperServiceTest {
    private Loan loan;
    private HelperService helperService;
    @BeforeEach
    void setUp() {
        this.loan = new Loan(SAMPLE_USER,SAMPLE_BANK,SAMPLE_PRINCIPAL,SAMPLE_TENURE,SAMPLE_INTEREST);
        this.helperService = new HelperService();
    }

    @Test
    void getNetAmount() {
        assertEquals(helperService.getNetAmount(loan.getPrincipal(),loan.getNumber_of_years_tenure(), loan.getInterest()),loan.getPrincipal()+(loan.getPrincipal()*loan.getNumber_of_years_tenure()*loan.getInterest()/100));
    }

    @Test
    void getMonthlyEmi() {
        Integer monthlyEmi = (int) Math.ceil( this.helperService.getNetAmount(loan.getPrincipal(),loan.getNumber_of_years_tenure(),loan.getInterest()) /(loan.getNumber_of_years_tenure()*12));
        assertEquals(helperService.getMonthlyEmi(loan.getPrincipal(),loan.getNumber_of_years_tenure(), loan.getInterest()),monthlyEmi);
    }
}