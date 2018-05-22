package logic;


import logic.algorithm.Algorithm;

import java.io.*;
import java.util.*;
public class System implements Serializable{


    private ArrayList<Container> containers = new ArrayList<Container>();
    private Random random = new Random();
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private FileManagment fileManagment = new FileManagment();


    public System() {
        setShips();
    }

    public FileManagment getFileManagment() {
        return fileManagment;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Static adding 5 ships
     */

    private void setShips() {
        ships.add(new Ship(55, 20));
        ships.add(new Ship(40, 25));
        ships.add(new Ship(50, 20));
        ships.add(new Ship(60, 10));
        ships.add(new Ship(30, 30));
    }

    /**
     *  Function to generate N containers
     * @param numberOfContainers
     */
    public void genarateConteiners(int numberOfContainers){
            for (int i = 0; i < numberOfContainers; i++) {
                containers.add(new Container(random.nextInt(5) + 5, random.nextInt(5) + 5));
            }
            fileManagment.writeToCSV(containers, numberOfContainers);
            try {
                fileManagment.writeToFile(containers);
            } catch (IOException e) {
                java.lang.System.out.println("Debug KEK ");
            }
    }


    /**
     * Send conteiners function
     * Algorithm to pack as many as it's possible
     * */
    public Statistics sendContainers() {
        Algorithm algorithm = new Algorithm();
        // Choose contaners
        if (this.containers.size() >= 100) {
            for (int i = 0; i < 100; i++) {
                algorithm.getChosenContainers().add(this.containers.get(i));
            }

        } else {
            for (int i = 0; i < this.containers.size(); i++) {
                algorithm.getChosenContainers().add(this.containers.get(i));
            }
        }
        // Sort containers before packing
        algorithm.sortConteiners();


        this.containers = algorithm.packConteiners(this.ships, this.containers);

        // update data files
        fileManagment.rewriteToCSV(this.containers);
        fileManagment.generateRaport(algorithm.getStatistics());
        // return statistics to show them
        return algorithm.getStatistics();
    }

    /**
     * reset all files in application
     */

    private void reset(){
        try {
            fileManagment.writeToFile(containers);
            fileManagment.rewriteToCSV(containers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        // reset system
        System system = new System();
        system.reset();
    }

}
