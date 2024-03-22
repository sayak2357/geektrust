package com.example.geektrust;

import com.example.geektrust.service.ApplicationService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            ApplicationService as = new ApplicationService();
            as.run(sc);

        } catch (IOException e) {
        }
    }
}
