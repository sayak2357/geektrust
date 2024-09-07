package com.example.geektrust.entity;

public class CostDirPair {
    private Integer cost;
    private String dir;

    public CostDirPair(Integer cost, String dir) {
        this.cost = cost;
        this.dir = dir;
    }

    public Integer getCost() {
        return cost;
    }

    public String getDir() {
        return dir;
    }
}
