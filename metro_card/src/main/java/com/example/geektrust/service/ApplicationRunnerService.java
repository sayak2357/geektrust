package com.example.geektrust.service;

import com.example.geektrust.helper.Constants;
import com.example.geektrust.helper.Pair;
import com.example.geektrust.repository.LocationDataManager;
import com.example.geektrust.repository.MetroCardManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ApplicationRunnerService {
    private String inputFile;
    private LocationDataManager locationDataManager;
    private MetroCardManager metroCardManager;
    private TrainService trainService;
    private SummaryService summaryService;
    public ApplicationRunnerService(String inputFile){

        this.inputFile = inputFile;
        locationDataManager = new LocationDataManager();
        metroCardManager = new MetroCardManager();
        trainService = new TrainService(locationDataManager,metroCardManager);
        summaryService = new SummaryService(locationDataManager,metroCardManager);
    }
    public void run(){
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(inputFile);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                //System.out.println(line);
                String[] command = line.split(" ");
                String op = command[0];
                if(op.equals("BALANCE")){
                    String cardId = command[1];
                    Double amount = Double.parseDouble(command[2]);
                    metroCardManager.registerCard(cardId,amount);
                }
                else if(op.equals("CHECK_IN")){
                    String cardId = command[1];
                    String passengerType = command[2];
                    String station = command[3];
                    trainService.trainServiceBooking(cardId,passengerType,station);
                }
                else if(op.equals("PRINT_SUMMARY")){
                    summaryService.summary();
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
    }
}
