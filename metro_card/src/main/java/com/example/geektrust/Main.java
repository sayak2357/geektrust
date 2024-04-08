package com.example.geektrust;

import com.example.geektrust.service.ApplicationRunnerService;

public class Main {
    public static void main(String[] args) {
        ApplicationRunnerService applicationRunnerService = new ApplicationRunnerService(args[0]);
        applicationRunnerService.run();
    }
}
