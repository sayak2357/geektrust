package com.example.geektrust.constant;

import java.util.HashMap;
import java.util.Map;

public class Fee {
    private static Map<String, Double> costMap = new HashMap<>();
    private static Map<String, Double> proDiscountMap = new HashMap<>();
    static {
        costMap.put("CERTIFICATION",3000d);
        costMap.put("DEGREE",5000d);
        costMap.put("DIPLOMA",2500d);
        costMap.put("PRO_MEMBERSHIP_FEE",200d);
        proDiscountMap.put("CERTIFICATION",2d);
        proDiscountMap.put("DEGREE",2d);
        proDiscountMap.put("DIPLOMA",1d);
    }

    public static Double getCost(String course){
        if(costMap.containsKey(course))
            return costMap.get(course);
        return 0d;
    }
    public static Double getDiscountRate(String course){
        if(proDiscountMap.containsKey(course))
            return proDiscountMap.get(course);
        return 0d;
    }
}
