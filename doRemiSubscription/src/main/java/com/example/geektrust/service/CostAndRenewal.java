package com.example.geektrust.service;

import com.example.geektrust.model.Subscription;
import com.example.geektrust.constants.Constants.*;
import org.apache.commons.lang3.time.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.geektrust.constants.Constants.*;

public class CostAndRenewal {
    public void costAndRenewalCalculator(Subscription sub){
        if(!sub.isSubscription()){
            System.out.println("SUBSCRIPTIONS_NOT_FOUND");
            return;
        }
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
        sdfrmt.setLenient(false);
        calculateMusicSubRenewalDate(sub, sdfrmt);
        calculateVideoSubRenewalDate(sub, sdfrmt);
        calculatePodcastSubRenewalDate(sub, sdfrmt);
        costCalculator(sub);
    }
    private void calculateMusicSubRenewalDate(Subscription sub, SimpleDateFormat sdfrmt){
        String subStartDate = sub.getStartDate();
        try {
            Date subStartJavaDate = sdfrmt.parse(subStartDate);
            if (sub.getMusic() != null) {
                String plan = sub.getMusic();
                Date endDate = new Date();


                if (plan.equals("FREE") || plan.equals("PERSONAL")) {
                    endDate = DateUtils.addMonths(subStartJavaDate, MUSIC_PERSONAL_DURATION_MONTHS);
                }
                if (plan.equals("PREMIUM")) {
                    endDate = DateUtils.addMonths(subStartJavaDate, MUSIC_PREMIUM_DURATION_MONTHS);
                }
                endDate = DateUtils.addDays(endDate, DAYS_BEFORE_SUBSCRIPTION_ENDS);
                System.out.println("RENEWAL_REMINDER MUSIC " + sdfrmt.format(endDate));
            }
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    private void calculateVideoSubRenewalDate(Subscription sub, SimpleDateFormat sdfrmt){
        String subStartDate = sub.getStartDate();
        try {
            Date subStartJavaDate = sdfrmt.parse(subStartDate);
            if (sub.getVideo() != null) {
                String plan = sub.getVideo();
                Date endDate = new Date();


                if (plan.equals("FREE") || plan.equals("PERSONAL")) {
                    endDate = DateUtils.addMonths(subStartJavaDate, VIDEO_PERSONAL_DURATION_MONTHS);
                }
                if (plan.equals("PREMIUM")) {
                    endDate = DateUtils.addMonths(subStartJavaDate, VIDEO_PREMIUM_DURATION_MONTHS);
                }
                endDate = DateUtils.addDays(endDate, DAYS_BEFORE_SUBSCRIPTION_ENDS);
                System.out.println("RENEWAL_REMINDER VIDEO " + sdfrmt.format(endDate));
            }
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    private void calculatePodcastSubRenewalDate(Subscription sub, SimpleDateFormat sdfrmt){
        String subStartDate = sub.getStartDate();
        try {
            Date subStartJavaDate = sdfrmt.parse(subStartDate);
            if (sub.getVideo() != null) {
                String plan = sub.getPodcast();
                Date endDate = new Date();


                if (plan.equals("FREE") || plan.equals("PERSONAL")) {
                    endDate = DateUtils.addMonths(subStartJavaDate, PODCAST_PERSONAL_DURATION_MONTHS);
                }
                if (plan.equals("PREMIUM")) {
                    endDate = DateUtils.addMonths(subStartJavaDate, PODCAST_PREMIUM_DURATION_MONTHS);
                }
                endDate = DateUtils.addDays(endDate, DAYS_BEFORE_SUBSCRIPTION_ENDS);
                System.out.println("RENEWAL_REMINDER PODCAST " + sdfrmt.format(endDate));
            }
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
    private void costCalculator(Subscription sub){
        Integer cost = 0;
        if(sub.getMusic()!=null){
            String plan = sub.getMusic();
            switch (plan){
                case "FREE": break;
                case "PERSONAL": cost+=MUSIC_PERSONAL_COST;break;
                case "PREMIUM": cost+=MUSIC_PREMIUM_COST;break;
            }
        }
        if(sub.getVideo()!=null){
            String plan = sub.getVideo();
            switch (plan){
                case "FREE": break;
                case "PERSONAL": cost+=VIDEO_PERSONAL_COST;break;
                case "PREMIUM": cost+=VIDEO_PREMIUM_COST;break;
            }
        }
        if(sub.getPodcast()!=null){
            String plan = sub.getPodcast();
            switch (plan){
                case "FREE": break;
                case "PERSONAL": cost+=PODCAST_PERSONAL_COST;break;
                case "PREMIUM": cost+=PODCAST_PREMIUM_COST;break;
            }
        }
        if(sub.getTopup()!=null){
            String type = sub.getTopup();
            switch (type){
                case "FOUR_DEVICE": cost += sub.getDeviceCount()*FOUR_DEVICE_TOPUP_COST;break;
                case "TEN_DEVICE":  cost += sub.getDeviceCount()*TEN_DEVICE_TOPUP_COST;break;
            }
        }
        System.out.println("RENEWAL_AMOUNT "+cost);
    }
}
