package com.example.geektrust.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {
    private Map<String, Integer> fromHydDistance;
    private List<String> trainAStationsToHyd;
    private List<String> trainBStationsToHyd;
    public Constants() {
        this.fromHydDistance = new HashMap<>();
        this.trainAStationsToHyd = new ArrayList<>();
        this.trainBStationsToHyd = new ArrayList<>();
        initializeDistanceMap();
    }
    private void initializeDistanceMap(){
        this.fromHydDistance.put("HYB",0);
        this.fromHydDistance.put("NGP",400);
        this.fromHydDistance.put("ITJ",700);
        this.fromHydDistance.put("BPL",800);
        this.fromHydDistance.put("AGA",1300);
        this.fromHydDistance.put("NDL",1500);
        this.fromHydDistance.put("PTA",1800);
        this.fromHydDistance.put("NJP",2200);
        this.fromHydDistance.put("GHY",2700);

        this.trainAStationsToHyd.add("CHN");
        this.trainAStationsToHyd.add("SLM");
        this.trainAStationsToHyd.add("BLR");
        this.trainAStationsToHyd.add("KRN");

        this.trainBStationsToHyd.add("TVC");
        this.trainBStationsToHyd.add("SRR");
        this.trainBStationsToHyd.add("MAQ");
        this.trainBStationsToHyd.add("MAO");
        this.trainBStationsToHyd.add("PNE");
    }

    public Map<String, Integer> getFromHydDistance() {
        return fromHydDistance;
    }

    public List<String> getTrainAStationsToHyd() {
        return trainAStationsToHyd;
    }

    public List<String> getTrainBStationsToHyd() {
        return trainBStationsToHyd;
    }

}
