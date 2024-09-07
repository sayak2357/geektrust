package com.example.geektrust.service;

import com.example.geektrust.entity.Coordinate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ApplicationService {
    private PowerLogicService powerLogicService;
    public ApplicationService(){
        this.powerLogicService = new PowerLogicService();
    }
    public void run(String arg){
        try {

            FileInputStream fis = new FileInputStream(arg);
            Scanner sc = new Scanner(fis);
            Integer sx = null, sy = null, dx = null, dy = null;
            String dir = null;
            while (sc.hasNextLine()) {
                String[] keys = sc.nextLine().split(" ");
                String command = keys[0];
                switch (command){
                    case "SOURCE": sx = Integer.parseInt(keys[1]);sy = Integer.parseInt(keys[2]); dir = keys[3];break;
                    case "DESTINATION":  dx = Integer.parseInt(keys[1]);dy = Integer.parseInt(keys[2]);break;
                    case "PRINT_POWER":
                        Coordinate source = new Coordinate(sx,sy);
                        Coordinate destination = new Coordinate(dx,dy);
                        Integer remainingPower = powerLogicService.getRemainingPower(source,destination,dir);
                        System.out.println(remainingPower);
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }
    }
}
