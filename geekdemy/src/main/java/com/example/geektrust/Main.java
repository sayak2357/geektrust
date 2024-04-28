package com.example.geektrust;

import com.example.geektrust.service.ApplicationService;


public class Main {
    public static void main(String[] args) {
        ApplicationService aps = new ApplicationService();
        aps.run(args[0]);
    }
}
