package com.geektrust.racetrackManagement.repository;

import com.geektrust.racetrackManagement.entity.RacetrackBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacetrackBookingRepo {
    private Map<String, List<RacetrackBooking>> bookingsPerTrack;
    private Map<String, Integer> totalRevenuePerTrack;
    private Map<String, RacetrackBooking> bookingPerVehicle;
    public RacetrackBookingRepo(){
        this.bookingsPerTrack = new HashMap<>();
        this.totalRevenuePerTrack = new HashMap<>();
        this.bookingPerVehicle = new HashMap<>();
    }
    public List<RacetrackBooking> getAllBookings(String track){
        return this.bookingsPerTrack.containsKey(track) ? this.bookingsPerTrack.get(track):new ArrayList<>();
    }
    public boolean addBooking(String track, RacetrackBooking racetrackBooking){
        if(!bookingsPerTrack.containsKey(track)){
            List<RacetrackBooking> temp = new ArrayList<>();
            temp.add(racetrackBooking);
            bookingsPerTrack.put(track,temp);
            return true;
        }
        List<RacetrackBooking> bookings = bookingsPerTrack.get(track);
        bookings.add(racetrackBooking);
        bookingsPerTrack.put(track,bookings);
        return true;
    }
    public boolean addRevenue(String track, Integer amount){
        this.totalRevenuePerTrack.put(track,this.totalRevenuePerTrack.getOrDefault(track,0)+amount);
        return true;
    }
    public Integer getRevenuePerTrack(String track){
        return this.totalRevenuePerTrack.getOrDefault(track,0);
    }
    public void addBookingOfVehicle(RacetrackBooking racetrackBooking){
        this.bookingPerVehicle.put(racetrackBooking.getVehicleNumber(),racetrackBooking);
    }
    public RacetrackBooking getRacetrackBookingOfVehicle(String vehicleNumber){
        return this.bookingPerVehicle.getOrDefault(vehicleNumber,null);
    }
}
