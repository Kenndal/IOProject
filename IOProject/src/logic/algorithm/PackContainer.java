package logic.algorithm;

import logic.Container;
import logic.Ship;

class PackContainer {

    private Ship ship;

    PackContainer(Ship ship) {
        this.ship = ship;
    }

    /**
     *  Find space in Ship to pack container
     * @param container
     * @return
     */
    boolean checkSpace(Container container){
        boolean checkOutOfBound = true;
        boolean checkIsOne = true;
        int k;
        int positionX;
        int positionY ;
        // find potential space
        for(positionX = 0; positionX< ship.getHeight(); positionX++) {
            for (positionY = 0; positionY < ship.getWidth(); positionY++) {
                if(ship.getPlace()[positionX][positionY] == 0) {
                    k = 0;
                    // look for available space in height
                    while (k < container.getHeight()) {
                        // check height out of bound
                        if ((positionX + k) >= ship.getHeight()) {
                            checkOutOfBound = true;
                            break;
                        }
                        if (ship.getPlace()[positionX + k][positionY] != 0) {
                            checkIsOne = true;
                            break;
                        }

                        k++;
                        checkIsOne = false;
                        checkOutOfBound = false;
                    }
                    k = 0;

                    if (!checkOutOfBound && !checkIsOne){
                        // look for available space in width
                        while (k < container.getWidth()) {
                            // check width out of bound
                            if ((positionY + k) >= ship.getWidth()) {
                                checkOutOfBound = true;
                                break;
                            }
                            if (ship.getPlace()[positionX][positionY + k] != 0) {
                                checkIsOne = true;
                                break;
                            }
                            k++;
                            checkIsOne = false;
                            checkOutOfBound = false;
                        }
                        // if everything is ok fill and change ship's space
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

    /**
     *  fill ship's space after space check
     * @param container
     * @param positionX
     * @param positionY
     */
    private void fillSpace(Container container, int positionX, int positionY){

        for(int i = positionX;i<positionX+container.getHeight();i++){
            for(int j = positionY;j<positionY+container.getWidth();j++){
                ship.getPlace()[i][j] = 1;
            }
        }

    }

    /**
     *  change field value after space check
     * @param container
     */
    private void changeSpace(Container container){
        ship.setSpace(ship.getSpace() - container.getSpace());
    }

}
