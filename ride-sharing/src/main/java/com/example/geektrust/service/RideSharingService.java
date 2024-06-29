package com.example.geektrust.service;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.DriverDistancePair;
import com.example.geektrust.model.Ride;
import com.example.geektrust.model.Rider;
import com.example.geektrust.repository.DriverRepo;
import com.example.geektrust.repository.MatchRepo;
import com.example.geektrust.repository.RideRepo;
import com.example.geektrust.repository.RiderRepo;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

import static com.example.geektrust.constants.Constants.DISTANCE_LIMIT;
import static com.example.geektrust.constants.Constants.NEAREST_DRIVER_DISPLAY_LIMIT;

public class RideSharingService {

    private DriverRepo driverRepo;
    private RiderRepo riderRepo;
    private MatchRepo matchRepo;
    private RideRepo rideRepo;
    private BillService billService;
    private DistanceFinderService distanceFinderService;
    public RideSharingService(){
        this.driverRepo = new DriverRepo();
        this.riderRepo = new RiderRepo();
        this.matchRepo = new MatchRepo();
        this.rideRepo = new RideRepo();
        this.billService = new BillService();
        this.distanceFinderService = new DistanceFinderService();
    }
    public void run(Scanner sc){
        while (sc.hasNextLine()) {
            //System.out.println(sc.nextLine());
            String[] stream = sc.nextLine().split(" ");
            String command = stream[0];
            if(command.equals("ADD_DRIVER")){
                String id = stream[1];
                Double x = Double.parseDouble(stream[2]);
                Double y = Double.parseDouble(stream[3]);
                driverRepo.addDriver(id,x,y);
            }
            else if(command.equals("ADD_RIDER")){
                String id = stream[1];
                Double x = Double.parseDouble(stream[2]);
                Double y = Double.parseDouble(stream[3]);
                riderRepo.addRider(id,x,y);
            }
            else if(command.equals("MATCH")){
                String riderId = stream[1];
                showNearestDriver(riderId);
            }
            else if(command.equals("START_RIDE")){
                String rideId = stream[1];
                Integer matchIndex = Integer.parseInt(stream[2]);
                String riderId = stream[3];
                Rider rider = riderRepo.getRider(riderId);
                Ride existingRide = rideRepo.getRideById(rideId);
                List<DriverDistancePair> nearestDrivers = matchRepo.getNearestDrivers(riderId);
                if(rider==null || rider.isOnRide() || existingRide!=null || nearestDrivers==null || nearestDrivers.size()<matchIndex){
                    System.out.println("INVALID_RIDE");
                    return;
                }
                Driver driver = nearestDrivers.get(matchIndex-1).getDriver();
                driver.setOnRide(true);
                rider.setOnRide(true);
                Ride ride = new Ride(rideId,riderId);
                ride.setDriverId(driver.getId());
                rideRepo.addRide(rideId,ride);
                System.out.println("RIDE_STARTED "+rideId);
            }
            else if(command.equals("STOP_RIDE")){
                String rideId = stream[1];
                Double destX = Double.parseDouble(stream[2]);
                Double destY = Double.parseDouble(stream[3]);
                Double time = Double.parseDouble(stream[4]);
                Ride ride = rideRepo.getRideById(rideId);
                if(ride==null || ride.isFinished()){
                    System.out.println("INVALID_RIDE");
                    return;
                }
                ride.setDestX(destX);
                ride.setDestY(destY);
                ride.setTime(time);
                ride.setFinished(true);
                Rider rider = riderRepo.getRider(ride.getRiderId());
                Double netDistance = distanceFinderService.findDistance(rider.getX(),rider.getY(),destX,destY);
                Double bill = billService.generateBill(round(netDistance,2), ride.getTime());
                ride.setBill(round(bill,2));
                rider.setOnRide(false);
                Driver driver = driverRepo.getDriver(ride.getDriverId());
                driver.setOnRide(false);
                System.out.println("RIDE_STOPPED "+rideId);
            }
            else if(command.equals("BILL")){
                String rideId = stream[1];
                Ride ride = rideRepo.getRideById(rideId);
                double netBill = round(ride.getBill(),2);
                DecimalFormat dec = new DecimalFormat("#0.00");
                System.out.println("BILL "+rideId+" "+ride.getDriverId()+" "+dec.format(netBill));
            }
        }
    }
    private void showNearestDriver(String riderId){
        List<Driver> drivers = driverRepo.getDrivers();
        Rider rider = riderRepo.getRider(riderId);
        Double x = rider.getX();
        Double y = rider.getY();
        List<DriverDistancePair> nearestDrivers = new ArrayList<>();
        for(Driver driver:drivers){
            if(!driver.isOnRide()) {
                Double driverX = driver.getX();
                Double driverY = driver.getY();
                Double distance = distanceFinderService.findDistance(x, y, driverX, driverY);
                if (distance <= DISTANCE_LIMIT) {
                    nearestDrivers.add(new DriverDistancePair(driver, distance));
                }
            }
        }
        Collections.sort(nearestDrivers, (a,b) -> a.getDistance().equals(b.getDistance())? a.getDriver().getId().compareTo(b.getDriver().getId()): a.getDistance().intValue()-b.getDistance().intValue());
        matchRepo.putNearestDrivers(riderId,nearestDrivers);
        int n = Math.min(NEAREST_DRIVER_DISPLAY_LIMIT,nearestDrivers.size());
        if(n==0){
            System.out.println("NO_DRIVERS_AVAILABLE");
        }
        else{
            System.out.printf("DRIVERS_MATCHED ");
            for(int i=0;i<n;i++){
                System.out.printf(nearestDrivers.get(i).getDriver().getId()+" ");
            }
            System.out.println();
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
