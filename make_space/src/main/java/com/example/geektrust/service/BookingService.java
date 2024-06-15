package com.example.geektrust.service;

import com.example.geektrust.entity.Room;
import com.example.geektrust.entity.TimeSlot;

import java.util.*;

public class BookingService {
    private Map<String, List<TimeSlot>> bookings;
    public BookingService(){
        this.bookings = new HashMap<>();
    }
    public void addRoom(String room){
        List<TimeSlot> ls = new ArrayList<>();
        this.bookings.put(room,ls);
    }
    public void addRooms(List<Room> rooms){
        for(Room r:rooms) {
            List<TimeSlot> ls = new ArrayList<>();
            this.bookings.put(r.getName(), ls);
        }
    }
    public boolean addBooking(String room, int startTime, int endTime){
        TimeSlot ts = new TimeSlot(startTime,endTime);
        List<TimeSlot> roombookings = this.bookings.get(room);
        if(roombookings.size()==0)
            roombookings.add(ts);
        else{
            for(TimeSlot booked_ts:roombookings){
                if(isOverlap(booked_ts,ts))
                    return false;
            }
        }
        this.bookings.put(room,roombookings);
        return true;
    }
    public boolean isOverlap(TimeSlot a, TimeSlot b){
        return !(b.getEnd()>=a.getEnd() || b.getEnd()<=a.getStart());
    }
    public boolean isBookingFeasible(String room, int startTime, int endTime){
        TimeSlot ts = new TimeSlot(startTime,endTime);
        List<TimeSlot> roombookings = this.bookings.get(room);
        if(roombookings.size()==0)
            return true;
        else{
            for(TimeSlot booked_ts:roombookings){
                if(isOverlap(booked_ts,ts))
                    return false;
            }
        }
        return true;
    }
}
