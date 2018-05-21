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

    /**
     * Sort containers before pack to ship.
     */

    void sortConteiners(){
        //sort
        Collections.sort(chosenContainers, (Comparator<Container>) (o1, o2) -> {
            String spaceString1 = Float.toString(o1.space);
            String spaceString2 = Float.toString(o2.space);
            return spaceString2.compareTo(spaceString1);
        });
    }

    /**
     *  Main algorithm to pack containers to ship.
     *
     * @param carriers all available ships
     * @param allContainers
     * @return actual list of all containers
     */


    ArrayList<Container> packConteiners(ArrayList<Carrier> carriers, ArrayList<Container> allContainers) {
        for (Carrier ship : carriers) {
            PackContainer packContainer = new PackContainer();
            packContainer.setShip((Ship) ship);
            while (isAll) {
                if(chosenContainers.size() != 0) {
                    for (int i = 0; i < chosenContainers.size(); i++) {
                        // find space to pack container to ship
                        if (packContainer.checkSpace(chosenContainers.get(i))) {
                            // sent container
                            shippedContainers.add(chosenContainers.get(i));
                            // statistic parameters
                            temporaryContainersSpace = temporaryContainersSpace + chosenContainers.get(i).getSpace();
                            temporaryNumberOf++;
                            // remove send container
                            findAndRemove(chosenContainers.get(i), allContainers);
                            // remove packed container from chosen list
                            chosenContainers.remove(i);

                            break;
                        }
                        if (i == chosenContainers.size() - 1) {
                            isAll = false; // do until last container
                        }
                    }
                }else isAll = false;
            }
            // generate statistics from ship load
            statistics.calcaluteSize(temporaryNumberOf);
            statistics.calculatePercent(ship.getTotalSpace(), temporaryContainersSpace);

            isAll = true;
            temporaryNumberOf = 0;
            temporaryContainersSpace = 0;

            ship.resetCarrier(); // reset ship after pack
        }
        return allContainers;
    }

    /**
     * If algorithm fing space to pack container is removed from main list of containers(All containers)
     * @param container
     * @param containers
     */
    private void findAndRemove(Container container,ArrayList<Container> containers){
        for(int i=0;i<containers.size();i++){
            if(containers.get(i) == container){
                containers.remove(i);
                break;
            }
        }
    }

    Statistics getStatistics() {
        return statistics;
    }

    ArrayList<Container> getShippedContainers() {
        return shippedContainers;
    }
}
