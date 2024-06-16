package com.example.geektrust.service;

import java.time.LocalTime;

public class TimestampSyntaxChecker {
    public boolean isValidTimestamps(String startTime, String endTime){
        return isNuericallyValidTimestamp(startTime,endTime) && intervalCheck(startTime,endTime);
    }

    private boolean isNuericallyValidTimestamp(String startTime, String endTime){
        try {
            LocalTime start = LocalTime.parse(startTime);
            LocalTime end = LocalTime.parse(endTime);
            return end.isAfter(start);
        }
        catch (Exception e) {
            return false;
        }
    }
    private boolean intervalCheck(String startTime, String endTime){
        Integer smins = Integer.valueOf(startTime.split(":")[1]);
        Integer emins = Integer.valueOf(endTime.split(":")[1]);
        return !(smins%15>0 || emins%15>0);
    }
}
