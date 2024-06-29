package com.example.geektrust.model;

public class EuclideanDistance implements Distance{

    @Override
    public double findDistance(Double sourceX, Double sourceY, Double destX, Double destY) {
        Double temp =  ((sourceX-destX)*(sourceX-destX)+(sourceY-destY)*(sourceY-destY));
        return Math.sqrt(temp);
    }
}
