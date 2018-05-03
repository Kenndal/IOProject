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

    public float getSpace() {
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


    IntegerProperty getWidthProperty(){
        return new SimpleIntegerProperty(width);
    }

    IntegerProperty getHightProprerty(){
        return new SimpleIntegerProperty(height);
    }

    FloatProperty getSpaceProperty(){
        return new SimpleFloatProperty(space);
    }
}