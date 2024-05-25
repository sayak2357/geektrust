package com.example.geektrust;

import com.example.geektrust.service.Application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            Application applicationService = new Application();
            applicationService.start(sc);
            sc.close();
        } catch (IOException e) {
        }

    }
}
