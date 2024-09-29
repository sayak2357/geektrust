package com.example.geektrust.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ApplicatonService {
    private TrainMergeService trainMergeService;

    public ApplicatonService() {
        this.trainMergeService = new TrainMergeService();
    }

    public boolean run(String inputFile){
        try {
            FileInputStream fis = new FileInputStream(inputFile);
            Scanner sc = new Scanner(fis);
            List<String> trainAArrival;
            List<String> trainBArrival;
            List<String> mergedDeparture;
            while (sc.hasNextLine()) {
                String[] tokens = sc.nextLine().split(" ");
                String train = tokens[0];
                switch (train){
                    case "TRAIN_A": for(int i=2;i<tokens.length;i++)
                                    {
                                        String boggie = tokens[i];
                                        trainMergeService.addToTrain(boggie,true);
                                    }
                                    break;
                    case "TRAIN_B": for(int i=2;i<tokens.length;i++)
                                    {
                                        String boggie = tokens[i];
                                        trainMergeService.addToTrain(boggie,false);
                                    }
                                    break;
                }
            }
            trainAArrival = trainMergeService.getArrivalList(true);
            trainBArrival = trainMergeService.getArrivalList(false);
            mergedDeparture = trainMergeService.getDeparatureList();
            String trainAArrivalMessage = "ARRIVAL "+generateResponseArrival("TRAIN_A",trainAArrival);
            String trainBArrivalMessage = "ARRIVAL "+generateResponseArrival("TRAIN_B",trainBArrival);
            String trainDepartureMessage = "DEPARTURE "+generateResponseArrival("TRAIN_AB ENGINE",mergedDeparture);
            System.out.println(trainAArrivalMessage);
            System.out.println(trainBArrivalMessage);
            System.out.println(trainDepartureMessage);
            sc.close(); // closes the scanner

        } catch (IOException e) {
            System.out.println("exception occured: "+e.getMessage());
        }
        return true;
    }
    public String generateResponseArrival(String train, List<String> boggies){
        String allBoggies = "";
        for(String boggie:boggies)
            allBoggies+=boggie+" ";
        return train+" ENGINE "+allBoggies;
    }
}
