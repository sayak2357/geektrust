package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.entity.CostDirPair;

import static com.example.geektrust.constants.Constants.directions.*;

public class HelperService {
    public Integer findHorizontalTurnCost(Integer horizontalGap, Constants.directions startingDir) {
        Integer ans = 0;
        if(horizontalGap==0)
            return ans;
        boolean isPositive = horizontalGap>0;
        switch(startingDir){
            case E: if(!isPositive)
                ans = Constants.DISTANCE_TRAVEL_COST;
                break;
            case W: if(isPositive)
                ans = Constants.DISTANCE_TRAVEL_COST;
                break;
            case N: ans = Constants.TURN_COST;break;

            case S: ans = Constants.TURN_COST;break;
        }
        return ans;
    }
    public Constants.directions findDireectionEnum(String dir){
        Constants.directions dirEnum = null;
        switch(dir){
            case "E": dirEnum= E;break;
            case "W": dirEnum= W;break;
            case "N": dirEnum=Constants.directions.N;break;
            case "S": dirEnum= S;break;
        }
        return dirEnum;
    }
    public Integer findVerticalTurnCost(Integer verticalGap, Constants.directions startingDir) {
        Integer ans = 0;
        if(verticalGap==0)
            return ans;
        boolean isPositive = verticalGap>0;
        switch(startingDir){
            case N: if(!isPositive)
                ans = Constants.DISTANCE_TRAVEL_COST;
                break;
            case S: if(isPositive)
                ans = Constants.DISTANCE_TRAVEL_COST;
                break;
            case E: ans = Constants.TURN_COST;break;

            case W: ans = Constants.TURN_COST;break;
        }
        return ans;
    }
    public CostDirPair calculateNetHorizontalCost(Integer horizontalGap, Constants.directions dir) {
        Integer distanceCost = Math.abs(horizontalGap)* Constants.DISTANCE_TRAVEL_COST;
        Integer turnCost = 0;
        Constants.directions finalDir = dir;
        boolean isPositive = horizontalGap>0;
        switch (dir){
            case E: if(!isPositive) {
                turnCost += 2*Constants.TURN_COST;
                finalDir = W;
            }
                break;
            case W: if(isPositive){
                turnCost += 2*Constants.TURN_COST;
                finalDir = E;
            }
                break;
            case S: turnCost += Constants.TURN_COST;
                finalDir = isPositive?E:W;
                break;

            case N: turnCost += Constants.TURN_COST;
                finalDir = isPositive?E:W;
                break;

        }
        Integer totalCost = turnCost+distanceCost;
        return new CostDirPair(totalCost,finalDir);
    }

    public CostDirPair calculateNetVerticalCost(Integer verticalGap, Constants.directions dir) {
        Integer distanceCost = Math.abs(verticalGap)*10;
        Integer turnCost = 0;
        Constants.directions finalDir = dir;
        boolean isPositive = verticalGap>0;
        switch (dir){
            case N: if(!isPositive) {
                turnCost += Constants.TURN_COST;
                finalDir = S;
            }
                break;
            case S: if(isPositive){
                turnCost += Constants.TURN_COST;
                finalDir = N;
            }

                break;
            case E: turnCost += Constants.TURN_COST;
                finalDir = isPositive?N:S;
                break;

            case W: turnCost += Constants.TURN_COST;
                finalDir = isPositive?N:S;
                break;

        }
        Integer totalCost = turnCost+distanceCost;
        return new CostDirPair(totalCost,finalDir);
    }
}
