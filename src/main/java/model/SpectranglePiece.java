package model;

import java.util.ArrayList;
import java.util.List;

public class SpectranglePiece {


    public List<String> drawingPiece;

    private SpectranglePieceColor colorVertical, colorLeft, colorRight;
    private SpectranglePieceOrientation orientation;
    private Integer points;


    /**
     * Constructs the spectrangle piece
     *
     * @param orientation
     * @param colorLeft
     * @param colorRight
     * @param colorVertical
     * @param points
     */
    public SpectranglePiece(SpectranglePieceOrientation orientation, SpectranglePieceColor colorLeft, SpectranglePieceColor colorRight, SpectranglePieceColor colorVertical, int points) {
        this.orientation = orientation;
        this.colorLeft = colorLeft;
        this.colorRight = colorRight;
        this.colorVertical = colorVertical;
        this.points = points;
        this.init();
    }

    /**

     */
    public static void take(List<SpectranglePiece> pieces) {
        for (int i = 0; i < 6; i++) {
            for (SpectranglePiece tile : pieces) {
                System.out.print(tile.drawingPiece.get(i));
            }
            System.out.print("\n");
        }
    }

    /**
     * This method rotates the piece
     */
    public void rotate() {
        SpectranglePieceColor aux_left = this.colorLeft, aux_right = this.colorRight, aux_vertical = this.colorVertical;

        if (this.orientation.equals(SpectranglePieceOrientation.UP)) {
            this.orientation = SpectranglePieceOrientation.DOWN;
            this.colorLeft = aux_vertical;
            this.colorRight = aux_right;
            this.colorVertical = aux_left;
        } else {
            this.orientation = SpectranglePieceOrientation.UP;
            this.colorLeft = aux_left;
            this.colorRight = aux_vertical;
            this.colorVertical = aux_right;
        }

        this.updatesPiece();
    }

    @Override
    public String toString() {
        if (this.orientation.equals(SpectranglePieceOrientation.UP)) {
            return String.valueOf(this.colorRight) + String.valueOf(this.colorVertical) + String.valueOf(this.colorLeft) + this.points.toString();
        } else {
            return String.valueOf(this.colorVertical) + String.valueOf(this.colorRight) + String.valueOf(this.colorLeft) + this.points.toString();
        }
    }

    public boolean isSamePiece(String pieceString) {
        boolean samePiece = false;

        for (int i = 0; i < 6; i++) {
            this.rotate();
            if (this.toString().equals(pieceString)) {
                samePiece = true;
            }
        }

        return samePiece;
    }

    private void init() {
        this.drawingPiece = new ArrayList<String>();
        this.updatesPiece();
    }

    private void updatesPiece() {
        this.drawingPiece = new ArrayList<String>();
        if (this.orientation.equals(SpectranglePieceOrientation.UP)) {
            this.drawingPiece.add("     ^      ");
            this.drawingPiece.add("    / \\     ");
            this.drawingPiece.add("   /   \\    ");
            this.drawingPiece.add("  /" + String.valueOf(this.colorLeft) + " " + this.points + " " + String.valueOf(this.colorRight) + "\\   ");
            this.drawingPiece.add(" /   " + String.valueOf(this.colorVertical) + "   \\  ");
            this.drawingPiece.add("----------- ");
        } else {
            this.drawingPiece.add("----------- ");
            this.drawingPiece.add(" \\   " + String.valueOf(this.colorVertical) + "   /  ");
            this.drawingPiece.add("  \\" + String.valueOf(this.colorLeft) + " " + String.valueOf(this.points) + " " + String.valueOf(this.colorRight) + "/   ");
            this.drawingPiece.add("   \\   /    ");
            this.drawingPiece.add("    \\ /     ");
            this.drawingPiece.add("     X      ");
        }
    }

    /**
     * This method inverts the orientation of the piece
     */
    public void invertPiece() {
        String aux = this.toString();
        for (int i = 0; i < 6; i++) {
            this.rotate();
            if (this.toString().equals(aux)) {
                return;
            }
        }
    }


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public SpectranglePieceColor getColorVertical() {
        return colorVertical;
    }

    public void setColorVertical(SpectranglePieceColor colorVertical) {
        this.colorVertical = colorVertical;
    }

    public SpectranglePieceColor getColorLeft() {
        return colorLeft;
    }

    public void setColorLeft(SpectranglePieceColor colorLeft) {
        this.colorLeft = colorLeft;
    }

    public SpectranglePieceColor getColorRight() {
        return colorRight;
    }

    public void setColorRight(SpectranglePieceColor colorRight) {
        this.colorRight = colorRight;
    }

    public SpectranglePieceOrientation getSpectranglePieceOrientation() {
        return orientation;
    }

    public void setSpectranglePieceOrientation(SpectranglePieceOrientation orientation) {
        this.orientation = orientation;
    }


}
