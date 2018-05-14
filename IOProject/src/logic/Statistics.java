package logic;

import java.util.ArrayList;

public class Statistics {

    private ArrayList<Integer> numberOfContainers = new ArrayList<>();
    private ArrayList<Float> percentOfLoad = new ArrayList<>();

    public void calcaluteSize(int number){
        this.numberOfContainers.add(number);
    }
    public void calculatePercent(float shipTotalSpace, float containersSpace){
        this.percentOfLoad.add((containersSpace/shipTotalSpace)*100);
    }

    public int getNumberOfContainers() {
        int totalNumberOfSentContainers = 0;
        for (Integer conteiners : numberOfContainers) {
            totalNumberOfSentContainers = totalNumberOfSentContainers + conteiners;
        }
        return totalNumberOfSentContainers;
    }

    public ArrayList<Float> getPercentOfLoad() {
        return percentOfLoad;
    }

}
