package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.dao.LumpsumPaymentRepo;
import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.Lumpsum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {
    private LoanRepo loanRepo;
    private BalanceService balanceService;
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    private Loan SAMPLE_LOAN;
    @BeforeEach
    void setUp() {
        this.loanRepo = new LoanRepo();
        this.lumpsumPaymentRepo = new LumpsumPaymentRepo();
        this.balanceService = new BalanceService(this.loanRepo,this.lumpsumPaymentRepo);
        this.SAMPLE_LOAN = new Loan(SAMPLE_USER,SAMPLE_BANK,SAMPLE_PRINCIPAL,SAMPLE_TENURE,SAMPLE_INTEREST);
        this.loanRepo.addLoan(SAMPLE_LOAN,SAMPLE_BANK, SAMPLE_USER);

    }

    @Test
    void getTotalAmountPaid() {
        assertEquals(this.balanceService.getTotalAmountPaid(SAMPLE_USER, SAMPLE_BANK,2),18);
    }

    @Test
    void getEmisRemaining() {
        Lumpsum lumpsum = new Lumpsum(30d,3);
        this.lumpsumPaymentRepo.addLumpsum(lumpsum,SAMPLE_USER, SAMPLE_BANK);
        assertEquals(this.balanceService.getEmisRemaining(SAMPLE_USER, SAMPLE_BANK,5),4);
    }
}