package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Algorithm {


    private ArrayList<Container> shippedContainers = new ArrayList<>();
    private ArrayList<Container> chosenContainers = new ArrayList<>();
    private boolean isAll = true;
    private float temporaryContainersSpace;
    private int temporaryNumberOf;
    private Statistics statistics = new Statistics();

    ArrayList<Container> getChosenContainers() {
        return chosenContainers;
    }

    void sortConteiners(){
        //sort
        Collections.sort(chosenContainers, (Comparator<Container>) (o1, o2) -> {
            String spaceString1 = Float.toString(o1.space);
            String spaceString2 = Float.toString(o2.space);
            return spaceString2.compareTo(spaceString1);
        });
    }

    ArrayList<Container> packConteiners(ArrayList<Ship> ships, ArrayList<Container> allContainers) {
        for (Ship ship : ships) {
            while (isAll) {
                if(chosenContainers.size() != 0) {
                    for (int i = 0; i < chosenContainers.size(); i++) {
                        if (ship.checkSpace(chosenContainers.get(i))) {
                            // sent conteiner
                            shippedContainers.add(chosenContainers.get(i));
                            // statistic parameters
                            temporaryContainersSpace = temporaryContainersSpace + chosenContainers.get(i).getSpace();
                            temporaryNumberOf++;
                            // remove send conteiner
                            findAndRemove(chosenContainers.get(i), allContainers);
                            chosenContainers.remove(i);

                            break;
                        }
                        if (i == chosenContainers.size() - 1) {
                            isAll = false;
                        }
                    }
                }else isAll = false;
            }
            statistics.calcaluteSize(temporaryNumberOf);
            statistics.calculatePercent(ship.getTotalSpace(), temporaryContainersSpace);

            isAll = true;
            temporaryNumberOf = 0;
            temporaryContainersSpace = 0;

            ship.resetShip();
        }
        return allContainers;
    }

    private void findAndRemove(Container container,ArrayList<Container> containers){
        for(int i=0;i<containers.size();i++){
            if(containers.get(i) == container){
                containers.remove(i);
                break;
            }
        }
    }

    public Statistics getStatistics() {
        return statistics;
    }

    ArrayList<Container> getShippedContainers() {
        return shippedContainers;
    }
}
