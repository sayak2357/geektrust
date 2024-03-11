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

    private SplitWise splitWise;
    public ExpenseManagementService(HouseService houseService, Users users){
        this.houseService = houseService;
        this.users = users;
        this.splitWise = new SplitWise();
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
                splitWise.aOwesB(giver,uname,amountPerHead);
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
            return ;
        }
        List<String> owesTo = splitWise.getAllOwesTo(uname);
        List<Pair> userAmounts = new ArrayList<>();
        for(String lender:owesTo){
            Double amount = splitWise.getDue(lender,uname);
            userAmounts.add(new Pair(lender,amount));
        }
        Collections.sort(userAmounts,
                (a,b) -> a.getAmount()== b.getAmount() ? a.getUname().compareTo(b.getUname()): b.getAmount().compareTo(a.getAmount()));
        for(Pair p:userAmounts){
            System.out.println(p.getUname()+" "+p.getAmount());
        }
    }
}
