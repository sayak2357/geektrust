package com.example.geektrust.service;

import com.example.geektrust.dao.LoanRepo;
import com.example.geektrust.entity.Loan;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class LedgerCompanyService {
    private LoanRepo loanRepo;

    public LedgerCompanyService() {
        this.loanRepo = new LoanRepo();
    }

    public void run(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis); // file to be scanned

            while (sc.hasNextLine()) {
                //System.out.println(sc.nextLine());
                String[] tokens = sc.nextLine().split(" ");
                String command = tokens[0];
                switch (command){
                    case "LOAN": String bank = tokens[1], user = tokens[2];
                                 Double principal = Double.parseDouble(tokens[3]);
                                 Integer tenure = Integer.parseInt(tokens[4]);
                                 Double interest = Double.parseDouble(tokens[5]);
                                 loanRepo.addLoan(bank,user,principal,tenure,interest);
                                 break;
                }
            }
            sc.close();
        } catch (IOException e) {
        }
    }
}
