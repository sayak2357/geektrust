package com.example.geektrust.service;

import com.example.geektrust.dao.LumpsumPaymentRepo;
import com.example.geektrust.entity.Lumpsum;

public class LumpsumPaymentService {
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    public LumpsumPaymentService(LumpsumPaymentRepo lumpsumPaymentRepo){
        this.lumpsumPaymentRepo = lumpsumPaymentRepo;
    }
    public boolean addLumpsum(String user, String bank,Double amount, Integer emiNumber){
        Lumpsum lumpsum = new Lumpsum(amount,emiNumber);
        return this.lumpsumPaymentRepo.addLumpsum(lumpsum,user,bank);
    }
}
