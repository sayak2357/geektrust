package com.example.geektrust.dto;

public class Pair {
    private String uname;
    private Double amount;

    public Pair(String uname, Double amount) {
        this.uname = uname;
        this.amount = amount;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
