package com.example.geektrust.service;

public class DistanceFinderService {
    public Double findDistance(Double x1, Double y1, Double x2, Double y2){
        Double temp =  ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        return Math.sqrt(temp);
    }
}
