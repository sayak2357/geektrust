package com.example.geektrust.service;

import com.example.geektrust.dao.SplitWise;
import com.example.geektrust.dto.Pair;

import java.text.DecimalFormat;
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

        List<String> giverOwesTo = getAllOwesTo(giverName);
        List<String> takerOwesTo = getAllOwesTo(takerName);
        Double existingAmount = splitWise.getDue(giverName,takerName);
        if(giverOwesTo!=null && takerOwesTo!=null && existingAmount>0){
            String commonLender = findIntersection(giverOwesTo,takerOwesTo,giverName,takerName);
            if(commonLender==null)
                return;
            Double giverOwesToCommonLender = getDue(commonLender,giverName);
            Double takerOwesToCommonLender = getDue(commonLender,takerName);
            if(takerOwesToCommonLender>existingAmount && giverOwesToCommonLender>existingAmount){
                splitWise.aOwesB(takerName,giverName,existingAmount);
                splitWise.aOwesB(commonLender,takerName,existingAmount);
                splitWise.aOwesB(giverName,commonLender,existingAmount);
            }
        }
    }
    public String findIntersection(List<String> giverOwesTo,List<String> takerOwesTo, String giverName, String takerName){
        for(String lender:giverOwesTo){
            if(!lender.equals(giverName) && !lender.equals(takerName) && takerOwesTo.contains(lender))
                return lender;
        }
        return null;
    }
    public List<String> getAllOwesTo(String uname) {
        return splitWise.getAllOwesTo(uname);
    }
    public Double getDue(String lender,String borrower){
        return splitWise.getDue(lender,borrower);
    }
    public void clearDue(String borrower, String lender, Double amount){
        List<Pair> borrowerDues = getDuesInfo(borrower);
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
                    //Integer newD = newDue.intValue();
                    aOwesB(borrower,lender,amount);
                    DecimalFormat format = new DecimalFormat("0.#");

                    System.out.println(format.format(newDue));
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
            //Integer amount = p.getAmount().intValue();
            DecimalFormat format = new DecimalFormat("0.#");
            System.out.println(p.getUname()+" "+format.format(p.getAmount()));
        }
        return userAmounts;
    }
    public List<Pair> getDuesInfo(String uname) {
        List<String> owesTo = getAllOwesTo(uname);
        List<Pair> userAmounts = new ArrayList<>();
        for(String lender:owesTo){
            Double amount = getDue(lender,uname);
            userAmounts.add(new Pair(lender,amount));
        }
        return userAmounts;
    }


}
