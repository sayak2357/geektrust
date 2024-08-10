package com.example.geektrust.dao;

import com.example.geektrust.entity.Loan;
import static com.example.geektrust.constant.Constants.DELIMETER;

import java.util.HashMap;
import java.util.Map;

public class LoanRepo {
    private Map<String, Loan> loanRepos;
    public LoanRepo(){
        this.loanRepos = new HashMap<>();
    }
    public boolean addLoan(Loan loan, String bank, String user){

        String userBank = user+DELIMETER+bank;
        if(loanRepos.containsKey(userBank))
            return false;
        loanRepos.put(userBank,loan);
        return true;
    }

    public Double getPrincipal(String user, String bank){
        String userBank = user+DELIMETER+bank;
        Loan loan =  loanRepos.get(userBank);
        return loan.getPrincipal();
    }
    public Integer getTenure(String user, String bank){
        String userBank = user+DELIMETER+bank;
        return loanRepos.get(userBank).getNumber_of_years_tenure();
    }
    public Double getInterest(String user, String bank){
        String userBank = user+DELIMETER+bank;
        return loanRepos.get(userBank).getInterest();
    }
}
