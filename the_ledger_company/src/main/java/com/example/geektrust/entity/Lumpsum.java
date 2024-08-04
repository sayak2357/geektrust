package com.example.geektrust.entity;

public class Lumpsum {
    private Double amount;
    private Integer emiNumber;

    public Lumpsum(Double amount, Integer emiNumber) {
        this.amount = amount;
        this.emiNumber = emiNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getEmiNumber() {
        return emiNumber;
    }

}
