package com.example.geektrust.helper;
import java.util.HashMap;
import java.util.Map;
public class Constants {
    public static Map<String,Integer> classPrice;
    public static Map<String,Integer> discountPrice;
    static{
        classPrice = new HashMap<>();
        classPrice.put("ADULT",200);
        classPrice.put("SENIOR_CITIZEN",100);
        classPrice.put("KID",50);
        discountPrice = new HashMap<>();
        discountPrice.put("ADULT",100);
        discountPrice.put("SENIOR_CITIZEN",50);
        discountPrice.put("KID",25);
    }
    public static Integer getPrice(String passengerType){
        return classPrice.get(passengerType);
    }
    public static Integer getDiscountedPrice(String passengerType){
        return discountPrice.get(passengerType);
    }
}
