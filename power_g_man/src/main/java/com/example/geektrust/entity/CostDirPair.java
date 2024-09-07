package com.example.geektrust.entity;

import com.example.geektrust.constants.Constants;

public class CostDirPair {
    private Integer cost;
    private Constants.directions dir;

    public CostDirPair(Integer cost, Constants.directions dir) {
        this.cost = cost;
        this.dir = dir;
    }

    public Integer getCost() {
        return cost;
    }

    public Constants.directions getDir() {
        return dir;
    }
}
