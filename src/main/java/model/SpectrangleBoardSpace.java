package model;

public class SpectrangleBoardSpace {

    private SpectranglePiece spectranglePiece;
    private SpectrangleBoardSpace left, right, vertical;

    private int multiplier;
    private SpectranglePieceOrientation orientation;
    private int index;


    public SpectrangleBoardSpace() {

    }

    /**
     * Returns piece
     */
    public SpectranglePiece getSpectranglePiece() {
        return spectranglePiece;
    }

    /**
     * Sets piece
     */
    public void setSpectranglePiece(SpectranglePiece spectranglePiece) {
        this.spectranglePiece = spectranglePiece;
    }

    /**
     * Returns  bonus point of the space
     */
    public int getMultiplier() {
        return this.multiplier;
    }

    /**
     * sets the bonus point of the space
     *
     * @param multiplier
     */

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Returns left board space
     * @return
     */
    public SpectrangleBoardSpace getLeft() {
        return left;
    }

    /**
     * Returns right board space
     */
    public SpectrangleBoardSpace getRight() {
        return right;
    }

    /**
     * Returns vertical board space
     */
    public SpectrangleBoardSpace getVertical() {
        return vertical;
    }

    public void setVertical(SpectrangleBoardSpace vertical) {
        this.vertical = vertical;
    }

    public void setLeft(SpectrangleBoardSpace left) {
        this.left = left;
    }

    public void setRight(SpectrangleBoardSpace right) {
        this.right = right;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return orientation
     */

    public SpectranglePieceOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(SpectranglePieceOrientation orientation) {
        this.orientation = orientation;
    }

}
