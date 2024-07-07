package com.geektrust.racetrackManagement.service;

import java.util.Scanner;

public class ApplicationService {
    private HelperService helperService;
    private RaceTrackBookingManagementService raceTrackBookingManagementService;
    public ApplicationService(){
        this.helperService = new HelperService();
        this.raceTrackBookingManagementService = new RaceTrackBookingManagementService();
    }

    public void run(Scanner sc){
        while(sc.hasNextLine()) {
            String ip = sc.nextLine();
            //System.out.println(ip);
            String[] tokens = ip.split(" ");
            String command = tokens[0];
            if(command.equals("BOOK")){
                String vehicleType = tokens[1];
                String vehicleNumber = tokens[2];
                String startTime = tokens[3];
                if(!helperService.startTimeChecker(startTime)) {
                    System.out.println("INVALID_ENTRY_TIME");
                    continue;
                }
                if(raceTrackBookingManagementService.book(vehicleType,vehicleNumber,startTime))
                    System.out.println("SUCCESS");
                else
                    System.out.println("RACETRACK_FULL");
            }
            else if(command.equals("ADDITIONAL")){
                String vehicleNumber = tokens[1];
                String newEndTime = tokens[2];
                if(!helperService.endTimeChecker(newEndTime)) {
                    System.out.println("INVALID_EXIT_TIME");
                    continue;
                }
                if(raceTrackBookingManagementService.extendBooking2(vehicleNumber,newEndTime))
                    System.out.println("SUCCESS");
                else
                    System.out.println("RACETRACK_FULL");

            }
            else if(command.equals("REVENUE")){
                raceTrackBookingManagementService.getrevenues();
            }
        }
    }
}
