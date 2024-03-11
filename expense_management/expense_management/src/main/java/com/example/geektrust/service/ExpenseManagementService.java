package com.example.geektrust.service;

import com.example.geektrust.dao.SplitWise;
import com.example.geektrust.dao.Users;
import com.example.geektrust.dto.Pair;
import com.example.geektrust.model.House;
import com.example.geektrust.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseManagementService {
    private HouseService houseService;
    private Users users;
    private SplitWiseService splitWiseService;
    public ExpenseManagementService(HouseService houseService, Users users){
        this.houseService = houseService;
        this.users = users;

        this.splitWiseService = new SplitWiseService();
    }
    public void moveIn(String userName, String houseId){
        User u = new User(userName);

        boolean userAddition = houseService.addUserToHouse(houseId,u);
        if(userAddition){
            users.addUser(u);
        }
    }
    public void addHouse(House house){
        houseService.addHouse(house);
    }
    public void expense(Double amount, String giver, List<String> borrower){
        Double amountPerHead = amount/(1+borrower.size());
        borrower.add(giver);
        boolean userCheck = validateUsers(borrower);
        if(!userCheck){
            System.out.println("MEMBER_NOT_FOUND");
            return;
        }
        for(String uname:borrower){
            if(!uname.equals(giver)){
                splitWiseService.aOwesB(giver,uname,amountPerHead);
            }
        }
        System.out.println("SUCCESS");
    }
    public boolean validateUsers(List<String> userNames){
        for(String uname:userNames){
            if(users.findByName(uname)==null)
                return false;
        }
        return true;
    }
    public void dues(String uname){
        List<String> user = new ArrayList<>();
        user.add(uname);
        boolean userCheck = validateUsers(user);
        if(!userCheck){
            System.out.println("MEMBER_NOT_FOUND");
            return;
        }
        splitWiseService.dues(uname);
    }
    public void clearDue(String borrower, String lender, Double amount){
        splitWiseService.clearDue(borrower, lender, amount);
    }
}
