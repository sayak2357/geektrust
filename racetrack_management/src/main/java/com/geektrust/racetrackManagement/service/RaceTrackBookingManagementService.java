package com.geektrust.racetrackManagement.service;

import com.geektrust.racetrackManagement.entity.Pair;
import com.geektrust.racetrackManagement.entity.RacetrackBooking;
import com.geektrust.racetrackManagement.repository.RacetrackBookingRepo;

import java.util.List;

import static com.geektrust.racetrackManagement.constants.Constants.*;

public class RaceTrackBookingManagementService {
    private RacetrackBookingRepo racetrackBookingRepo;
    private HelperService helperService;
    private RaceTrackConstraintChecker raceTrackConstraintChecker;
    public RaceTrackBookingManagementService(){
        this.racetrackBookingRepo = new RacetrackBookingRepo();
        this.helperService = new HelperService();
        this.raceTrackConstraintChecker = new RaceTrackConstraintChecker();
    }
    public boolean book(String vehicleType,String vehicleNumber,String startTime){
        String startTimeHour = startTime.split(":")[0];
        Integer stHour = Integer.parseInt(startTimeHour);
        Integer etHour = stHour+DEFAULT_BOOKING_INTERVAL;
        String endTime = String.valueOf(etHour)+":"+startTime.split(":")[1];
        RacetrackBooking racetrackBooking = new RacetrackBooking(startTime,endTime,vehicleNumber,vehicleType);
        return newBookingHelper(racetrackBooking, false,0);
    }
    private boolean newBookingHelper(RacetrackBooking racetrackBooking, boolean extension, Integer cost){
        String track = "REGULAR";
        List<RacetrackBooking> racetrackBookingList = racetrackBookingRepo.getAllBookings(track);
        String vehicleType = racetrackBooking.getVehicleType();
        int vehicleBookingCost = extension ? cost: raceTrackConstraintChecker.getVehicleBookingCost(track,racetrackBooking.getVehicleType())*DEFAULT_BOOKING_INTERVAL;
        if(racetrackBookingList.size()==0){
            racetrackBooking.setTrack(track);
            racetrackBookingRepo.addBooking(track,racetrackBooking);
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }
        int regularConflicts = findConflicts(racetrackBooking,racetrackBookingList);

        int numberOfVehiclesAllowed = raceTrackConstraintChecker.getAllowedVehicleCount(track,vehicleType);

        if(regularConflicts<numberOfVehiclesAllowed && numberOfVehiclesAllowed>0){
            racetrackBooking.setTrack(track);
            racetrackBookingRepo.addBooking(track,racetrackBooking);
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }

        track = "VIP";
        racetrackBookingList = racetrackBookingRepo.getAllBookings(track);
        int vipConflicts = findConflicts(racetrackBooking,racetrackBookingList);
        numberOfVehiclesAllowed = raceTrackConstraintChecker.getAllowedVehicleCount(track,vehicleType);
        vehicleBookingCost = extension ? cost: raceTrackConstraintChecker.getVehicleBookingCost(track,racetrackBooking.getVehicleType())*DEFAULT_BOOKING_INTERVAL;

        if(vipConflicts<numberOfVehiclesAllowed && numberOfVehiclesAllowed>0){
            racetrackBooking.setTrack(track);
            racetrackBookingRepo.addBooking(track,racetrackBooking);
            racetrackBookingRepo.addRevenue(track,vehicleBookingCost);
            racetrackBookingRepo.addBookingOfVehicle(racetrackBooking);
            return true;
        }
        return false;
    }
    private Integer findConflicts(RacetrackBooking racetrackBooking, List<RacetrackBooking> racetrackBookingList){
        int conflicts = 0;
        for(RacetrackBooking racetrackBooking1:racetrackBookingList){
            if(helperService.isOverlap(racetrackBooking1,racetrackBooking))
                conflicts++;
        }
        return conflicts;
    }
    public boolean extendBooking(String vehicleNumber, String newEndTime){
        RacetrackBooking racetrackBooking = racetrackBookingRepo.getRacetrackBookingOfVehicle(vehicleNumber);
        String track = racetrackBooking.getTrack();
        String oldEndTime = racetrackBooking.getEndTime();
        Integer cost = EXTENDED_BOOKING_HOURLY_COST;
        List<RacetrackBooking> racetrackBookingList = racetrackBookingRepo.getAllBookings(track);
        String vehicleType = racetrackBooking.getVehicleType();
        int numberOfVehiclesAllowed = raceTrackConstraintChecker.getAllowedVehicleCount(track,vehicleType);
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
    public boolean extendBookingImproved(String vehicleNumber, String newEndTime){
        RacetrackBooking racetrackBooking = racetrackBookingRepo.getRacetrackBookingOfVehicle(vehicleNumber);
        RacetrackBooking extended = racetrackBooking.clone();
        extended.setStartTime(racetrackBooking.getEndTime());
        extended.setEndTime(newEndTime);
        extended.isExtended();
        int vehicleBookingCost = helperService.extensionCostCalculator(racetrackBooking.getEndTime(), newEndTime,racetrackBooking.isExtended());
        return  newBookingHelper(extended,true,vehicleBookingCost);
    }
    public Pair getrevenues(){
        Integer regularRevenue = racetrackBookingRepo.getRevenuePerTrack("REGULAR");
        Integer vipRevenue = racetrackBookingRepo.getRevenuePerTrack("VIP");
        Pair res = new Pair();
        res.setRegularRevenue(regularRevenue);
        res.setVipRevenue(vipRevenue);
        return res;
    }
}
