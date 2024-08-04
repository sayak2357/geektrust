package com.example.geektrust.entity;

public class Lumpsum {
    private Integer amount;
    private Integer emiNumber;

    public Lumpsum(Integer amount, Integer emiNumber) {
        this.amount = amount;
        this.emiNumber = emiNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getEmiNumber() {
        return emiNumber;
    }

    public void setEmiNumber(Integer emiNumber) {
        this.emiNumber = emiNumber;
    }
}
