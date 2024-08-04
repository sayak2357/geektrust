package com.example.geektrust.dao;

import com.example.geektrust.constant.Constants;
import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.Lumpsum;
import static com.example.geektrust.constant.Constants.DELIMETER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanRepo {
    private Map<String, Loan> loanRepos;
    public LoanRepo(){
        this.loanRepos = new HashMap<>();
    }
    public boolean addLoan(String bank, String user, Double principal, Integer tenure, Double interest){
        Loan loan = new Loan(user,bank,principal,tenure,interest);
        String userBank = user+DELIMETER+bank;
        if(loanRepos.containsKey(userBank))
            return false;
        loanRepos.put(userBank,loan);
        return true;
    }
    public void addLumpsum(String user, String bank,Double amount, Integer emiNumber){
        Lumpsum lumpsum  = new Lumpsum(amount,emiNumber);
        String userBank = user + DELIMETER +bank;
        Loan userLoan = loanRepos.get(userBank);
        userLoan.addLumpsumPayment(lumpsum);
    }
    public List<Lumpsum> getLumpsumPayments(String user, String bank){
        String userBank = user+DELIMETER+bank;
        Loan userLoan = loanRepos.get(userBank);
        return userLoan.getLumpsumPayments();
    }
    public Integer getMonthlyEmiAmount(String user, String bank){
        String userBank = user+DELIMETER+bank;
        return loanRepos.get(userBank).getMonthlyEmi();
    }
    public Double getNetAmount(String user, String bank){
        String userBank = user+DELIMETER+bank;
        return loanRepos.get(userBank).getNetAmount();
    }
}
