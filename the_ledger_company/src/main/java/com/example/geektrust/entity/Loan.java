package com.example.geektrust.entity;

import java.util.ArrayList;
import java.util.List;

public class Loan {
    private String user;
    private String bank;
    private Double principal;
    private Integer number_of_years_tenure;
    private Double interest;
    private Integer monthlyEmi;
    private Double netAmount;
    private List<Lumpsum> lumpsumPayments;
    public Loan(String user, String bank, Double principal, Integer tenure, Double interest){
        this.user = user;
        this.bank  = bank;
        this.number_of_years_tenure = tenure;
        this.principal = principal;
        this.interest = interest;
        this.lumpsumPayments = new ArrayList<>();
        this.netAmount = principal+(principal*tenure*interest/100);
        this.monthlyEmi = (int) Math.ceil( this.netAmount /(tenure*12));
    }
    public Double getPrincipal() {
        return principal;
    }

    public Integer getMonthlyEmi() {
        return monthlyEmi;
    }

    public List<Lumpsum> getLumpsumPayments() {
        return lumpsumPayments;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public String getUser() {
        return user;
    }

    public Double getInterest() {
        return interest;
    }

    public Integer getNumber_of_years_tenure() {
        return number_of_years_tenure;
    }

    public String getBank() {
        return bank;
    }

    public void addLumpsumPayment(Lumpsum lumpsum){
        this.lumpsumPayments.add(lumpsum);
    }
}
