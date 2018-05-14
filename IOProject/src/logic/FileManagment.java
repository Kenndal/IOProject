package logic;
import java.io.*;
import java.util.ArrayList;

public class FileManagment {


    /**
     * Write informations about conteiners to CSV file Conteiners.csv
     *
     * @param containers
     * @param nuberOfItems
     */
    public void writeToCSV(ArrayList<Container> containers, int nuberOfItems) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("resources/Conteiners.csv", true);
            for (int i = 0; i < nuberOfItems; i++) {
                StringBuilder stringBuilder = lineConteiners(containers.get(i));
                fileWriter.write(stringBuilder.toString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rewriteToCSV(ArrayList<Container> containers) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("resources/Conteiners.csv", false);
            for (Container container : containers) {
                StringBuilder stringBuilder = lineConteiners(container);
                fileWriter.write(stringBuilder.toString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder lineConteiners(Container container){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(container.getTime()); // Conteiner time
            stringBuilder.append(";");
            stringBuilder.append(container.getSpace()); // Space'
            stringBuilder.append(";");
            stringBuilder.append(container.getWidth()); // Width
            stringBuilder.append(";");
            stringBuilder.append(container.getHeight()); // Height
            stringBuilder.append("\n");
        return stringBuilder;
    }

        /**
         * Write conteiners to file Conteiners.bin
         * @param containers
         */

    public void writeToFile(ArrayList<Container> containers) throws IOException {

        ObjectOutputStream toSave = null;
        try {
            FileOutputStream outputStream = new FileOutputStream("resources/Conteiners.bin");
            toSave = new ObjectOutputStream(outputStream);
            toSave.writeObject(containers);
        } catch (IOException ex) {
            java.lang.System.out.println("Blad w zapisie do pliku 'Conteiners.bin'");
        } finally {
            if (toSave != null)
                toSave.close();
        }
    }


    /**
     * Load conteiners from File Conteiners.bin
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Container> loadFile() throws IOException, ClassNotFoundException {
        ArrayList<Container> containers = new ArrayList<>();
        ObjectInputStream toLoad = null;
        try {
            FileInputStream input = new FileInputStream("resources/Conteiners.bin");
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
     * Generate ending raport and write to CSV
     * @param statistics
     */
    void generateRaport(Statistics statistics){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("resources/Raport.csv", true);

            String stringBuilder = String.valueOf(statistics.getPercentOfLoad().get(0)) +
                    ";" +
                    statistics.getPercentOfLoad().get(1) +
                    ";" +
                    statistics.getPercentOfLoad().get(2) +
                    ";" +
                    statistics.getPercentOfLoad().get(3) +
                    ";" +
                    statistics.getPercentOfLoad().get(4) +
                    ";" +
                    statistics.getNumberOfContainers() +
                    "\n";
            fileWriter.write(stringBuilder);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
