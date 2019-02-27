package model;

import java.util.ArrayList;
import java.util.List;

/*
 * This class implements the tile of the Spectrangle game.
 */
public class Tile {


    public List<String> drawingPiece;

    private char colorVertical, colorLeft, colorRight;
    private TileOrientation orientation;
    private Integer points;


    /*
     *This constructor creates an instance of a tile by the colors and score given.     *
     * @param orientation
     * @param colorLeft the color in the left position
     * @param colorRight the color in the right position
     * @param colorVertical the color in the vertical position
     * @param points the score of the tile
     */
    public Tile(TileOrientation orientation, char colorLeft, char colorRight, char colorVertical, int points) {
        this.orientation = orientation;
        this.colorLeft = colorLeft;
        this.colorRight = colorRight;
        this.colorVertical = colorVertical;
        this.points = points;
        this.init();
    }

    /*

     */
    public static void take(List<Tile> pieces) {
        for (int i = 0; i < 6; i++) {
            for (Tile tile : pieces) {
                System.out.print(tile.drawingPiece.get(i));
            }
            System.out.print("\n");
        }
    }

    /*
     * This method rotates the piece
     */
    public void rotate() {
        char aux_left = this.colorLeft, aux_right = this.colorRight, aux_vertical = this.colorVertical;

        if (this.orientation.equals(TileOrientation.UP)) {
            this.orientation = TileOrientation.DOWN;
            this.colorLeft = aux_vertical;
            this.colorRight = aux_right;
            this.colorVertical = aux_left;
        } else {
            this.orientation = TileOrientation.UP;
            this.colorLeft = aux_left;
            this.colorRight = aux_vertical;
            this.colorVertical = aux_right;
        }

        this.updatesPiece();
    }

    @Override
    public String toString() {
        if (this.orientation.equals(TileOrientation.UP)) {
            return String.valueOf(this.colorRight) + String.valueOf(this.colorVertical) + String.valueOf(this.colorLeft) + this.points.toString();
        } else {
            return String.valueOf(this.colorVertical) + String.valueOf(this.colorRight) + String.valueOf(this.colorLeft) + this.points.toString();
        }
    }

    public boolean isSamePiece(String pieceString) {
        boolean samePiece = false;

        for (int i = 0; i < 6; i++) {
            this.rotate();
            if (this.toString().equalsIgnoreCase(pieceString)) {
                samePiece = true;
            }
        }

        return samePiece;
    }

    private void init() {
        this.drawingPiece = new ArrayList<>();
        this.updatesPiece();
    }

    private void updatesPiece() {
        this.drawingPiece = new ArrayList<>();
        if (this.orientation.equals(TileOrientation.UP)) {
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

    /*
     * This method inverts the orientation of the piece
     */
    public void invertPiece() {
        String aux = this.toString();
        for (int i = 0; i < 6; i++) {
            this.rotate();
            if (this.toString().equalsIgnoreCase(aux)) {
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

    /*
     * This method gives the color in the vertical field.
     *
     * @return the color in the vertical color field
     */
    public char getColorVertical() {
        return colorVertical;
    }

    /*
     * This method gives the color in the left field.
     *
     * @return the color in the left color field
     */
    public char getColorLeft() {
        return colorLeft;
    }

    /*
     * This method gives the color in the right field.
     *
     * @return the color in the right color field
     */
    public char getColorRight() {
        return colorRight;
    }


    public TileOrientation getTileOrientation() {
        return orientation;
    }


}
