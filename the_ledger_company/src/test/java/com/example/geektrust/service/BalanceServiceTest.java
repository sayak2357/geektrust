package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.geektrust.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {
    private LoanRepo loanRepo;
    private BalanceService balanceService;
    @BeforeEach
    void setUp() {
        this.loanRepo = new LoanRepo();
        this.balanceService = new BalanceService(this.loanRepo);
        this.loanRepo.addLoan(BANK,USER,PRINCIPAL,TENURE,INTEREST);

    }

    @Test
    void getTotalAmountPaid() {
        assertEquals(this.balanceService.getTotalAmountPaid(USER,BANK,2),18);
    }

    @Test
    void getEmisRemaining() {
        this.loanRepo.addLumpsum(USER,BANK,30d,3);
        assertEquals(this.balanceService.getEmisRemaining(USER,BANK,5),4);
    }
}