package com.geektrust.racetrackManagement.service;

import static com.geektrust.racetrackManagement.constants.Constants.*;

public class RaceTrackConstraintChecker {
    public Integer getAllowedVehicleCount(String track, String vehicleType){
        Integer value = 0;
        if(track.equals("REGULAR")){
            switch (vehicleType){
                case "BIKE": value = REGULAR_BIKES_ALLOWED;break;
                case "CAR": value =  REGULAR_CARS_ALLOWED;break;
                case "SUV": value =  REGULAR_SUVS_ALLOWED;break;
            }
        }
        else if(track.equals("VIP")){
            switch (vehicleType){

                case "CAR": value =  VIP_CARS_ALLOWED;break;
                case "SUV": value =  VIP_SUVS_ALLOWED;break;
            }
        }
        return value;
    }
    public Integer getVehicleBookingCost(String track, String vehicleType){
        Integer value = 0;
        if(track.equals("REGULAR")){
            switch (vehicleType){
                case "BIKE": value = REGULAR_BIKE_HOURLY_COST;break;
                case "CAR": value =  REGULAR_CAR_HOURLY_COST;break;
                case "SUV": value =  REGULAR_SUV_HOURLY_COST;break;
            }
        }
        else if(track.equals("VIP")){
            switch (vehicleType){

                case "CAR": value =  VIP_CAR_HOURLY_COST;break;
                case "SUV": value =  VIP_SUV_HOURLY_COST;break;
            }
        }
        return value;
    }
}
