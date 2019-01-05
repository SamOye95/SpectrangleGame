package model;

import java.awt.*;


public class SpectrangleValidMoveElement {

    // The location on the board that this piece can go
    private Point boardLoc;

    // The array of pieces that have been fliped/rotated that fit on this space.
    private SpectranglePiece[] orientations;

    /**
     * Constructor which creates the element
     *
     * @param loc          The board locations
     * @param orientations The orientations for this location
     */
    public SpectrangleValidMoveElement(Point loc, SpectranglePiece[] orientations) {
        this.boardLoc = loc;
        this.orientations = orientations;
    }

    /**
     * Determine if this board location matches the one provided.
     */
    public boolean isSameSpace(Point testLoc) {
        return (boardLoc.x == testLoc.x) && (boardLoc.y == testLoc.y);
    }

    /**
     * Return the list of orientations
     */
    public SpectranglePiece[] getOrientations() {
        return orientations;
    }
}
