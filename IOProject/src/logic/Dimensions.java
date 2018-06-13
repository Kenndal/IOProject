package logic;

import java.io.Serializable;

/**
 * Abstract class with main parameters of ships and containers
 */
abstract class Dimensions implements Serializable {

    float space;
    int width;
    int height;
    int[][] place;

    Dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        this.space = width*height;
    }

    public float getSpace() {
        return space;
    }

    public void setSpace(float space) {
        this.space = space;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    void setPlace(int width, int height){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                place[i][j] = 0;
            }
        }
    }

    public int[][] getPlace() {
        return place;
    }
}