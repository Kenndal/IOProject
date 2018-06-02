package logic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Container extends Dimensions implements Serializable{

    private long time; // specific time of generate container


    Container(int width, int height) {
        super(width, height);
        Date time =  new Date();
        Timestamp timestamp = new Timestamp(time.getTime());
        this.time = timestamp.getTime();
        place = new int[height][width];
        setPlace(width,height); // must be here, because of null pointer in Dimensions class
    }

    Container(long time, int width, int height) {
        super(width, height);
        this.time = time;
        place = new int[height][width];
        setPlace(width,height); // must be here, because of null pointer in Dimensions class
    }

    long getTime() {
        return time;
    }

}
