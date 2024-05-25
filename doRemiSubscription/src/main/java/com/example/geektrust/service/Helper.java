package com.example.geektrust.service;

import com.example.geektrust.model.Subscription;

public class Helper {
    public void addPlan(Subscription sub,String category, String plan){
        if(sub.getStartDate()==null){
            System.out.println("ADD_SUBSCRIPTION_FAILED INVALID_DATE");
            return;
        }
        switch (category){
            case "MUSIC": addMusic(sub,plan);break;
            case "VIDEO": addVideo(sub,plan);break;
            case "PODCAST": addPodcast(sub,plan);break;
        }

    }
    private void addMusic(Subscription sub, String plan){

        if(sub.getMusic()!=null){
            System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
        }
        else{
            sub.setMusic(plan);
            sub.setSubscription(true);
        }
    }
    private void addVideo(Subscription sub, String plan){
        if(sub.getVideo()!=null){
            System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
        }
        else{
            sub.setVideo(plan);
            sub.setSubscription(true);
        }
    }
    private void addPodcast(Subscription sub, String plan){
        if(sub.getPodcast()!=null){
            System.out.println("ADD_SUBSCRIPTION_FAILED DUPLICATE_CATEGORY");
        }
        else{
            sub.setPodcast(plan);
            sub.setSubscription(true);
        }
    }
    public void addTopup(Subscription sub,String type, Integer count){
        if(sub.getTopup()!=null){
            System.out.println("ADD_TOPUP_FAILED DUPLICATE_TOPUP");
        }
        else{
            if(sub.getStartDate()==null)
                System.out.println("ADD_TOPUP_FAILED INVALID_DATE");
            else if(!sub.isSubscription())
                System.out.println("ADD_TOPUP_FAILED SUBSCRIPTIONS_NOT_FOUND");
            else {
                sub.setTopup(type);
                sub.setDeviceCount(count);
            }
        }
    }
}
