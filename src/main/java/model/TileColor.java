package model;

/*
 * This class implements a color for the tiles of Spectrangle game.
 */
public enum TileColor {

    R, G, B, Y, P, W;

    /**
     * This methods return the character symbol of each color.
     *
     * @return character 'R','G','B','Y','P','W' for colors
     */
    public Character getChar() {
        switch (this) {
            case R:
                return 'R';
            case G:
                return 'G';
            case B:
                return 'B';
            case Y:
                return 'Y';
            case P:
                return 'P';
            case W:
                return 'W';
            default:
                return ' ';
        }
    }
}
