package com.example.geektrust.entity;

public class TimeSlot {
    int start,end;
    public TimeSlot(int start, int end){
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
