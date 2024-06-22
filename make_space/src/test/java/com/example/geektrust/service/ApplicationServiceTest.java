package com.example.geektrust.service;

import com.example.geektrust.entity.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationServiceTest {
    private ApplicationService applicationService;

    @BeforeEach
    void beforeEach() {
        this.applicationService = new ApplicationService();
    }
    @Test
    void serviceUp() {

        assertNotNull(this.applicationService);
    }

}