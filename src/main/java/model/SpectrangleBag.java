package model;

import exceptions.EmptyBagException;

public interface SpectrangleBag {


    void resetBag();

    SpectranglePiece takeSpectranglePiece() throws EmptyBagException;

    int getTheNumberOfRemainingPieces();






}
