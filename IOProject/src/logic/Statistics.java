package logic;

import java.util.ArrayList;

public class Statistics {

    private ArrayList<Integer> numberOfConteiners = new ArrayList<>();
    private ArrayList<Float> percentOfLoad = new ArrayList<>();

    public void calcaluteSize(int number){
        this.numberOfConteiners.add(number);
    }
    public void calculatePercent(float shipTotalSpace, float conteinersSpace){
        this.percentOfLoad.add((conteinersSpace/shipTotalSpace)*100);
    }

    public int getNumberOfConteiners() {
        int totalNumberOfSentConteiners = 0;
        for (Integer conteiners : numberOfConteiners) {
            totalNumberOfSentConteiners = totalNumberOfSentConteiners + conteiners;
        }
        return totalNumberOfSentConteiners;
    }

    public ArrayList<Float> getPercentOfLoad() {
        return percentOfLoad;
    }

}
