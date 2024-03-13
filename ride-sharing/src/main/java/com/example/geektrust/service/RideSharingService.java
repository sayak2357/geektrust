package com.example.geektrust.service;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.DriverDistancePair;
import com.example.geektrust.model.Ride;
import com.example.geektrust.model.Rider;
import com.example.geektrust.repository.DriverRepo;
import com.example.geektrust.repository.MatchRepo;
import com.example.geektrust.repository.RideRepo;
import com.example.geektrust.repository.RiderRepo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.example.geektrust.constants.Constants.DISTANCE_LIMIT;
import static com.example.geektrust.constants.Constants.NEAREST_DRIVER_DISPLAY_LIMIT;

public class RideSharingService {

    private DriverRepo driverRepo;
    private RiderRepo riderRepo;
    private MatchRepo matchRepo;
    private RideRepo rideRepo;
    public RideSharingService(){
        this.driverRepo = new DriverRepo();
        this.riderRepo = new RiderRepo();
        this.matchRepo = new MatchRepo();
        this.rideRepo = new RideRepo();
    }
    public void run(Scanner sc){
        while (sc.hasNextLine()) {
            //System.out.println(sc.nextLine());
            String[] stream = sc.nextLine().split(" ");
            String command = stream[0];
            if(command.equals("ADD_DRIVER")){
                String id = stream[1];
                Integer x = Integer.parseInt(stream[2]);
                Integer y = Integer.parseInt(stream[3]);
                driverRepo.addDriver(id,x,y);
            }
            else if(command.equals("ADD_RIDER")){
                String id = stream[1];
                Integer x = Integer.parseInt(stream[2]);
                Integer y = Integer.parseInt(stream[3]);
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
                Ride ride = new Ride(rideId,riderId);
                rideRepo.addRide(rideId,ride);
                Driver driver = nearestDrivers.get(matchIndex-1).getDriver();
                driver.setOnRide(true);
                System.out.println("RIDE_STARTED "+rideId);
            }
        }
    }
    private void showNearestDriver(String riderId){
        List<Driver> drivers = driverRepo.getDrivers();
        Rider rider = riderRepo.getRider(riderId);
        Integer x = rider.getX();
        Integer y = rider.getY();
        List<DriverDistancePair> nearestDrivers = new ArrayList<>();
        for(Driver driver:drivers){
            if(!driver.isOnRide()) {
                Integer driverX = driver.getX();
                Integer driverY = driver.getY();
                Double distance = findDistance(x, y, driverX, driverY);
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
    private Double findDistance(Integer x1, Integer y1, Integer x2, Integer y2){
        Double temp = (double) ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        return Math.sqrt(temp);
    }
}
