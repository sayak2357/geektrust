package com.example.geektrust.service;

import com.example.geektrust.dao.SplitWise;
import com.example.geektrust.dto.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplitWiseService {
    private SplitWise splitWise;
    public SplitWiseService(){
        this.splitWise = new SplitWise();
    }
    public void aOwesB(String giverName, String takerName, Double amount){
        splitWise.aOwesB(giverName,takerName,amount);
    }
    public List<String> getAllOwesTo(String uname) {
        return splitWise.getAllOwesTo(uname);
    }
    public Double getDue(String lender,String borrower){
        return splitWise.getDue(lender,borrower);
    }
    public void clearDue(String borrower, String lender, Double amount){
        List<Pair> borrowerDues = dues(borrower);
        boolean flag = false;
        for(Pair p:borrowerDues){
            if(p.getUname().equals(lender)){
                Double dueAmount = p.getAmount();
                if(dueAmount<amount){
                    System.out.println("INCORRECT_PAYMENT");
                    return;
                }
                else{
                    flag = true;
                    Double newDue = dueAmount-amount;
                    aOwesB(borrower,lender,amount);
                    System.out.println(newDue);
                    break;
                }
            }
        }
        if(!flag)
            System.out.println("INCORRECT_PAYMENT");
    }
    public List<Pair> dues(String uname){
        List<String> owesTo = getAllOwesTo(uname);
        List<Pair> userAmounts = new ArrayList<>();
        for(String lender:owesTo){
            Double amount = getDue(lender,uname);
            userAmounts.add(new Pair(lender,amount));
        }
        Collections.sort(userAmounts,
                (a,b) -> a.getAmount()== b.getAmount() ? a.getUname().compareTo(b.getUname()): b.getAmount().compareTo(a.getAmount()));
        for(Pair p:userAmounts){
            System.out.println(p.getUname()+" "+p.getAmount());
        }
        return userAmounts;
    }

}
