package com.example.geektrust.service;

import com.example.geektrust.dao.LumpsumPaymentRepo;

public class LumpsumPaymentService {
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    public LumpsumPaymentService(LumpsumPaymentRepo lumpsumPaymentRepo){
        this.lumpsumPaymentRepo = lumpsumPaymentRepo;
    }
    public boolean addLumpsum(String user, String bank,Double amount, Integer emiNumber){
        return this.lumpsumPaymentRepo.addLumpsum(user,bank,amount,emiNumber);
    }
}
