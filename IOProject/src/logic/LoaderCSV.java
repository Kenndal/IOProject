package logic;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class which is used to load external files with containers.
 * Load from CSV files.
 */
public class LoaderCSV {

    private ArrayList<Container> containers = new ArrayList<>();
    private ArrayList<String> errors = new ArrayList<>(); // list of information where errors are
    /**
     * Load data from CSV file
     * @param path
     */
    void load(String path){
        resetErrors(); // reset errors before new load
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null){
                String[] temp = line.split(cvsSplitBy);
                temp[2] = temp[2].replace("[","");
                temp[3] = temp[3].replace("]","");
                Container container = checkContainer(temp[0],temp[2],temp[3], temp[1]);
                if(container != null) // null from check containers is not add to ArrayList
                    containers.add(container);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Check if the values in csv file are correct
     * @param time
     * @param weight
     * @param height
     * @param numberOf
     * @return container if everything is ok or null where data values are not ok
     */
    private Container checkContainer(String time,String weight, String height, String numberOf){
        int tempWeight;
        int tempHeight;
        long tempTime;
        try {
            if(!Objects.equals(time, "") && !Objects.equals(weight, "") && !Objects.equals(height, "")) {
                tempTime = Long.valueOf(time);
                tempWeight = Integer.valueOf(weight);
                tempHeight = Integer.valueOf(height);
                return new Container(tempTime, tempWeight, tempHeight);
            }else {
                errors.add("Kontener " + numberOf + " ma niewłaściwy format danych");
                return null;
            }

        }catch (RuntimeException e){
            errors.add("Kontener " + numberOf + " ma niewłaściwy format danych");
            return null;
        }

    }

    ArrayList<Container> getContainers() {
        return containers;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    /**
     *  Reset errors
     */
    private void resetErrors(){
        errors.clear();
    }
}
