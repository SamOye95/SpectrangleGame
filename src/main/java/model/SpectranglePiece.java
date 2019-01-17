package model;

import java.text.MessageFormat;


public class SpectranglePiece {

    // Sections of the piece.  These should be used for the getColor() method.
    public static int BOTTOM_SECTION = 0;
    public static int LEFT_SECTION = 1;
    public static int RIGHT_SECTION = 2;

    // The colors of the three sections of the piece
    private SpectranglePieceColor[] colors;

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
    public SpectranglePiece(int value, SpectranglePieceColor bottomColor, SpectranglePieceColor leftColor, SpectranglePieceColor rightColor) {
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

    /*
     * This routine will generate the possible rotations/flips of the given
     * piece.  The array returned will include only distinct pieces, so in the
     * case that 2 or 3 segments are the same color there will be fewer entries
     * in the array returned.
     * A copy of the given piece will be returned as element 0 of the array.
     *
     * @param thePiece   The piece to make rotations from.
     * @return an array of the valid orientations.
     */
    public static SpectranglePiece[] makePieceRotations(SpectranglePiece thePiece) {
        SpectranglePiece[] rots;

        int val = thePiece.getValue();
        SpectranglePieceColor cl = thePiece.getColor(SpectranglePiece.LEFT_SECTION);
        SpectranglePieceColor cr = thePiece.getColor(SpectranglePiece.RIGHT_SECTION);
        SpectranglePieceColor cb = thePiece.getColor(SpectranglePiece.BOTTOM_SECTION);

        if (cl != cr) {
            // All 3 colors are different, so 6 orientations
            rots = new SpectranglePiece[6];

            rots[0] = new SpectranglePiece(val, cb, cl, cr);
            rots[1] = new SpectranglePiece(val, cb, cr, cl);
            rots[2] = new SpectranglePiece(val, cl, cr, cb);
            rots[3] = new SpectranglePiece(val, cr, cl, cb);
            rots[4] = new SpectranglePiece(val, cr, cb, cl);
            rots[5] = new SpectranglePiece(val, cl, cb, cr);
        } else {
            if (cl == cb) {
                // All 3 colors are the same, so only 1 orientation
                rots = new SpectranglePiece[1];

                rots[0] = new SpectranglePiece(val, cb, cl, cr);
            } else {
                // Left & right are same color, so 3 orientations
                rots = new SpectranglePiece[3];

                rots[0] = new SpectranglePiece(val, cb, cl, cr);
                rots[1] = new SpectranglePiece(val, cr, cb, cl);
                rots[2] = new SpectranglePiece(val, cl, cr, cb);
            }
        }

        return rots;
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
     * Return the color for the requested part of the piece.
     *
     * @param section Which section to get the color of.
     * @return the color code for the requested section.
     */
    public SpectranglePieceColor getColor(int section) {
        return colors[section];
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
        SpectranglePieceColor tcl = tgtPiece.colors[LEFT_SECTION];
        SpectranglePieceColor tcr = tgtPiece.colors[RIGHT_SECTION];
        SpectranglePieceColor tcb = tgtPiece.colors[BOTTOM_SECTION];
        SpectranglePieceColor cl = colors[LEFT_SECTION];
        SpectranglePieceColor cr = colors[RIGHT_SECTION];
        SpectranglePieceColor cb = colors[BOTTOM_SECTION];

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

    @Override
    public String toString() {
        String template = "\n" +
                "     ʌ\n" +
                "    / \\\n" +
                "   / {0} \\\n" +
                "  /{1}   {2}\\\n" +
                " /   {3}   \\\n" +
                "/---------\\\n";
        Object[] args = new Object[4];
        args[0] = value;
        args[1] = colors[BOTTOM_SECTION].getChar();
        args[2] = colors[LEFT_SECTION].getChar();
        args[3] = colors[RIGHT_SECTION].getChar();
        MessageFormat mf = new MessageFormat(template);
        return mf.format(args);
    }


}
