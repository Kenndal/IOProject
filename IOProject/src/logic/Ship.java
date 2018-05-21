package logic;

import java.lang.System;

public class Ship extends Dimentions implements Carrier {

    private int totalSpace; // maximal field value


    public Ship(int width, int height) {
        super(width, height);
        this.totalSpace = width*height;
        place = new int[height][width];
        getPlace(width,height);
    }

    @Override
    public void printPlace(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                System.out.print(place[i][j]);
            }
            System.out.print("\n");
        }
    }

    @Override
    public int getTotalSpace() {
        return totalSpace;
    }

    @Override
    public void resetCarrier(){
        space = width*height;
        getPlace(width,height);
    }
}
