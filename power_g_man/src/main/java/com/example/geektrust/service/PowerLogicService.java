package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;
import com.example.geektrust.entity.Coordinate;
import com.example.geektrust.entity.CostDirPair;
import com.example.geektrust.entity.Gap;

public class PowerLogicService {
    private HelperService helperService;
    public PowerLogicService(){
        this.helperService = new HelperService();
    }
    public Integer getRemainingPower(Coordinate source, Coordinate destination, String dir){
        Gap gap = findGap(source,destination);
        Integer horizontalVerticalOrder = findHorizontalVerticalOrder(gap,dir);
        return Constants.TOTAL_BALANCE-getMinCost(gap,horizontalVerticalOrder,dir);
    }

    private Integer getMinCost(Gap gap, Integer horizontalVerticalOrder, String dir) {
        Integer horizontalGap = gap.getHorizontal();
        Integer verticalGap = gap.getVertical();
        Integer totalCost = 0;
        if(horizontalVerticalOrder>0){
            CostDirPair cdp = helperService.calculateNetHorizontalCost(horizontalGap,dir);
            totalCost += cdp.getCost();
            String newDir = cdp.getDir();
            cdp = helperService.calculateNetVerticalCost(verticalGap,newDir);
            totalCost += cdp.getCost();
        }
        else{
            CostDirPair cdp = helperService.calculateNetVerticalCost(verticalGap,dir);
            totalCost += cdp.getCost();
            String newDir = cdp.getDir();
            cdp = helperService.calculateNetHorizontalCost(horizontalGap,newDir);
            totalCost += cdp.getCost();
        }
        return totalCost;
    }



    private Gap findGap(Coordinate source, Coordinate destination){
        Integer horizontalGap = destination.getX()-source.getX();
        Integer verticalGap = destination.getY()-source.getY();
        return new Gap(horizontalGap,verticalGap);
    }
    private Integer findHorizontalVerticalOrder(Gap gap, String startingDir){
        Integer horizontalGap = gap.getHorizontal();
        Integer verticalGap = gap.getVertical();
        Integer horizontalTurnCost = helperService.findHorizontalTurnCost(horizontalGap,startingDir);
        Integer verticalTurnCost = helperService.findVerticalTurnCost(horizontalGap,startingDir);
        return horizontalTurnCost>verticalTurnCost? -1:1;
    }



}
