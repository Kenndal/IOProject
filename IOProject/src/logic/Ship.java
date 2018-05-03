package logic;

import java.lang.System;

public class Ship extends Dimentions{

    private int totalSpace;


    public Ship(int width, int height) {
        this.width = width;
        this.height = height;
        this.space = width*height;
        this.totalSpace = width*height;
        place = new int[height][width];
        getPlace(width,height);
    }


    boolean checkSpace(Container container){
        boolean checkOutOfBound = true;
        boolean checkIsOne = true;
        int k;
        int positionX;
        int positionY ;
        for(positionX = 0;positionX<height;positionX++) {
            for (positionY = 0; positionY < width; positionY++) {
                if(place[positionX][positionY] == 0) {
                    k = 0;
                    while (k < container.getHeight()) {
                        if ((positionX + k) >= height) {
                            checkOutOfBound = true;
                            break;
                        }
                        if (place[positionX + k][positionY] != 0) {
                            checkIsOne = true;
                            break;
                        }

                        k++;
                        checkIsOne = false;
                        checkOutOfBound = false;
                    }
                    k = 0;

                    if (!checkOutOfBound && !checkIsOne){
                        while (k < container.getWidth()) {
                            if ((positionY + k) >= width) {
                                checkOutOfBound = true;
                                break;
                            }
                            if (place[positionX][positionY + k] != 0) {
                                checkIsOne = true;
                                break;
                            }
                            k++;
                            checkIsOne = false;
                            checkOutOfBound = false;
                        }
                        if(!checkOutOfBound && !checkIsOne) {
                            fillSpace(container, positionX, positionY);
                            changeSpace(container);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private void fillSpace(Container container, int positionX, int positionY){

        for(int i = positionX;i<positionX+container.getHeight();i++){
            for(int j = positionY;j<positionY+container.getWidth();j++){
                place[i][j] = 1;
            }
        }

    }


    public void printPlace(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                System.out.print(place[i][j]);
            }
            System.out.print("\n");
        }
    }

    private void changeSpace(Container container){
        this.space = this.space - container.getSpace();
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void resetShip(){
        space = width*height;
        getPlace(width,height);
    }
}
