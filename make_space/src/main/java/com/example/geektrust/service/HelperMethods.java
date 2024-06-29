package com.example.geektrust.service;

import com.example.geektrust.entity.TimeSlot;

import java.time.LocalTime;

public class HelperMethods {
    public static boolean isOverlap(TimeSlot a, TimeSlot b){
        LocalTime aStart = LocalTime.parse(a.getStart());
        LocalTime aEnd = LocalTime.parse(a.getEnd());
        LocalTime bStart = LocalTime.parse(b.getStart());
        LocalTime bEnd = LocalTime.parse(b.getEnd());
        //return !(b.getEnd()>=a.getEnd() || b.getEnd()<=a.getStart());
        return !((bStart.isAfter(aEnd) || bStart.equals(aEnd)) || (bEnd.isBefore(aStart) || bEnd.equals(aStart)));
    }
}
