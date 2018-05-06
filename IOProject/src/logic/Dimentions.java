package logic;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

abstract class Dimentions implements Serializable {

    float space;
    int width;
    int height;
    int[][] place;

    Dimentions(int width, int height) {
        this.width = width;
        this.height = height;
        this.space = width*height;
    }

    float getSpace() {
        return space;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    int[][] getPlace(int width, int height){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                place[i][j] = 0;
            }
        }
        return place;
    }

}