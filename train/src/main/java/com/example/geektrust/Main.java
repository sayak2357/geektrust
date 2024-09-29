package com.example.geektrust;

import com.example.geektrust.service.ApplicatonService;

public class Main {
    public static void main(String[] args) {
        ApplicatonService applicatonService = new ApplicatonService();

        applicatonService.run(args[0]);

    }
}
