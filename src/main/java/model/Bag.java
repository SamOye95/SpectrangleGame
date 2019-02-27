package model;

import exceptions.EmptyBagException;

public interface Bag {


    void resetBag();

    Tile takeTile() throws EmptyBagException;

    int getTheNumberOfRemainingPieces();






}
