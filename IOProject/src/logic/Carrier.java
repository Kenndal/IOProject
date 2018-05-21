package logic;

import java.io.Serializable;

public interface Carrier extends Serializable {
    void printPlace();

    int getTotalSpace();

    void resetCarrier();
}
