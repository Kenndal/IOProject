package logic;

import java.util.ArrayList;

public class Statistics {

    private ArrayList<Integer> numberOfContainers = new ArrayList<>();
    private ArrayList<Float> percentOfLoad = new ArrayList<>();

    /**
     *  adding number of containers, which was packed to ship
     * @param number
     */
    public void calcaluteSize(int number){
        this.numberOfContainers.add(number);
    }

    /**
     * calculate percent of used space to total space
     * @param shipTotalSpace
     * @param containersSpace
     */
    public void calculatePercent(float shipTotalSpace, float containersSpace){
        this.percentOfLoad.add((containersSpace/shipTotalSpace)*100);
    }

    /**
     *  all number of packed containers in send
     * @return
     */
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
