package model;


/*
 * This class implements a board space inside a board of Spectrangle game.
 */
public class BoardSpace {

    private Tile spectranglePiece;
    private BoardSpace left, right, vertical;

    private int multiplier;
    private TileOrientation orientation;
    private int index;


    public BoardSpace() {
        this.multiplier = 1;
    }

    /*
     * Returns piece
     */
    public Tile getSpectranglePiece() {
        return spectranglePiece;
    }

    /*
     * Sets piece
     */
    public void setSpectranglePiece(Tile spectranglePiece) {
        this.spectranglePiece = spectranglePiece;
    }

    /*
     * Returns  bonus point of the space
     */
    public int getMultiplier() {
        return this.multiplier;
    }

    /*
     * sets the bonus point of the space
     *
     * @param multiplier
     */

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    /*
     * Returns left board space
     * @return
     */
    public BoardSpace getLeft() {
        return left;
    }

    public void setLeft(BoardSpace left) {
        this.left = left;
    }

    /*
     * Returns right board space
     */
    public BoardSpace getRight() {
        return right;
    }

    public void setRight(BoardSpace right) {
        this.right = right;
    }

    /*
     * Returns vertical board space
     */
    public BoardSpace getVertical() {
        return vertical;
    }

    public void setVertical(BoardSpace vertical) {
        this.vertical = vertical;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /*
     *
     * @return orientation
     */

    public TileOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(TileOrientation orientation) {
        this.orientation = orientation;
    }

}
