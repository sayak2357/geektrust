package com.example.geektrust;

import com.example.geektrust.service.RideSharingService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            RideSharingService rs = new RideSharingService();
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            rs.run(sc);

            sc.close(); // closes the scanner
        } catch (IOException e) {
        }

    }
}
