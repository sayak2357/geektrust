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
            ExpenseManagementService expenseManagementService = new ExpenseManagementService(houseService,users);
            House house = new House("h1");
            expenseManagementService.addHouse(house);

            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis);

            while (sc.hasNextLine()) {
                //System.out.println(sc.nextLine());
                String[] ops = sc.nextLine().split(" ");
                String op = ops[0];
                if(op.equals("MOVE_IN")){
                    String userName = ops[1];
                    expenseManagementService.moveIn(userName,"h1");
                }
                else if(op.equals("SPEND")){
                    Double amount = Double.parseDouble(ops[1]);
                    String spender = ops[2];
                    List<String> borrower = new ArrayList<>();
                    for(int i=3;i<ops.length;i++){
                        borrower.add(ops[i]);
                    }
                    expenseManagementService.expense(amount,spender,borrower);
                }
                else if(op.equals("DUES")){
                    String userWhoOwes = ops[1];
                    expenseManagementService.dues(userWhoOwes);
                }
            }
            sc.close(); // closes the scanner
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("hello java");
    }
}
