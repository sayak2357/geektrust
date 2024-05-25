package com.example.geektrust.service;

import com.example.geektrust.model.Subscription;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Application {
    private Helper helper;
    private CostAndRenewal costAndRenewal;
    public Application(){
        this.helper = new Helper();
        this.costAndRenewal = new CostAndRenewal();
    }
    public void start(Scanner sc){
        Subscription sub = new Subscription();
        while (sc.hasNextLine()) {
            //System.out.println(sc.nextLine());
            String[] ip = sc.nextLine().split(" ");
            String op = ip[0];
            if(op.equals("START_SUBSCRIPTION")){
                String startDate = ip[1];
                boolean isValidDate = validateDate(startDate);
                if(isValidDate){
                    sub.setStartDate(startDate);
                }
                else {
                    System.out.println("INVALID_DATE");
                }
            }
            else if(op.equals("ADD_SUBSCRIPTION")){
                String category = ip[1];
                String plan = ip[2];
                helper.addPlan(sub,category,plan);
            }
            else if(op.equals("ADD_TOPUP")){
                String type = ip[1];
                Integer count = Integer.parseInt(ip[2]);
                helper.addTopup(sub,type,count);
            }
            else if(op.equals("PRINT_RENEWAL_DETAILS")){
                costAndRenewal.costAndRenewalCalculator(sub);
            }
        }
    }
    private boolean validateDate(String date){
        if(date.trim().equals(""))
        {
            return true;
        }
        else
        {
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
            sdfrmt.setLenient(false);
            try
            {
                Date javaDate = sdfrmt.parse(date);
                //System.out.println(date+" is valid date format");
            }
            catch (ParseException e)
            {
                System.out.println(date+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }
}
