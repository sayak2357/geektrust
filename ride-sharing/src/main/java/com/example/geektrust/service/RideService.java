package com.example.geektrust.service;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Ride;
import com.example.geektrust.model.Rider;
import com.example.geektrust.repository.DriverRepo;
import com.example.geektrust.repository.RideRepo;
import com.example.geektrust.repository.RiderRepo;

import java.text.DecimalFormat;

public class RideService {
    private RideRepo rideRepo;
    private DistanceFinderService distanceFinderService;
    private BillService billService;
    private RideRepo ri;
    private DriverRepo driverRepo;
    private RiderRepo riderRepo;
    private CommonService commonService;
    public RideService(RiderRepo riderRepo, DriverRepo driverRepo){
        this.rideRepo = new RideRepo();
        this.distanceFinderService = new DistanceFinderService();
        this.billService = new BillService();
        this.riderRepo = riderRepo;
        this.driverRepo = driverRepo;
        this.commonService = new CommonService();

    }
    public boolean startRide(String rideId, Driver driver, Rider rider){
        Ride existingRide = rideRepo.getRideById(rideId);
        if(existingRide!=null){
            System.out.println("INVALID_RIDE");
            return false;
        }
        driver.setOnRide(true);
        rider.setOnRide(true);
        Ride ride = new Ride(rideId,rider.getId());
        ride.setDriverId(driver.getId());
        rideRepo.addRide(rideId,ride);
        System.out.println("RIDE_STARTED "+rideId);
        return true;
    }
    public boolean stopRide(String rideId, Double destX, Double destY, Double time){
        Ride ride = rideRepo.getRideById(rideId);
        if(ride==null || ride.isFinished()){
            System.out.println("INVALID_RIDE");
            return false;
        }
        ride.setDestX(destX);
        ride.setDestY(destY);
        ride.setTime(time);
        ride.setFinished(true);
        Rider rider = riderRepo.getRider(ride.getRiderId());
        Double netDistance = distanceFinderService.findDistance(rider.getX(),rider.getY(),destX,destY);
        Double bill = billService.generateBill(commonService.round(netDistance,2), ride.getTime());
        ride.setBill(commonService.round(bill,2));
        rider.setOnRide(false);
        Driver driver = driverRepo.getDriver(ride.getDriverId());
        driver.setOnRide(false);
        System.out.println("RIDE_STOPPED "+rideId);
        return true;
    }
    public boolean generateBill(String rideId){
        Ride ride = rideRepo.getRideById(rideId);
        if(ride==null) {
            System.out.println("INVALID_RIDE");
            return false;
        }
        double netBill = commonService.round(ride.getBill(),2);
        DecimalFormat dec = new DecimalFormat("#0.00");
        System.out.println("BILL "+rideId+" "+ride.getDriverId()+" "+dec.format(netBill));
        return true;
    }
}
