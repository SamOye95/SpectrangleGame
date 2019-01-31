package model;

import java.util.ArrayList;
import java.util.List;

public class SpectranglePiece {


    public List<String> drawingLines;
    private SpectranglePlayer player;
    private SpectranglePieceColor colorVertical, colorLeft, colorRight;
    private SpectranglePieceOrientation orientation;
    private Integer points;


    public SpectranglePiece(SpectranglePieceOrientation orientation, SpectranglePieceColor colorLeft, SpectranglePieceColor colorRight, SpectranglePieceColor colorVertical, int points) {
        this.orientation = orientation;
        this.colorLeft = colorLeft;
        this.colorRight = colorRight;
        this.colorVertical = colorVertical;
        this.points = points;
        this.init();
    }

    public static void draw(List<SpectranglePiece> tiles) {
        for (int i = 0; i < 6; i++) {
            for (SpectranglePiece tile : tiles) {
                System.out.print(tile.drawingLines.get(i));
            }
            System.out.print("\n");
        }
    }

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

        this.updateDrawing();
    }

    @Override
    public String toString() {
        if (this.orientation.equals(SpectranglePieceOrientation.UP)) {
            return String.valueOf(this.colorRight) + String.valueOf(this.colorVertical) + String.valueOf(this.colorLeft) + this.points.toString();
        } else {
            return String.valueOf(this.colorVertical) + String.valueOf(this.colorRight) + String.valueOf(this.colorLeft) + this.points.toString();
        }
    }

    public boolean isEquivalent(String tileStr) {
        boolean equivalent = false;

        for (int i = 0; i < 6; i++) {
            this.rotate();
            if (this.toString().equals(tileStr)) {
                equivalent = true;
            }
        }

        return equivalent;
    }

    private void init() {
        this.drawingLines = new ArrayList<String>();
        this.updateDrawing();
    }

    private void updateDrawing() {
        this.drawingLines = new ArrayList<String>();
        if (this.orientation.equals(SpectranglePieceOrientation.UP)) {
            this.drawingLines.add("     ^      ");
            this.drawingLines.add("    / \\     ");
            this.drawingLines.add("   /   \\    ");
            this.drawingLines.add("  /" + String.valueOf(this.colorLeft) + " " + this.points + " " + String.valueOf(this.colorRight) + "\\   ");
            this.drawingLines.add(" /   " + String.valueOf(this.colorVertical) + "   \\  ");
            this.drawingLines.add("----------- ");
        } else {
            this.drawingLines.add("----------- ");
            this.drawingLines.add(" \\   " + String.valueOf(this.colorVertical) + "   /  ");
            this.drawingLines.add("  \\" + String.valueOf(this.colorLeft) + " " + String.valueOf(this.points) + " " + String.valueOf(this.colorRight) + "/   ");
            this.drawingLines.add("   \\   /    ");
            this.drawingLines.add("    \\ /     ");
            this.drawingLines.add("     X      ");
        }
    }

    public void invert() {
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
