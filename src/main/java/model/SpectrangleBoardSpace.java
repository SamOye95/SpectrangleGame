package model;

public class SpectrangleBoardSpace {

    private SpectranglePiece spectranglePiece;
    private SpectrangleBoardSpace left, right, vertical;

    private int multiplier;
    private SpectranglePieceOrientation orientation;
    private int index;


    public SpectrangleBoardSpace() {

    }

    public SpectranglePiece getSpectranglePiece() {
        return spectranglePiece;
    }

    public void setSpectranglePiece(SpectranglePiece spectranglePiece) {
        this.spectranglePiece = spectranglePiece;
    }

    public int getMultiplier() {
        return this.multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public SpectrangleBoardSpace getLeft() {
        return left;
    }

    public SpectrangleBoardSpace getRight() {
        return right;
    }

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

    public SpectranglePieceOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(SpectranglePieceOrientation orientation) {
        this.orientation = orientation;
    }

}
