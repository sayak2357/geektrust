package com.example.geektrust.service;

import com.example.geektrust.model.Subscription;

public class Helper {
    public void addPlan(Subscription sub,String category, String plan){
        if(sub.getStartDate()==null){
            System.out.println("ADD_SUBSCRIPTION_FAILED INVALID_DATE");
            return;
        }
        if(category.equals("MUSIC")){
            if(sub.getMusic()!=null){
                System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
            }
            else{
                sub.setMusic(plan);
                sub.setSubscription(true);
            }
        }
        else if(category.equals("VIDEO")){
            if(sub.getVideo()!=null){
                System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
            }
            else{
                sub.setVideo(plan);
                sub.setSubscription(true);
            }
        }
        else if(category.equals("PODCAST")){
            if(sub.getPodcast()!=null){
                System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
            }
            else{
                sub.setPodcast(plan);
                sub.setSubscription(true);
            }
        }
    }
    public void addTopup(Subscription sub,String type, Integer count){
        if(sub.getTopup()!=null){
            System.out.println("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
        }
        else{
            if(!sub.isSubscription())
                System.out.println("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND");
            else {
                sub.setTopup(type);
                sub.setDeviceCount(count);
            }
        }
    }
}
