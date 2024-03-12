package com.example.geektrust;

import com.example.geektrust.dao.Users;
import com.example.geektrust.model.House;
import com.example.geektrust.service.ExpenseManagementService;
import com.example.geektrust.service.HouseService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {

            Users users = new Users();
            HouseService houseService = new HouseService();
            ExpenseManagementService expenseManagementService = new ExpenseManagementService(houseService, users);
            House house = new House("h1");
            expenseManagementService.addHouse(house);

            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);
            expenseManagementService.run(sc);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //System.out.println("hello java");
    }
}
