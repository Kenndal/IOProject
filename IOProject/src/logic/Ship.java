package logic;

import java.lang.System;

public class Ship extends Dimensions {

    private int totalSpace; // maximal field value doesn't change during packing


    public Ship(int width, int height) {
        super(width, height);
        this.totalSpace = width*height;
        place = new int[height][width];
        setPlace(width,height); // must be here, because of null pointer in Dimensions class
    }


    /**
     * Print space in the ship
     */
    public void printPlace(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                System.out.print(place[i][j]);
            }
            System.out.print("\n");
        }
    }

    /**
     * Get maximal ship's space
     * @return total space of ship
     */

    public int getTotalSpace() {
        return totalSpace;
    }

    /**
     *  Reset using space in the ship
     *  Used before every containers pack
     */
    public void resetCarrier(){
        space = width*height;
        setPlace(width,height);
    }
}
