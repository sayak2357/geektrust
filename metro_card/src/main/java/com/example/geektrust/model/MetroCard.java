package com.example.geektrust.model;

public class MetroCard {
    private String id;
    private Double balance;
    private Integer travelCount;

    public MetroCard(String id, Double balance){
        this.id = id;
        this.balance = balance;
        this.travelCount = 0;
    }

    public Integer getTravelCount() {
        return travelCount;
    }

    public void setTravelCount(Integer travelCount) {
        this.travelCount = travelCount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
