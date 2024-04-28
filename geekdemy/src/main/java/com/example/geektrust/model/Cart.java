package com.example.geektrust.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

     private List<CourseCount> listOfCourses;

     private List<String> coupons;
     private boolean proMembership;
     public Cart(){
         this.listOfCourses = new ArrayList<>();
         this.coupons = new ArrayList<>();
         this.proMembership = false;
     }
     public void addCourse(String course, Integer count){
         CourseCount cc = new CourseCount(course,count);
         this.listOfCourses.add(cc);
     }
     public void addCoupon(String coupon){
         this.coupons.add(coupon);
     }
     public void purchaseProMembership(){
         this.proMembership = true;
     }
     public boolean isProMembership(){
         return this.proMembership;
     }
     public List<String> getCoupons(){
         return this.coupons;
     }
     public List<CourseCount> getListOfCourses(){
         return this.listOfCourses;
     }
}
