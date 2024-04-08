package com.example.geektrust.service;

import com.example.geektrust.helper.Constants;
import com.example.geektrust.repository.LocationDataManager;
import com.example.geektrust.repository.MetroCardManager;

public class TrainService {
    private LocationDataManager locationDataManager;
    private MetroCardManager metroCardManager;
    public TrainService(LocationDataManager locationDataManager, MetroCardManager metroCardManager){
        this.locationDataManager = locationDataManager;
        this.metroCardManager = metroCardManager;
    }
    public void trainServiceBooking(String cardId, String passengerType, String station){
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
}
