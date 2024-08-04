package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.entity.Lumpsum;

import java.util.List;

public class BalanceService {
    private LoanRepo loanRepo;

    public BalanceService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }
    public Integer getTotalAmountPaid(String user,String bank,Integer emiNumber){
        Integer totalAmountPaid = 0;
        Integer monthlyEmiAmount =      loanRepo.getMonthlyEmiAmount(user,bank);
        Double netAmount = loanRepo.getNetAmount(user,bank);
        List<Lumpsum> lumpsumPayments =  loanRepo.getLumpsumPayments(user,bank);
        totalAmountPaid += Math.min(monthlyEmiAmount*emiNumber,netAmount.intValue());
        for(Lumpsum lsum:lumpsumPayments){
            if(lsum.getEmiNumber()<=emiNumber)
                totalAmountPaid +=  lsum.getAmount().intValue();
        }
        totalAmountPaid = Math.min(totalAmountPaid,netAmount.intValue());
        return totalAmountPaid;
    }
    public Integer getEmisRemaining(String user,String bank, Integer emiNumber){
        int res = 0;
        Integer monthlyEmiAmount =      loanRepo.getMonthlyEmiAmount(user,bank);
        Integer totalAmountPaid = getTotalAmountPaid(user,bank,emiNumber);
        Double netAmount = loanRepo.getNetAmount(user,bank);
        Integer amountRemaining = (int) Math.ceil(netAmount)-totalAmountPaid;
        res = amountRemaining/monthlyEmiAmount + (amountRemaining%monthlyEmiAmount==0 ? 0:1);
        return res;
    }
}
