package com.example.geektrust.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BillService {
    public Double generateBill(Double distance, Double time){
        Double total = 0d;
        total += 50;
        total += distance*6.5;
        total += time*2;
        total *= 1.2;
        return total;
    }
}
