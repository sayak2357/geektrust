package com.example.geektrust.service;

import com.example.geektrust.model.Cart;

import java.io.FileInputStream;
import java.util.Scanner;

public class ApplicationService {
    private Cart cart;
    private Billservice bs;
    public ApplicationService(){
        this.cart = new Cart();
        this.bs = new Billservice();
    }
    public void run(String file){
            // the file to be opened for reading
            try {
                FileInputStream fis = new FileInputStream(file);
                Scanner sc = new Scanner(fis); // file to be scanned
                // returns true if there is another line to read
                while (sc.hasNextLine()) {
                    //System.out.println(sc.nextLine());
                    String[] command = sc.nextLine().split(" ");
                    if(command[0].equals("ADD_PROGRAMME")){
                        String cname = command[1];
                        Integer quantity = Integer.parseInt(command[2]);
                        cart.addCourse(cname,quantity);
                    }
                    else if(command[0].equals("APPLY_COUPON")){
                        String cname = command[1];
                        cart.addCoupon(cname);
                    }
                    else if(command[0].equals("ADD_PRO_MEMBERSHIP")){
                        cart.purchaseProMembership();
                    }
                    else
                        bs.printBill(cart);
                }
                sc.close(); // closes the scanner
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

    }
}
