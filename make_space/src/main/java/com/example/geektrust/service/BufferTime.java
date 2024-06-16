package com.example.geektrust.service;

import com.example.geektrust.entity.TimeSlot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
public class BufferTime {
    List<TimeSlot> bufferSlots;
    public BufferTime(){
        this.bufferSlots = new ArrayList<>();
        populateBufferTime();
    }
    private void populateBufferTime(){
        TimeSlot bf1 = new TimeSlot("09:00","09:15");
        TimeSlot bf2 = new TimeSlot("13:15","13:45");
        TimeSlot bf3 = new TimeSlot("18:45","19:00");
        this.bufferSlots.add(bf1);
        this.bufferSlots.add(bf2);
        this.bufferSlots.add(bf3);
    }
    public List<TimeSlot> getBufferSlots(){
        return this.bufferSlots;
    }
}
