package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;

public class LoanService {
    private LoanRepo loanRepo;
    public LoanService(LoanRepo loanRepo){
        this.loanRepo = loanRepo;
    }
    public boolean addLoan(String bank, String user, Double principal, Integer tenure, Double interest){
        return loanRepo.addLoan(bank,user,principal,tenure,interest);
    }
}
