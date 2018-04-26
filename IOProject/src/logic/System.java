package logic;

import java.io.*;
import java.util.*;
import java.util.Comparator;
public class System implements Serializable{


    private ArrayList<Container> containers = new ArrayList<Container>();
    private Random random = new Random();
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private FileManagment fileManagment = new FileManagment();

    public System() {
        setShips();
       /*try {
            containers = fileManagment.loadFile(containers);
        } catch (IOException | ClassNotFoundException  e) {
            java.lang.System.out.println("Coś poszło nie tak :O ");
        }*/
    }

    private void setShips() {
        ships.add(new Ship(110, 10));
        ships.add(new Ship(100, 20));
        ships.add(new Ship(150, 14));
        ships.add(new Ship(100, 10));
        ships.add(new Ship(110, 10));
    }

    public void genarateConteiners(int nuberOfConteiners) {
        for (int i = 0; i < nuberOfConteiners; i++) {
            containers.add(new Container(random.nextInt(5) + 5,random.nextInt(5) + 5));
        }
        fileManagment.writeToCSV(containers,nuberOfConteiners);
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
    public ArrayList<Container> sendConteiners() {
        ArrayList<Container> shippedConetiners = new ArrayList<>();
        int howMany = 0;
        //sort
        Collections.sort(this.containers, (Comparator<Container>) (o1, o2) -> {
                String kek1 = Integer.toString(o1.space);
                String kek2 = Integer.toString(o2.space);
                return kek2.compareTo(kek1);
        });

        for(int j=0;j<ships.size();j++) {
            int tempWidth = ships.get(j).width;
            for (int i = 0; i < containers.size(); i++) {
                if (tempWidth > containers.get(i).getWidth()) {
                    shippedConetiners.add(containers.get(i));
                    tempWidth = tempWidth - containers.get(i).getWidth();
                    howMany++;
                }
                if (tempWidth < 5) {
                    break;
                }
            }
            for (int i = 0; i < howMany; i++)
                containers.remove(i);

            howMany = 0;
        }
        return shippedConetiners;
    }


    public void reset(){
        try {
            fileManagment.writeToFile(containers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int space = 0;
        double kek = 0;
        System system = new System();

        system.reset();
        system.genarateConteiners(100);

        java.lang.System.out.println(system.containers.size());

        ArrayList<Container> containers = system.sendConteiners();

        java.lang.System.out.println("--------------");
        java.lang.System.out.println(containers.size());

        for(int i=0;i<containers.size();i++){
            java.lang.System.out.println(containers.get(i).getWidth()+ " " + containers.get(i).getHeight()+ " " + containers.get(i).getSpace());
            space = space + containers.get(i).getSpace();
        }
        java.lang.System.out.println("--------------");
        java.lang.System.out.println(space);
        java.lang.System.out.println("--------------");
        java.lang.System.out.println(system.containers.size());
        java.lang.System.out.println("--------------");

        for(int i=0;i <system.containers.size();i++){
            java.lang.System.out.println(system.containers.get(i).getWidth()+ " " + system.containers.get(i).getHeight()+ " " + system.containers.get(i).getSpace());
        }
    }

}
