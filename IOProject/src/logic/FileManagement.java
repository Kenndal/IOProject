package logic;
import java.io.*;
import java.util.ArrayList;

public class FileManagement {


    /**
     * Write information about containers to CSV file Containers.csv
     *
     * @param containers
     * @param numberOfItems
     */
    void writeToCSV(ArrayList<Container> containers, int numberOfItems) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("resources/Containers.csv", true);
            for (int i = 0; i < numberOfItems; i++) {
                StringBuilder stringBuilder = lineContainers(containers.get(i));
                fileWriter.write(stringBuilder.toString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void rewriteToCSV(ArrayList<Container> containers) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("resources/Containers.csv", false);
            for (Container container : containers) {
                StringBuilder stringBuilder = lineContainers(container);
                fileWriter.write(stringBuilder.toString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder lineContainers(Container container){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(container.getTime()); // Conteiner time
            stringBuilder.append(",");
            stringBuilder.append(container.getSpace()); // Space'
            stringBuilder.append(",");
            stringBuilder.append(container.getWidth()); // Width
            stringBuilder.append(",");
            stringBuilder.append(container.getHeight()); // Height
            stringBuilder.append("\n");
        return stringBuilder;
    }

        /**
         * Write containers to file Containers.bin
         * @param containers
         */

    public void writeToFile(ArrayList<Container> containers) throws IOException {

        ObjectOutputStream toSave = null;
        try {
            FileOutputStream outputStream = new FileOutputStream("resources/Containers.bin");
            toSave = new ObjectOutputStream(outputStream);
            toSave.writeObject(containers);
        } catch (IOException ex) {
            java.lang.System.out.println("Blad w zapisie do pliku 'Containers.bin'");
        } finally {
            if (toSave != null)
                toSave.close();
        }
    }


    /**
     * Load containers from File Containers.bin
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Container> loadFile() throws IOException, ClassNotFoundException {
        ArrayList<Container> containers = new ArrayList<>();
        ObjectInputStream toLoad = null;
        try {
            FileInputStream input = new FileInputStream("resources/Containers.bin");
            toLoad = new ObjectInputStream(input);
            containers = (ArrayList<Container>) toLoad.readObject();
        } catch (IOException ex) {
            java.lang.System.out.println("Koniec pliku");
        } finally {
            if (toLoad != null)
                toLoad.close();
        }
        return containers;
    }

    /**
     * Generate ending report and write to CSV
     * @param statistics
     */
    void generateReport(Statistics statistics){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("resources/Raport.csv", true);

            String stringBuilder = String.valueOf(statistics.getPercentOfLoad().get(0)) +
                    "," +
                    statistics.getPercentOfLoad().get(1) +
                    "," +
                    statistics.getPercentOfLoad().get(2) +
                    "," +
                    statistics.getPercentOfLoad().get(3) +
                    "," +
                    statistics.getPercentOfLoad().get(4) +
                    "," +
                    statistics.getNumberOfContainers() +
                    "\n";
            fileWriter.write(stringBuilder);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
