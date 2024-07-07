package com.geektrust.racetrackManagement.service;

import com.geektrust.racetrackManagement.entity.RacetrackBooking;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static com.geektrust.racetrackManagement.constants.Constants.*;
import static com.geektrust.racetrackManagement.constants.Constants.VIP_SUV_HOURLY_COST;

public class HelperService {

    public boolean startTimeChecker(String startTime){
        try {
            LocalTime startLimit = LocalTime.parse(START_BOOKING_TIME);
            LocalTime endLimit   = LocalTime.parse(END_BOOKING_TIME);
            LocalTime curr = LocalTime.parse(startTime);
            return (curr.isAfter(startLimit)||curr.equals(startLimit)) && (curr.equals(endLimit)||curr.isBefore(endLimit));
        }
        catch (Exception e) {
            return false;
        }
    }
    public boolean endTimeChecker(String endTime){
        try {
            LocalTime startLimit = LocalTime.parse(START_BOOKING_TIME);
            LocalTime endLimit   = LocalTime.parse(END_BOOKING_EXTENSION_TIME);
            LocalTime curr = LocalTime.parse(endTime);
            return curr.isAfter(startLimit) && (curr.equals(endLimit)||curr.isBefore(endLimit));
        }
        catch (Exception e) {
            return false;
        }
    }
    public boolean isOverlap(RacetrackBooking rtb1, RacetrackBooking rtb2){
        if(!rtb1.getVehicleType().equals(rtb2.getVehicleType()))
            return false;
        LocalTime aStart = LocalTime.parse(rtb1.getStartTime());
        LocalTime aEnd = LocalTime.parse(rtb1.getEndTime());
        LocalTime bStart = LocalTime.parse(rtb2.getStartTime());
        LocalTime bEnd = LocalTime.parse(rtb2.getEndTime());
        //return !(b.getEnd()>=a.getEnd() || b.getEnd()<=a.getStart());
        return !((bStart.isAfter(aEnd) || bStart.equals(aEnd)) || (bEnd.isBefore(aStart) || bEnd.equals(aStart)));
    }
    public Long minutesdifference(String low, String high){
        LocalTime lt1 = LocalTime.parse(low);
        LocalTime lt2 = LocalTime.parse(high);
        return lt1.until(lt2, ChronoUnit.MINUTES);
    }
    public int extensionCostCalculator(String oldEndTime, String newEndTime, boolean isExtended){
        int minuteDiff = Math.toIntExact(minutesdifference(oldEndTime,newEndTime));
        int vehicleBookingCost = 0;
        Integer cost = EXTENDED_BOOKING_HOURLY_COST;
        if(minuteDiff>15){
            int hours = minuteDiff/60;
            int mins = minuteDiff%60;
            if(mins>0){
                hours++;
            }
            vehicleBookingCost = hours*cost;
        }
        else if(minuteDiff<=15 && isExtended){
            vehicleBookingCost = cost;
        }
        return vehicleBookingCost;

    }

}
