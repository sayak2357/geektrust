package com.example.geektrust.entity;

public class TimeSlot {
    String start,end;
    public TimeSlot(String start, String end){
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
