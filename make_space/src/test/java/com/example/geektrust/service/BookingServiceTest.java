package com.example.geektrust.service;

import com.example.geektrust.entity.Room;
import com.example.geektrust.entity.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private BookingService bookingService;
    @BeforeEach
    void beforeEach() {
        this.bookingService = new BookingService();
    }
    @Test
    void isOverlap() {
        TimeSlot ts1 = new TimeSlot("10:00","11:00");
        TimeSlot ts2 = new TimeSlot("10:45","12:00");
        assertTrue(bookingService.isOverlap(ts1,ts2));
    }

    @Test
    void addRoom() {
        assertTrue(bookingService.addRoom("test"));
    }


    @Test
    void bufferSlotOverlap() {
        assertTrue(bookingService.bufferSlotOverlap("09:10","10:10"));
        assertTrue(bookingService.bufferSlotOverlap("13:30","14:10"));
        assertTrue(bookingService.bufferSlotOverlap("18:50","20:30"));
    }

    @Test
    void bufferSlotNotOverlap() {
        assertFalse(bookingService.bufferSlotOverlap("09:15","10:10"));
        assertFalse(bookingService.bufferSlotOverlap("12:30","13:15"));
        assertFalse(bookingService.bufferSlotOverlap("18:10","18:45"));
    }

    @Test
    void allotRoom() {
        assertTrue(bookingService.allotRoom("10:30","13:00",5));
    }

    @Test
    void allotRoomFailDueToBufferslotOverlap() {
        assertFalse(bookingService.allotRoom("10:30","13:30",5));
    }

    @Test
    void allotRoomFailDueToCapacityOverlap() {
        assertFalse(bookingService.allotRoom("10:30","12:30",30));
    }

    @Test
    void vacanciesAvailable() {
        List<String> roomsVacantForBooking = bookingService.vacancies("14:30","17:00");
        assertTrue(roomsVacantForBooking.size()>0);
    }

    @Test
    void vacanciesNotAvailableDueToBffertimeOverlap() {
        List<String> roomsVacantForBooking = bookingService.vacancies("13:30","17:00");
        assertFalse(roomsVacantForBooking.size()>0);
    }
}