package com.example.geektrust.dao;

import com.example.geektrust.entity.Loan;
import com.example.geektrust.entity.Lumpsum;

import java.util.HashMap;
import java.util.Map;

public class LoanRepo {
    private Map<String, Loan> loanRepos;
    public LoanRepo(){
        this.loanRepos = new HashMap<>();
    }
    public boolean addLoan(String bank, String user, Double principal, Integer tenure, Double interest){
        Loan loan = new Loan(user,bank,principal,tenure,interest);
        if(loanRepos.containsKey(user))
            return false;
        loanRepos.put(user,loan);
        return true;
    }
    public void addLumpsum(String user, Integer amount, Integer emiNumber){
        Lumpsum lumpsum  = new Lumpsum(amount,emiNumber);
        Loan userLoan = loanRepos.get(user);
        userLoan.addLumpsumPayment(lumpsum);
    }
}
