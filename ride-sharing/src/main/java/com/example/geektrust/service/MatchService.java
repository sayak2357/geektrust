package com.example.geektrust.service;

import com.example.geektrust.model.Driver;
import com.example.geektrust.model.DriverDistancePair;
import com.example.geektrust.model.Rider;
import com.example.geektrust.repository.DriverRepo;
import com.example.geektrust.repository.MatchRepo;
import com.example.geektrust.repository.RiderRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.geektrust.constants.Constants.DISTANCE_LIMIT;

public class MatchService {
    private DriverRepo driverRepo;
    private RiderRepo riderRepo;
    private DistanceFinderService distanceFinderService;
    private MatchRepo matchRepo;
    public MatchService(DriverRepo driverRepo, RiderRepo riderRepo, DistanceFinderService distanceFinderService, MatchRepo matchRepo){
        this.driverRepo = driverRepo;
        this.riderRepo = riderRepo;
        this.distanceFinderService = distanceFinderService;
        this.matchRepo = matchRepo;
    }

    public List<DriverDistancePair> showNearestDrivers(String riderId) {
        List<Driver> drivers = driverRepo.getDrivers();
        Rider rider = riderRepo.getRider(riderId);
        Double x = rider.getX();
        Double y = rider.getY();
        List<DriverDistancePair> nearestDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (!driver.isOnRide()) {
                Double driverX = driver.getX();
                Double driverY = driver.getY();
                Double distance = distanceFinderService.findDistance(x, y, driverX, driverY);
                if (distance <= DISTANCE_LIMIT) {
                    nearestDrivers.add(new DriverDistancePair(driver, distance));
                }
            }
        }
        Collections.sort(nearestDrivers, (a, b) -> a.getDistance().equals(b.getDistance()) ? a.getDriver().getId().compareTo(b.getDriver().getId()) : a.getDistance().compareTo(b.getDistance()));
        matchRepo.putNearestDrivers(riderId, nearestDrivers);
        return nearestDrivers;
    }
}
