package com.example.geektrust.service;

import com.example.geektrust.constants.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainMergeService {
    List<String> trainAFromHyd;
    List<String> trainBFromHyd;
    List<String> mergedTrainFromHyd;
    private Constants constants;
    public TrainMergeService() {
        this.trainAFromHyd = new ArrayList<>();
        this.trainBFromHyd = new ArrayList<>();
        this.mergedTrainFromHyd = new ArrayList<>();
        this.constants = new Constants();
    }
    public boolean addToTrain(String boggie,boolean trainAFlag){
        List<String> trainAStationsToHyd = this.constants.getTrainAStationsToHyd();
        List<String> trainBStationsToHyd = this.constants.getTrainBStationsToHyd();
        if(!trainAStationsToHyd.contains(boggie) && !trainBStationsToHyd.contains(boggie) && trainAFlag)
            trainAFromHyd.add(boggie);
        if(!trainAStationsToHyd.contains(boggie) && !trainBStationsToHyd.contains(boggie) && !trainAFlag)
            trainBFromHyd.add(boggie);

        return true;
    }
    public List<String> getArrivalList(boolean trainAFlag){
        return trainAFlag ? this.trainAFromHyd:trainBFromHyd;
    }
    public List<String> getDeparatureList(){
        Map<String, Integer> fromHydDistance = this.constants.getFromHydDistance();
        for(String boggie:trainAFromHyd)
            mergedTrainFromHyd.add(boggie);
        for(String boggie:trainBFromHyd)
            mergedTrainFromHyd.add(boggie);
        this.mergedTrainFromHyd.sort((a,b)->fromHydDistance.get(b)-fromHydDistance.get(a));
        for(int i=mergedTrainFromHyd.size()-1;i>=0;i--){
            if(fromHydDistance.get(mergedTrainFromHyd.get(i))==0)
                mergedTrainFromHyd.remove(i);
        }
        return mergedTrainFromHyd;
    }

}
