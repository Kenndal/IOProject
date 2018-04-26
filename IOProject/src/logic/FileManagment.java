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
            fileWriter = new FileWriter("Conteiners.csv", true);
            for (int i = 0; i < nuberOfItems; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(containers.get(i).getTime()); // Conteiner time
                stringBuilder.append(";");
                stringBuilder.append(containers.get(i).getSpace()); // Space'
                stringBuilder.append(";");
                stringBuilder.append(containers.get(i).getWidth()); // Width
                stringBuilder.append(";");
                stringBuilder.append(containers.get(i).getHeight()); // Height
                stringBuilder.append("\n");
                fileWriter.write(stringBuilder.toString());
            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /**
         * Write conteiners to file Conteiners.bin
         * @param containers
         */

    public void writeToFile(ArrayList<Container> containers) throws IOException {

        ObjectOutputStream toSave = null;
        try {
            FileOutputStream outputStream = new FileOutputStream("Conteiners.bin");
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
     * @param containers
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ArrayList<Container> loadFile(ArrayList<Container> containers) throws IOException, ClassNotFoundException {
        ObjectInputStream toLoad = null;
        try {
            FileInputStream input = new FileInputStream("Conteiners.bin");
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
}
