package com.example.geektrust.service;

import com.example.geektrust.model.Distance;
import com.example.geektrust.model.EuclideanDistance;

public class DistanceFinderService {
    private Distance distanceFinder;
    public DistanceFinderService(){
        this.distanceFinder = new EuclideanDistance();
    }
    public Double findDistance(Double x1, Double y1, Double x2, Double y2){
        return distanceFinder.findDistance(x1, y1, x2, y2);
    }
}
