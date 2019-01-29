package model;

public enum SpectranglePieceColor {

    RED, GREEN, BLUE, YELLOW, PURPLE, WHITE;

    public Character getChar() {
        switch (this) {
            case RED:
                return 'R';
            case GREEN:
                return 'G';
            case BLUE:
                return 'B';
            case YELLOW:
                return 'Y';
            case PURPLE:
                return 'P';
            case WHITE:
                return 'W';
            default:
                return ' ';
        }
    }

    /**
     * Determine if the two colors provided match.
     */
    public boolean colorMatch(SpectranglePieceColor c1, SpectranglePieceColor c2) {
        return ((c1 == c2) || (c1 == WHITE) || (c2 == WHITE));
    }
}
