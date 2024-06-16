package com.example.geektrust.service;

import com.example.geektrust.entity.Room;
import com.example.geektrust.entity.TimeSlot;
import com.example.geektrust.repository.Rooms;

import java.time.LocalTime;
import java.util.*;

public class BookingService {
    private Map<String, List<TimeSlot>> bookings;
    private List<TimeSlot> bufferSlots;
    private Rooms rooms;
    private List<Room> allRooms;
    public BookingService(){
        this.bookings = new LinkedHashMap<>();
        this.bufferSlots = new BufferTime().getBufferSlots();
        this.rooms = Initializer.getInitializedRooms();
        this.allRooms = rooms.getAllRooms();
        addRooms(allRooms);
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
    public List<String> vacancies(String startTime, String endTime){
        TimeSlot ts = new TimeSlot(startTime,endTime);
        List<String> vacantRooms = new ArrayList<>();

        if(bufferSlotOverlap(startTime,endTime))
            return vacantRooms;

        for(String roomName: this.bookings.keySet()){
            List<TimeSlot> bookingsOfRoom = this.bookings.get(roomName);
            if(bookingsOfRoom.size()==0)
                vacantRooms.add(roomName);
            else{
                Boolean flag = true;
                for(TimeSlot tsb:bookingsOfRoom){
                    if(isOverlap(tsb,ts)){
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    vacantRooms.add(roomName);
            }
        }
        return vacantRooms;
    }
    public String addBooking(String room, String startTime, String endTime){
        TimeSlot ts = new TimeSlot(startTime,endTime);
        List<TimeSlot> roombookings = this.bookings.get(room);


        if(roombookings.size()>0){
            for(TimeSlot booked_ts:roombookings){
                if(isOverlap(booked_ts,ts))
                    return null;
            }
        }
        roombookings.add(ts);
        this.bookings.put(room,roombookings);
        return room;
    }
    public boolean isOverlap(TimeSlot a, TimeSlot b){
        LocalTime aStart = LocalTime.parse(a.getStart());
        LocalTime aEnd = LocalTime.parse(a.getEnd());
        LocalTime bStart = LocalTime.parse(b.getStart());
        LocalTime bEnd = LocalTime.parse(b.getEnd());
        //return !(b.getEnd()>=a.getEnd() || b.getEnd()<=a.getStart());
        return !((bStart.isAfter(aEnd) || bStart.equals(aEnd)) || (bEnd.isBefore(aStart) || bEnd.equals(aStart)));
    }

    public boolean bufferSlotOverlap(String startTime, String endTime){
        TimeSlot curr = new TimeSlot(startTime,endTime);
        for(TimeSlot ts:this.bufferSlots){
            if(isOverlap(ts,curr)){
                return true;
            }
        }
        return false;
    }
    public void allotRoom(String startTime, String endTime, Integer count){
        TimeSlot curr = new TimeSlot(startTime,endTime);
        boolean roomBooked = false;

        if(bufferSlotOverlap(startTime,endTime)){
            System.out.println("NO_VACANT_ROOM");
            return;
        }
        for(Room room:this.allRooms){
            if(room.getCapacity()>=count)
            {
                String bookedRoom =  addBooking(room.getName(),startTime,endTime);
                if(bookedRoom!=null){
                    roomBooked = true;
                    System.out.println(bookedRoom);
                    break;
                }
            }
        }
        if(!roomBooked)
            System.out.println("NO_VACANT_ROOM");
    }
}
