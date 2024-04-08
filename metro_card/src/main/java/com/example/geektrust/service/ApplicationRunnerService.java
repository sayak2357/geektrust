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
    public ApplicationRunnerService(String inputFile){

        this.inputFile = inputFile;
        locationDataManager = new LocationDataManager();
        metroCardManager = new MetroCardManager();
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
                    trainService(cardId,passengerType,station);
                }
                else if(op.equals("PRINT_SUMMARY")){
                    summary();
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
    }
    private void trainService(String cardId, String passengerType, String station){
        Double balance = metroCardManager.getBalance(cardId);
        if(balance==-1d){
            System.out.println("Invalid metro card id");
            return;
        }
        Integer travelCount = metroCardManager.getTravelCount(cardId);
        if(travelCount==-1){
            System.out.println("Invalid metro card id");
            return;
        }
        travelCount+=1;
        metroCardManager.incrementTravelCount(cardId);
        boolean isDiscount = travelCount%2==0;
        Integer basePrice = 0;
        if(isDiscount){
            basePrice = Constants.getDiscountedPrice(passengerType);
            Integer discountGiven = Constants.getPrice(passengerType)-basePrice;
            locationDataManager.addToTotalDiscountsGiven(station,discountGiven);
        }
        else
            basePrice = Constants.getPrice(passengerType);
        Double collection = 0d;
        if(balance>=basePrice){
            balance-=basePrice;
            metroCardManager.updateBalance(cardId,balance);
            collection = (double) basePrice;
        }
        else{
            Double diff = basePrice-balance;
            balance = 0d;
            metroCardManager.updateBalance(cardId,balance);
            collection = basePrice+diff*0.02;
        }
        locationDataManager.addToCollection(station,collection);
        locationDataManager.addPassengerType(station,passengerType);
    }
    private void summary(){
        if(locationDataManager.getTotalCollection("CENTRAL")>0d){
            String place = "CENTRAL";
            System.out.println("TOTAL_COLLECTION "+place+" "+locationDataManager.getTotalCollection(place).intValue()+" "+locationDataManager.getTotalDiscountGiven(place).intValue());
            summarizePassengers(place);
        }
        if(locationDataManager.getTotalCollection("AIRPORT")>0d){
            String place = "AIRPORT";
            System.out.println("TOTAL_COLLECTION "+place+" "+locationDataManager.getTotalCollection(place).intValue()+" "+locationDataManager.getTotalDiscountGiven(place).intValue());
            summarizePassengers(place);
        }
    }
    private void summarizePassengers(String station){
        System.out.println("PASSENGER_TYPE_SUMMARY");
        List<String> passengerTypes = locationDataManager.getListOfPassengerTypes(station);
        Map<String, Integer> count = new HashMap<>();
        for(String type:passengerTypes){
            count.put(type,count.getOrDefault(type,0)+1);
        }
        List<Pair> classCountList = new ArrayList<>();
        for(String type: count.keySet()){
            int val = count.get(type);
            Pair temp = new Pair(type,val);
            classCountList.add(temp);
        }
        Collections.sort(classCountList,(a,b) -> a.getValue()==b.getValue() ? a.getKey().compareTo(b.getKey()):b.getValue()-a.getValue());
        for(Pair p:classCountList){
            System.out.println(p.getKey()+" "+p.getValue());
        }
    }
}
