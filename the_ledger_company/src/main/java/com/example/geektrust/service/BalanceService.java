package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.entity.Lumpsum;

import java.util.List;

public class BalanceService {
    private LoanRepo loanRepo;

    public BalanceService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }
    public Integer getTotalAmountPaid(String user,Integer emiNumber){
        Integer totalAmountPaid = 0;
        Integer monthlyEmiAmount =      loanRepo.getMonthlyEmiAmount(user);
        Double netAmount = loanRepo.getNetAmount(user);
        List<Lumpsum> lumpsumPayments =  loanRepo.getLumpsumPayments(user);
        totalAmountPaid += Math.min(monthlyEmiAmount*emiNumber,netAmount.intValue());
        for(Lumpsum lsum:lumpsumPayments){
            if(lsum.getEmiNumber()<=emiNumber)
                totalAmountPaid +=  lsum.getAmount().intValue();
        }
        return totalAmountPaid;
    }
    public Integer getEmisRemaining(String user,Integer emiNumber){
        int res = 0;
        Integer monthlyEmiAmount =      loanRepo.getMonthlyEmiAmount(user);
        Integer totalAmountPaid = getTotalAmountPaid(user,emiNumber);
        Double netAmount = loanRepo.getNetAmount(user);
        Integer amountRemaining = (int) Math.ceil(netAmount)-totalAmountPaid;
        res = amountRemaining/monthlyEmiAmount + (amountRemaining%monthlyEmiAmount==0 ? 0:1);
        return res;
    }
}
