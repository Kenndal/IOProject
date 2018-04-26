package logic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;

public class Container extends Dimentions implements Serializable{

    private long time;


    public Container(int width, int height) {
        Date time =  new Date();
        Timestamp timestamp = new Timestamp(time.getTime());
        this.time = timestamp.getTime();
        this.width = width;
        this.height = height;
        this.space = width*height;

    }

    public long getTime() {
        return time;
    }

}
