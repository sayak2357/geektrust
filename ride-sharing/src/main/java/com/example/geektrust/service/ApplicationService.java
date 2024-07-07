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
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;

import static com.example.geektrust.constants.Constants.NEAREST_DRIVER_DISPLAY_LIMIT;

public class ApplicationService {

    private DriverRepo driverRepo;
    private RiderRepo riderRepo;
    private MatchRepo matchRepo;
    private RideRepo rideRepo;
    private BillService billService;
    private DistanceFinderService distanceFinderService;
    private MatchService matchService;
    private RideService rideService;
    private CommonService commonService;
    public ApplicationService(){
        this.driverRepo = new DriverRepo();
        this.riderRepo = new RiderRepo();
        this.matchRepo = new MatchRepo();
        this.rideRepo = new RideRepo();
        this.billService = new BillService();
        this.distanceFinderService = new DistanceFinderService();
        this.rideService = new RideService(riderRepo,driverRepo);
        this.matchService = new MatchService(this.driverRepo,this.riderRepo,this.distanceFinderService,this.matchRepo);
        this.commonService = new CommonService();
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
                List<DriverDistancePair> nearbyDrivers = matchService.showNearestDrivers(riderId);
                int n = Math.min(NEAREST_DRIVER_DISPLAY_LIMIT, nearbyDrivers.size());
                if (n == 0) {
                    System.out.println("NO_DRIVERS_AVAILABLE");
                } else {
                    System.out.printf("DRIVERS_MATCHED ");
                    for (int i = 0; i < n; i++) {
                        System.out.printf(nearbyDrivers.get(i).getDriver().getId() + " ");
                    }
                    System.out.println();
                }
            }
            else if(command.equals("START_RIDE")){
                String rideId = stream[1];
                Integer matchIndex = Integer.parseInt(stream[2]);
                String riderId = stream[3];
                Rider rider = riderRepo.getRider(riderId);
                List<DriverDistancePair> nearestDrivers = matchRepo.getNearestDrivers(riderId);
                if(rider==null || rider.isOnRide() || nearestDrivers==null || nearestDrivers.size()<matchIndex){
                    System.out.println("INVALID_RIDE");
                    continue;
                }

                Driver driver = nearestDrivers.get(matchIndex-1).getDriver();
                rideService.startRide(rideId,driver,rider);
            }
            else if(command.equals("STOP_RIDE")){
                String rideId = stream[1];
                Double destX = Double.parseDouble(stream[2]);
                Double destY = Double.parseDouble(stream[3]);
                Double time = Double.parseDouble(stream[4]);
                rideService.stopRide(rideId,destX,destY,time);

            }
            else if(command.equals("BILL")){
                String rideId = stream[1];
                rideService.generateBill(rideId);
            }
        }
    }

}
