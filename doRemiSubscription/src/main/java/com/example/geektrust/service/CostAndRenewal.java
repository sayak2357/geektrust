package com.example.geektrust.service;

import com.example.geektrust.model.Subscription;
import org.apache.commons.lang3.time.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CostAndRenewal {
    public void costAndRenewalCalculator(Subscription sub){
        if(!sub.isSubscription()){
            System.out.println("SUBSCRIPTIONS_NOT_FOUND");
            return;
        }
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
        sdfrmt.setLenient(false);
        String subStartDate = sub.getStartDate();
        try {
            Date subStartJavaDate = sdfrmt.parse(subStartDate);

            if (sub.getMusic() != null) {
                String plan = sub.getMusic();
                Date endDate = new Date();


                if(plan.equals("FREE") || plan.equals("PERSONAL")){
                    endDate = DateUtils.addMonths(subStartJavaDate, 1);
                }
                if(plan.equals("PREMIUM")){
                    endDate = DateUtils.addMonths(subStartJavaDate, 3);
                }
                endDate = DateUtils.addDays(endDate,-10);
                System.out.println("RENEWAL_REMINDER MUSIC "+sdfrmt.format(endDate));
            }
            if (sub.getVideo() != null) {
                String plan = sub.getVideo();
                Date endDate = new Date();
                if(plan.equals("FREE") || plan.equals("PERSONAL")){
                    endDate = DateUtils.addMonths(subStartJavaDate, 1);
                }
                if(plan.equals("PREMIUM")){
                    endDate = DateUtils.addMonths(subStartJavaDate, 3);
                }
                endDate = DateUtils.addDays(endDate,-10);
                System.out.println("RENEWAL_REMINDER VIDEO "+sdfrmt.format(endDate));
            }
            if (sub.getPodcast() != null) {
                String plan = sub.getPodcast();
                Date endDate = new Date();
                if(plan.equals("FREE") || plan.equals("PERSONAL")){
                    endDate = DateUtils.addMonths(subStartJavaDate, 1);
                }
                if(plan.equals("PREMIUM")){
                    endDate = DateUtils.addMonths(subStartJavaDate, 3);
                }
                endDate = DateUtils.addDays(endDate,-10);

                System.out.println("RENEWAL_REMINDER PODCAST "+sdfrmt.format(endDate));
            }
            costCalculator(sub);
        }
        catch (ParseException e)
        {
            System.out.println(subStartDate+" is Invalid Date format");

        }
    }
    private void costCalculator(Subscription sub){
        Integer cost = 0;
        if(sub.getMusic()!=null){
            String plan = sub.getMusic();
            switch (plan){
                case "FREE": break;
                case "PERSONAL": cost+=100;break;
                case "PREMIUM": cost+=250;break;
            }
        }
        if(sub.getVideo()!=null){
            String plan = sub.getVideo();
            switch (plan){
                case "FREE": break;
                case "PERSONAL": cost+=200;break;
                case "PREMIUM": cost+=500;break;
            }
        }
        if(sub.getPodcast()!=null){
            String plan = sub.getPodcast();
            switch (plan){
                case "FREE": break;
                case "PERSONAL": cost+=100;break;
                case "PREMIUM": cost+=300;break;
            }
        }
        if(sub.getTopup()!=null){
            String type = sub.getTopup();
            if(type.equals("FOUR_DEVICE"))
                cost += sub.getDeviceCount()*50;
            else if(type.equals("TEN_DEVICE"))
                cost += sub.getDeviceCount()*100;
        }
        System.out.println("RENEWAL_AMOUNT "+cost);
    }
}
