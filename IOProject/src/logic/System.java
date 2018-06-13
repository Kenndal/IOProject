package logic;


import logic.algorithm.Algorithm;

import java.io.*;
import java.util.*;

public class System implements Serializable{


    private ArrayList<Container> containers = new ArrayList<>();
    private Random random = new Random();
    private ArrayList<Ship> ships = new ArrayList<>();
    private FileManagement fileManagement = new FileManagement(); // manage in program files
    private LoaderCSV loaderCSV = new LoaderCSV(); // load external csv files
    private Observer observer; // observer to updates text fields in GUI

    public System() {
        setShips();
    }

    public FileManagement getFileManagement() {
        return fileManagement;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public LoaderCSV getLoaderCSV() {
        return loaderCSV;
    }

    /**
     * Static adding 5 ships
     */

    private void setShips() {
        ships.add(new Ship(13, 10));
        ships.add(new Ship(17, 14));
        ships.add(new Ship(11, 11));
        ships.add(new Ship(10, 17));
        ships.add(new Ship(12, 14));
    }

    public void loadExternalData(String path){
        reset();
        loaderCSV.load(path);
        loaderCSV.sortContainers();
        containers = loaderCSV.getContainers();
        try {
            fileManagement.writeToFile(containers);
            fileManagement.writeToCSV(containers,containers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        observer.inform(); // inform GUI about changes
    }

    /**
     *  Function to generate N containers
     * @param numberOfContainers
     */
    public void generateContainers(int numberOfContainers){
            for (int i = 0; i < numberOfContainers; i++) {
                containers.add(new Container(random.nextInt(5) + 5, random.nextInt(5) + 5));
            }
            fileManagement.writeToCSV(containers, numberOfContainers);
            try {
                fileManagement.writeToFile(containers);
            } catch (IOException e) {
                java.lang.System.out.println("Debug KEK ");
            }
    }


    /**
     * Send containers function
     * Algorithm to pack as many as it's possible
     * */
    public Statistics sendContainers() {
        Algorithm algorithm = new Algorithm();
        // Choose containers
        if (this.containers.size() >= 100) {
            for (int i = 0; i < 100; i++) {
                algorithm.getChosenContainers().add(this.containers.get(i));
            }

        } else {
            for (Container container : this.containers) {
                algorithm.getChosenContainers().add(container);
            }
        }
        // Sort containers before packing
        algorithm.sortContainers();


        this.containers = algorithm.packContainers(this.ships, this.containers);

        // update data files
        fileManagement.rewriteToCSV(this.containers);
        fileManagement.generateReport(algorithm.getStatistics());
        // return statistics to show them
        return algorithm.getStatistics();
    }

    /**
     * reset all files in application
     */

    public void reset(){
        try {
            this.containers.clear();
            fileManagement.writeToFile(containers);
            fileManagement.rewriteToCSV(containers);
            observer.inform(); // inform GUI about changes
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  make subscribe to class, who observe this class
     * @param observer
     */
    public void subscribe(Observer observer){
        this.observer = observer;
    }

    public static void main(String[] args) {
        System system = new System();
        system.loadExternalData("resources/DataInputGroupPT1440.csv");
        Statistics statistics =  new Statistics();
        while(system.getContainers().size() != 0 ){
            statistics = system.sendContainers();
            system.getFileManagement().generateReport(statistics);
        }

        // send 255 ships
    }
}
