package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.dao.LumpsumPaymentRepo;
import com.example.geektrust.entity.Lumpsum;

import java.util.List;

public class BalanceService {
    private LoanRepo loanRepo;
    private LumpsumPaymentRepo lumpsumPaymentRepo;
    private HelperService helperService;
    public BalanceService(LoanRepo loanRepo, LumpsumPaymentRepo lumpsumPaymentRepo) {

        this.loanRepo = loanRepo;
        this.lumpsumPaymentRepo = lumpsumPaymentRepo;
        this.helperService = new HelperService();
    }
    public Integer getTotalAmountPaid(String user,String bank,Integer emiNumber){
        Integer totalAmountPaid = 0;
        Double principal = loanRepo.getPrincipal(user,bank);
        Integer tenure = loanRepo.getTenure(user,bank);
        Double interest = loanRepo.getInterest(user,bank);
        Integer monthlyEmiAmount = helperService.getMonthlyEmi(principal,tenure,interest);
        Double netAmount = helperService.getNetAmount(principal,tenure,interest);
        List<Lumpsum> lumpsumPayments =  lumpsumPaymentRepo.getLumpsumPayments(user,bank);
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
        Double principal = loanRepo.getPrincipal(user,bank);
        Integer tenure = loanRepo.getTenure(user,bank);
        Double interest = loanRepo.getInterest(user,bank);
        Integer monthlyEmiAmount =      helperService.getMonthlyEmi(principal,tenure,interest);
        Integer totalAmountPaid = getTotalAmountPaid(user,bank,emiNumber);
        Double netAmount = helperService.getNetAmount(principal,tenure,interest);
        Integer amountRemaining = (int) Math.ceil(netAmount)-totalAmountPaid;
        res = amountRemaining/monthlyEmiAmount + (amountRemaining%monthlyEmiAmount==0 ? 0:1);
        return res;
    }
}
