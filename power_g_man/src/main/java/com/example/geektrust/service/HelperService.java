package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.entity.CostDirPair;

public class HelperService {
    public Integer findHorizontalTurnCost(Integer horizontalGap, String startingDir) {
        Integer ans = 0;
        if(horizontalGap==0)
            return ans;
        boolean isPositive = horizontalGap>0;
        switch(startingDir){
            case "E": if(!isPositive)
                ans = 10;
                break;
            case "W": if(isPositive)
                ans = 10;
                break;
            case "N": ans = 5;break;

            case "S": ans = 5;break;
        }
        return ans;
    }
    public Integer findVerticalTurnCost(Integer verticalGap, String startingDir) {
        Integer ans = 0;
        if(verticalGap==0)
            return ans;
        boolean isPositive = verticalGap>0;
        switch(startingDir){
            case "N": if(!isPositive)
                ans = 10;
                break;
            case "S": if(isPositive)
                ans = 10;
                break;
            case "E": ans = 5;break;

            case "W": ans = 5;break;
        }
        return ans;
    }
    public CostDirPair calculateNetHorizontalCost(Integer horizontalGap, String dir) {
        Integer distanceCost = Math.abs(horizontalGap)* Constants.DISTANCE_TRAVEL_COST;
        Integer turnCost = 0;
        String finalDir = dir;
        boolean isPositive = horizontalGap>0;
        switch (dir){
            case "E": if(!isPositive) {
                turnCost += 2*Constants.TURN_COST;
                finalDir = "W";
            }
                break;
            case "W": if(isPositive){
                turnCost += 2*Constants.TURN_COST;
                finalDir = "E";
            }
                break;
            case "S": turnCost += Constants.TURN_COST;
                finalDir = isPositive?"E":"W";
                break;

            case "N": turnCost += Constants.TURN_COST;
                finalDir = isPositive?"E":"W";
                break;

        }
        Integer totalCost = turnCost+distanceCost;
        return new CostDirPair(totalCost,finalDir);
    }

    public CostDirPair calculateNetVerticalCost(Integer verticalGap, String dir) {
        Integer distanceCost = Math.abs(verticalGap)*10;
        Integer turnCost = 0;
        String finalDir = dir;
        boolean isPositive = verticalGap>0;
        switch (dir){
            case "N": if(!isPositive) {
                turnCost += 10;
                finalDir = "S";
            }
                break;
            case "S": if(isPositive){
                turnCost += 10;
                finalDir = "N";
            }

                break;
            case "E": turnCost += 5;
                finalDir = isPositive?"N":"S";
                break;

            case "W": turnCost += 5;
                finalDir = isPositive?"N":"S";
                break;

        }
        Integer totalCost = turnCost+distanceCost;
        return new CostDirPair(totalCost,finalDir);
    }
}
