package com.geektrust.racetrackManagement;

import com.geektrust.racetrackManagement.service.ApplicationService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            ApplicationService applicationService = new ApplicationService();
            applicationService.run(sc);
            sc.close();
        } catch (IOException e) {
        }

    }
}
