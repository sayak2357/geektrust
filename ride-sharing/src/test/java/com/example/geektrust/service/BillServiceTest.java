package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillServiceTest {
    private BillService billService;
    @BeforeEach
    void initialize(){
        this.billService = new BillService();
    }
    @Test
    void generateBill() {
        double bill = billService.generateBill(1d,1d);
        assertTrue(bill==70.2d);
    }
}