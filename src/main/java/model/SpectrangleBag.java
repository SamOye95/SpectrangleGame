package model;

import exceptions.EmptyBagException;

public interface SpectrangleBag {

    /**
     * @param spectranglePiece
     */
    void addSpectranglePiece(SpectranglePiece spectranglePiece);

    void resetBag();

    SpectranglePiece takeSpectranglePiece() throws EmptyBagException;

    int getTheNumberOfRemainingPieces();






}
