package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.entity.Loan;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class LedgerCompanyService {
    private LoanRepo loanRepo;
    private BalanceService balanceService;

    public LedgerCompanyService() {

        this.loanRepo = new LoanRepo();
        this.balanceService = new BalanceService(this.loanRepo);
    }

    public boolean run(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis); // file to be scanned
            String bank,user;
            Double principal, interest,amount;
            Integer tenure,emiNumber;
            while (sc.hasNextLine()) {
                //System.out.println(sc.nextLine());
                String[] tokens = sc.nextLine().split(" ");
                String command = tokens[0];
                switch (command){
                    case "LOAN":            bank = tokens[1];
                                            user = tokens[2];
                                            principal = Double.parseDouble(tokens[3]);
                                            tenure = Integer.parseInt(tokens[4]);
                                            interest = Double.parseDouble(tokens[5]);
                                            loanRepo.addLoan(bank,user,principal,tenure,interest);
                                            break;
                    case "PAYMENT":         bank = tokens[1];
                                            user = tokens[2];
                                            amount = Double.parseDouble(tokens[3]);
                                            emiNumber = Integer.parseInt(tokens[4]);
                                            loanRepo.addLumpsum(user,bank,amount,emiNumber);
                                            break;
                    case "BALANCE":         bank = tokens[1];
                                            user = tokens[2];
                                            emiNumber = Integer.parseInt(tokens[3]);
                                            Integer totalAmountPaid = balanceService.getTotalAmountPaid(user,bank,emiNumber);
                                            Integer emisRamaining   = balanceService.getEmisRemaining(user,bank,emiNumber);
                                            System.out.println(bank+" "+user+" "+totalAmountPaid+" "+emisRamaining);
                                            break;
                    default:
                                            System.out.println("error");
                                            break;
                }
            }
            sc.close();
        } catch (IOException e) {
        }
        return true;
    }
}
