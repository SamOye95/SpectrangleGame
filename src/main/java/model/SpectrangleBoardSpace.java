package model;

public class SpectrangleBoardSpace {

    private SpectranglePiece spectranglePiece;
    private SpectrangleBoardSpace left, right, vertical;

    private int multiplier;

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


}
