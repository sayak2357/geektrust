package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.dao.LumpsumPaymentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {
    private LoanRepo loanRepo;
    private BalanceService balanceService;
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    @BeforeEach
    void setUp() {
        this.loanRepo = new LoanRepo();
        this.lumpsumPaymentRepo = new LumpsumPaymentRepo();
        this.balanceService = new BalanceService(this.loanRepo,this.lumpsumPaymentRepo);
        this.loanRepo.addLoan(SAMPLE_BANK, SAMPLE_USER, SAMPLE_PRINCIPAL, SAMPLE_TENURE, SAMPLE_INTEREST);

    }

    @Test
    void getTotalAmountPaid() {
        assertEquals(this.balanceService.getTotalAmountPaid(SAMPLE_USER, SAMPLE_BANK,2),18);
    }

    @Test
    void getEmisRemaining() {
        this.lumpsumPaymentRepo.addLumpsum(SAMPLE_USER, SAMPLE_BANK,30d,3);
        assertEquals(this.balanceService.getEmisRemaining(SAMPLE_USER, SAMPLE_BANK,5),4);
    }
}