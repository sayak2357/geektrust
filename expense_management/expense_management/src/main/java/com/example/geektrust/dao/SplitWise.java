package com.example.geektrust.dao;

import com.example.geektrust.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitWise {
    private Map<String, Double> A_OWES_B;
    private Map<String, List<String>> owes;
    public SplitWise(){
        A_OWES_B = new HashMap<>();
        owes = new HashMap<>();
    }

    public void aOwesB(String giverName, String takerName, Double amount){
        String key = takerName+"#"+giverName;
        Double existingAmount = A_OWES_B.containsKey(key) ? A_OWES_B.get(key): 0d;
        existingAmount+=amount;

        String reverseKey = giverName+"#"+takerName;
        Double reverseAmount = A_OWES_B.containsKey(reverseKey)? A_OWES_B.get(reverseKey): 0d;
        if(reverseAmount>=existingAmount){
            reverseAmount-=existingAmount;
            existingAmount = 0d;
            A_OWES_B.put(reverseKey,reverseAmount);
            A_OWES_B.put(key,existingAmount);
        }
        else{
            existingAmount-=reverseAmount;
            reverseAmount = 0d;
            A_OWES_B.put(reverseKey,reverseAmount);
            A_OWES_B.put(key,existingAmount);
        }
        if(!owes.containsKey(takerName)){
            owes.put(takerName,new ArrayList<>());
        }
        if(!owes.containsKey(giverName)){
            owes.put(giverName,new ArrayList<>());
        }
        List<String> owesToList = owes.get(takerName);
        if(!owesToList.contains(giverName)) {
            owesToList.add(giverName);
            owes.put(takerName,owesToList);
        }
        if(existingAmount==0){
            if(owesToList.contains(giverName)) {
                owesToList.remove(giverName);
            }
        }
        List<String> reverseOwesToList = owes.get(giverName);
        if(reverseAmount==0){
            if(reverseOwesToList.contains(takerName)) {
                reverseOwesToList.remove(takerName);
            }
        }
        return;
    }
    public List<String> getAllOwesTo(String uname){
        return owes.containsKey(uname) ? owes.get(uname):null;
    }
    public Double getDue(String lender,String borrower){
        String key = borrower+"#"+lender;
        return A_OWES_B.containsKey(key)? A_OWES_B.get(key):0d;
    }
}
