package com.example.geektrust.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {
    private ApplicationService applicationService;
    @BeforeEach
    void setUp() {
        this.applicationService = new ApplicationService();
    }

    @Test
    void run() {
        assertTrue(this.applicationService.run("sample_input/input1.txt"));
    }
}