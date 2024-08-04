package com.example.geektrust;

import com.example.geektrust.service.LedgerCompanyService;

public class Main {
    public static void main(String[] args) {

        LedgerCompanyService ledgerCompanyService = new LedgerCompanyService();
        ledgerCompanyService.run(args[0]);

    }
}
