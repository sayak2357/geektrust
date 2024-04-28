package com.example.geektrust.service;

import com.example.geektrust.constant.Fee;
import com.example.geektrust.model.Cart;
import com.example.geektrust.model.CourseCount;

import java.util.ArrayList;
import java.util.List;

public class Billservice {
    private double subtotal, total_pro_discount,total, pro_membership_fee, coupon_discount,enrollment_fee;
    private String coupon_applied;
    public Billservice(){
        this.subtotal = 0d;
        this.total_pro_discount = 0d;
        this.total = 0d;
        this.pro_membership_fee = 0d;
        this.coupon_discount = 0d;
        this.coupon_applied = "NONE";
        this.enrollment_fee = 0d;
    }
    public void printBill(Cart cart){
        calculateSubtotal(cart);
        calculateDiscount(cart);
        System.out.println("SUB_TOTAL "+subtotal);
        System.out.println("COUPON_DISCOUNT "+coupon_applied+" "+coupon_discount);
        System.out.println("TOTAL_PRO_DISCOUNT "+total_pro_discount);
        System.out.println("PRO_MEMBERSHIP_FEE "+pro_membership_fee);
        System.out.println("ENROLLMENT_FEE "+enrollment_fee);
        Double grandTotal = subtotal+enrollment_fee-coupon_discount;
        System.out.println("TOTAL "+grandTotal);
    }

    private void calculateSubtotal(Cart cart){
        List<CourseCount> listOfCourses = cart.getListOfCourses();
        Double totalProDis = 0d;
        for(CourseCount cc:listOfCourses){
            String cname = cc.course;
            Integer ct = cc.count;
            Double totalCost = Fee.getCost(cname)*ct;
            if(cart.isProMembership()){
                Double discount = Fee.getDiscountRate(cname);
                Double discountedcost = (totalCost - (totalCost*discount/100));
                subtotal += discountedcost;
                total_pro_discount += (totalCost*discount/100);
            }
            else
                subtotal += totalCost;
        }
        if(subtotal<6666){
            this.enrollment_fee = 500d;
        }
        if(cart.isProMembership()) {
            subtotal += Fee.getCost("PRO_MEMBERSHIP_FEE");
            pro_membership_fee += Fee.getCost("PRO_MEMBERSHIP_FEE");
        }
    }
    private void calculateDiscount(Cart cart){
        List<CourseCount> listOfCourses = cart.getListOfCourses();
        List<String> coupons = cart.getCoupons();
        Integer count = 0;
        List<Integer> prices = new ArrayList<>();
        for(CourseCount cc:listOfCourses){
            count += cc.count;
            prices.add(Fee.getCost(cc.course).intValue());
        }
        if(count>=4){
            this.coupon_applied = "B4G1";
            prices.sort((a,b) -> a-b);
            this.coupon_discount = prices.get(0);
        }
        else if(coupons.size()>0){
            if(coupons.size()==1 && coupons.get(0).equals("DEAL_G20") && subtotal>=10000){
                this.coupon_applied = "DEAL_G20";
                this.coupon_discount = subtotal*20/100;
            }
            else if(coupons.size()==1 && coupons.get(0).equals("DEAL_G5") && count>=2){
                this.coupon_applied = "DEAL_G5";
                this.coupon_discount = subtotal*5/100;
            }
            else if(coupons.size()==2 && (coupons.get(0).equals("DEAL_G20") || coupons.get(1).equals("DEAL_G20") )&& subtotal>=10000){
                this.coupon_applied = "DEAL_G20";
                this.coupon_discount = subtotal*20/100;
            }
            else if(coupons.size()==2 && (coupons.get(0).equals("DEAL_G5") || coupons.get(1).equals("DEAL_G5") )&& subtotal<10000){
                this.coupon_applied = "DEAL_G5";
                this.coupon_discount = subtotal*5/100;
            }
        }
        else
            this.coupon_applied = "NONE";
    }
}
