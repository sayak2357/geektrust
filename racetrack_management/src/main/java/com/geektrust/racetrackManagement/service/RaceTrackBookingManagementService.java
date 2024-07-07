package com.geektrust.racetrackManagement.service;

import com.geektrust.racetrackManagement.entity.RacetrackBooking;
import com.geektrust.racetrackManagement.repository.RacetrackBookingRepo;

import java.time.LocalTime;
import java.util.List;

import static com.geektrust.racetrackManagement.constants.Constants.*;

public class RaceTrackBookingManagementService {
    private RacetrackBookingRepo racetrackBookingRepo;
    private HelperService helperService;
    public RaceTrackBookingManagementService(){
        this.racetrackBookingRepo = new RacetrackBookingRepo();
        this.helperService = new HelperService();
    }
    public boolean book(String vehicleType,String vehicleNumber,String startTime){
        String startTimeHour = startTime.split(":")[0];
        Integer stHour = Integer.parseInt(startTimeHour);
        Integer etHour = stHour+DEFAULT_BOOKING_INTERVAL;
        String endTime = String.valueOf(etHour)+":"+startTime.split(":")[1];
        RacetrackBooking racetrackBooking = new RacetrackBooking(startTime,endTime,vehicleNumber,vehicleType);
        return newBookingHelper(racetrackBooking);
    }
    private boolean newBookingHelper(RacetrackBooking racetrackBooking){
        String track = "REGULAR";
        List<RacetrackBooking> racetrackBookingList = racetrackBookingRepo.getAllBookings(track);
        String vehicleType = racetrackBooking.getVehicleType();
        int vehicleBookingCost = helperService.getVehicleBookingCost(track,racetrackBooking.getVehicleType())*DEFAULT_BOOKING_INTERVAL;
        if(racetrackBookingList.size()==0){
            racetrackBooking.setTrack(track);
            racetrackBookingRepo.addBooking(track,racetrackBooking);
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }
        int regularConflicts = 0;
        int numberOfVehiclesAllowed = helperService.getAllowedVehicleCount(track,vehicleType);

        for(RacetrackBooking racetrackBooking1:racetrackBookingList){
            if(helperService.isOverlap(racetrackBooking1,racetrackBooking))
                regularConflicts++;
        }
        if(regularConflicts<numberOfVehiclesAllowed && numberOfVehiclesAllowed>0){
            racetrackBooking.setTrack(track);
            racetrackBookingRepo.addBooking(track,racetrackBooking);
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }

        track = "VIP";
        racetrackBookingList = racetrackBookingRepo.getAllBookings(track);
        int vipConflicts = 0;
        numberOfVehiclesAllowed = helperService.getAllowedVehicleCount(track,vehicleType);
        vehicleBookingCost = helperService.getVehicleBookingCost(track,racetrackBooking.getVehicleType())*DEFAULT_BOOKING_INTERVAL;
        for(RacetrackBooking racetrackBooking1:racetrackBookingList){
            if(helperService.isOverlap(racetrackBooking1,racetrackBooking))
                vipConflicts++;
        }
        if(vipConflicts<numberOfVehiclesAllowed && numberOfVehiclesAllowed>0){
            racetrackBooking.setTrack(track);
            racetrackBookingRepo.addBooking(track,racetrackBooking);
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }
        return false;
    }
    public boolean extendBooking(String vehicleNumber, String newEndTime){
        RacetrackBooking racetrackBooking = racetrackBookingRepo.getRacetrackBookingOfVehicle(vehicleNumber);
        String track = racetrackBooking.getTrack();
        String oldEndTime = racetrackBooking.getEndTime();
        Integer cost = EXTENDED_BOOKING_HOURLY_COST;
        List<RacetrackBooking> racetrackBookingList = racetrackBookingRepo.getAllBookings(track);
        String vehicleType = racetrackBooking.getVehicleType();
        int numberOfVehiclesAllowed = helperService.getAllowedVehicleCount(track,vehicleType);
        int conflicts = 0;
        racetrackBooking.setEndTime(newEndTime);
        for(RacetrackBooking racetrackBooking1:racetrackBookingList){
            if(helperService.isOverlap(racetrackBooking1,racetrackBooking))
                conflicts++;
        }
        if(conflicts<numberOfVehiclesAllowed){

            int minuteDiff = Math.toIntExact(helperService.minutesdifference(oldEndTime,newEndTime));
            int vehicleBookingCost = helperService.extensionCostCalculator(oldEndTime,newEndTime,racetrackBooking.isExtended());
            racetrackBooking.setIsExtended();
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }
        racetrackBooking.setEndTime(oldEndTime);
        return false;
    }
    public boolean extendBooking2(String vehicleNumber, String newEndTime){
        return  false;
    }
    public void getrevenues(){
        Integer regularRevenue = racetrackBookingRepo.getRevenuePerTrack("REGULAR");
        Integer vipRevenue = racetrackBookingRepo.getRevenuePerTrack("VIP");
        System.out.println(regularRevenue+" "+vipRevenue);
    }
}
