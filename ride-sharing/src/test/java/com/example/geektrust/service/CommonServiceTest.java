package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonServiceTest {
    private CommonService commonService;
    @BeforeEach
    void setUp() {
        this.commonService = new CommonService();
    }

    @Test
    void round() {
        Double sample = 88.34882d;
        assertEquals(88.35,commonService.round(sample,2));
    }
}