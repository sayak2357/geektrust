package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.entity.Loan;

public class LoanService {
    private LoanRepo loanRepo;
    public LoanService(LoanRepo loanRepo){
        this.loanRepo = loanRepo;
    }
    public boolean addLoan(String bank, String user, Double principal, Integer tenure, Double interest){
        Loan loan = new Loan(user,bank,principal,tenure,interest);
        return loanRepo.addLoan(loan,bank,user);
    }
}
