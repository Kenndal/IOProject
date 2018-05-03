package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Algorithm {


    private ArrayList<Container> shippedConetiners = new ArrayList<>();
    private ArrayList<Container> chosenConteiners = new ArrayList<>();
    private boolean isAll = true;
    private float temporaryConteinersSpace;
    private int temporaryNumberOf;
    private Statistics statistics = new Statistics();

    ArrayList<Container> getChosenConteiners() {
        return chosenConteiners;
    }

    void sortConteiners(){
        //sort
        Collections.sort(chosenConteiners, (Comparator<Container>) (o1, o2) -> {
            String spaceString1 = Float.toString(o1.space);
            String spaceString2 = Float.toString(o2.space);
            return spaceString2.compareTo(spaceString1);
        });
    }

    ArrayList<Container> packConteiners(ArrayList<Ship> ships, ArrayList<Container> allConteiners) {
        for (Ship ship : ships) {
            while (isAll) {
                if(chosenConteiners.size() != 0) {
                    for (int i = 0; i < chosenConteiners.size(); i++) {
                        if (ship.checkSpace(chosenConteiners.get(i))) {
                            // sent conteiner
                            shippedConetiners.add(chosenConteiners.get(i));
                            // statistic parameters
                            temporaryConteinersSpace = temporaryConteinersSpace + chosenConteiners.get(i).getSpace();
                            temporaryNumberOf++;
                            // remove send conteiner
                            findAndRemove(chosenConteiners.get(i), allConteiners);
                            chosenConteiners.remove(i);

                            break;
                        }
                        if (i == chosenConteiners.size() - 1) {
                            isAll = false;
                        }
                    }
                }else isAll = false;
            }
            statistics.calcaluteSize(temporaryNumberOf);
            statistics.calculatePercent(ship.getTotalSpace(),temporaryConteinersSpace);

            isAll = true;
            temporaryNumberOf = 0;
            temporaryConteinersSpace = 0;

            ship.resetShip();
        }
        return allConteiners;
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

    ArrayList<Container> getShippedConetiners() {
        return shippedConetiners;
    }
}
