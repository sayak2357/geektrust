package com.example.geektrust;

import com.example.geektrust.service.ApplicationService;

public class Main {
    public static void main(String[] args) {

        ApplicationService applicationService = new ApplicationService();
        applicationService.run(args[0]);

    }
}
