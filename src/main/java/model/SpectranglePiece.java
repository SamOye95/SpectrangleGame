package model;


public class SpectranglePiece {

    // Sections of the piece.  These should be used for the getColor() method.
    public static int BOTTOM_SECTION = 0;
    public static int LEFT_SECTION = 1;
    public static int RIGHT_SECTION = 2;

    // The colors of the three sections of the piece
    private int[] colors = new int[3];

    // The value of the piece
    private int value;

    /**
     * Constructor which creates the piece.
     *
     * @param value       The value of the piece
     * @param bottomColor Color for the bottom
     * @param leftColor   Color for the left
     * @param rightColor  Color for the right
     */
    public SpectranglePiece(int value, int bottomColor, int leftColor, int rightColor) {
        this.value = value;
        colors[BOTTOM_SECTION] = bottomColor;
        colors[LEFT_SECTION] = leftColor;
        colors[RIGHT_SECTION] = rightColor;
    }

    /**
     * Constructor for a piece given a piece.
     *
     * @param oldPiece The piece to copy.
     */
    public SpectranglePiece(SpectranglePiece oldPiece) {
        this.value = oldPiece.value;
        colors[BOTTOM_SECTION] = oldPiece.colors[BOTTOM_SECTION];
        colors[LEFT_SECTION] = oldPiece.colors[LEFT_SECTION];
        colors[RIGHT_SECTION] = oldPiece.colors[RIGHT_SECTION];
    }


    /**
     * Return the color for the requested part of the piece.
     *
     * @param section Which section to get the color of.
     * @return the color code for the requested section.
     */
    public int getColor(int section) {
        return colors[section];
    }

    /**
     * Return the value of the piece.
     *
     * @return the value of the piece.
     */
    public int getValue() {
        return value;
    }

    /**
     * Determine if the piece given is the same piece.  A piece is the same
     * irrespective of flips and/or rotates.
     */
    public boolean isSamePiece(SpectranglePiece tgtPiece) {
        if (value != tgtPiece.value) {
            return false;
        }

        // Brute force compare
        int tcl = tgtPiece.colors[LEFT_SECTION];
        int tcr = tgtPiece.colors[RIGHT_SECTION];
        int tcb = tgtPiece.colors[BOTTOM_SECTION];
        int cl = colors[LEFT_SECTION];
        int cr = colors[RIGHT_SECTION];
        int cb = colors[BOTTOM_SECTION];

        if (cb == tcb) {
            return ((cl == tcl) && (cr == tcr)) ||
                    ((cl == tcr) && (cr == tcl));
        } else if (cb == tcl) {
            return ((cl == tcb) && (cr == tcr)) ||
                    ((cl == tcr) && (cr == tcb));
        } else if (cb == tcr) {
            return ((cl == tcl) && (cr == tcb)) ||
                    ((cl == tcb) && (cr == tcl));
        }
        return false;
    }


}
