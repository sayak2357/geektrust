package com.example.geektrust.service;

public class HelperService {
    public Double getNetAmount(Double principal, Integer tenure, Double interest){
        return principal+(principal*tenure*interest/100);
    }
    public Integer getMonthlyEmi(Double principal, Integer tenure, Double interest){
        return (int) Math.ceil( this.getNetAmount(principal,tenure,interest) /(tenure*12));
    }
}
