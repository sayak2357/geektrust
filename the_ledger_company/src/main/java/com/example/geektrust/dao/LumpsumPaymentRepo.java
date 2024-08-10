package com.example.geektrust.dao;

import com.example.geektrust.entity.Lumpsum;

import java.util.*;

import static com.example.geektrust.constant.Constants.DELIMETER;

public class LumpsumPaymentRepo {
    private Map<String, List<Lumpsum>> lumpsumPayments;
    public LumpsumPaymentRepo(){
        this.lumpsumPayments = new HashMap<>();
    }

    public boolean addLumpsum(Lumpsum lumpsum,String user, String bank){
        String userBank = user + DELIMETER +bank;
        if(lumpsumPayments.containsKey(userBank))
            lumpsumPayments.get(userBank).add(lumpsum);
        else
            lumpsumPayments.put(userBank, new ArrayList<>(Arrays.asList(lumpsum)));
        return true;
    }
    public List<Lumpsum> getLumpsumPayments(String user, String bank){
        String userBank = user+DELIMETER+bank;
        return lumpsumPayments.containsKey(userBank)? lumpsumPayments.get(userBank):new ArrayList<>();
    }
}
