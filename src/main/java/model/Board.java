package model;

import view.SpectrangleBoardPrinter;

import java.util.ArrayList;
import java.util.List;


/*
 * This class implements the board of the Spectrangle Game.
 *
 * @author Samuel Oyediran
 */

public class Board {

    // ------------------ Instance variables ----------------

    public static final int LENGTH = 36;
    private List<BoardSpace> spaces;

    // -- Constructor -----------------------------------------------

    /*
     * This constructor creates a board.
     */
    public Board() {
        this.spaces = new ArrayList<BoardSpace>();
        this.init();
    }

    // ------------------ Queries and Methods --------------------------

    /*
     * Places a piece on the board and returns the points from that move.
     *
     *
     * @param piece
     * @param index
     * @return points
     */
    /*
     *@requires piece != null && index >= 0 && index <= this.length
     *@ensures piece == null ==> \result == -1;
     *@ensures !this.canBePlaced(piece) ==> \result == -1;
     */
    public int placeTile(Tile piece, int index) {
        BoardSpace space = null;
        Integer points = 0;
        Integer edges = 0;

        try {
            space = this.spaces.get(index);
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }

        if (space.getSpectranglePiece() != null) {
            return -1;
        }

        edges = this.canBePlaced(space, piece);
        if (edges == 0) {
            piece.invertPiece();
            edges = this.canBePlaced(space, piece);
        }

        if (edges == 0) {
            piece.invertPiece();
            return -1;
        }

        points = piece.getPoints() * edges * space.getMultiplier();

        space.setSpectranglePiece(piece);
        return points;
    }

    /*
     * This method returns the number of matching sides when a player places a tile in a Board space
     * @param space
     * @param piece
     * @return
     */
    /*
     *@requires space != null && piece != null;
     *@ensures \result >= 0 && \result <= 3;
     */
    public int canBePlaced(BoardSpace space, Tile piece) {
        int edges = 0;
        int matchedEdges = 0;

        if (this.isEmpty()) {

            if (!piece.getTileOrientation().equals(space.getOrientation())) {
                piece.invertPiece();
            }

            return 1;
        } else {
            if (!piece.getTileOrientation().equals(space.getOrientation())) {
                return 0;
            }

            BoardSpace left;
            if ((left = space.getLeft()) != null) {
                if (left.getSpectranglePiece() != null) {
                    edges++;
                    if (left.getSpectranglePiece().getColorRight() == piece.getColorLeft() || piece.getColorLeft() == 'W' || left.getSpectranglePiece().getColorRight() == 'W') {
                        matchedEdges++;
                    }
                }
            }
        }


        BoardSpace right;
        if ((right = space.getRight()) != null) {
            if (right.getSpectranglePiece() != null) {
                edges++;
                if (right.getSpectranglePiece().getColorLeft() == piece.getColorRight() || piece.getColorRight() == 'W' || right.getSpectranglePiece().getColorLeft() == 'W') {
                    matchedEdges++;
                }
            }
        }


        BoardSpace vertical;
        if ((vertical = space.getVertical()) != null) {
            if (vertical.getSpectranglePiece() != null) {
                edges++;

                if (vertical.getSpectranglePiece().getColorVertical() == piece.getColorVertical() || piece.getColorVertical() == 'W' || vertical.getSpectranglePiece().getColorVertical() == 'W') {
                    matchedEdges++;
                }
            }
        }

        if (edges > matchedEdges) {
            return 0;
        }

        return matchedEdges;
    }

    /*
     * This checks if the piece can be placed in a space.
     * @param piece
     * @return
     */
    /*@pure
     *@requires tile != null;
     *@ensures (\exists BoardSpace space; this.getBoardSpaces().contains(space) && this.canBePlaced(space,tile) != 0; \result == true)
     */
    public boolean canBePlaced(Tile piece) {
        if (this.isEmpty()) {
            return true;
        }

        for (BoardSpace space : this.spaces) {
            if (this.canBePlaced(space, piece) != 0) {
                return true;
            }
        }

        return false;
    }

    /*
     *@pure
     *@ensures \result != null;
     */
    public String toString() {
        List<Integer> values = new ArrayList<Integer>();
        List<Character> vertical = new ArrayList<Character>();
        List<Character> left = new ArrayList<Character>();
        List<Character> right = new ArrayList<Character>();

        Integer value;
        Character chr_vertical, chr_left, chr_right;
        for (BoardSpace space : this.spaces) {
            value = null;
            chr_vertical = null;
            chr_left = null;
            chr_right = null;

            if (space.getSpectranglePiece() != null) {
                value = space.getSpectranglePiece().getPoints();
                chr_vertical = space.getSpectranglePiece().getColorVertical();
                chr_left = space.getSpectranglePiece().getColorLeft();
                chr_right = space.getSpectranglePiece().getColorRight();
            }

            values.add(value);
            vertical.add(chr_vertical);
            left.add(chr_left);
            right.add(chr_right);
        }


        return SpectrangleBoardPrinter.getBoardString(values, vertical, left, right);
    }

    /*
     * This method initialises a new space ,sets the orientation and sets the index multiplier (bonus point)
     */
    /*
     *@ensures this.getBoardSpaces() != null && this.getBoardSpaces().size() == this.LENGTH;
     *@ensures (\forall BoardSpace space; this.getBoardSpaces().contains(space); space.getOrientation() != null);
     *@ensures (\forall BoardSpace space; this.getBoardSpaces().contains(space) && (space.getIndex() = 11 || space.getIndex() == 133 || space.getIndex() == 20 || space.getIndex() == 2 || space.getIndex() == 26 || space.getIndex() ==34 || space.getIndex() == 10 || space.getIndex() == 14 || space.getIndex() == 30) ; space.getMultiplier() != 1);
     *@ensures (\forall BoardSpace space; this.getBoardSpaces().contains(space) && (columnOfIndex(space.getIndex()) != (-1) * rowOfIndex(space.getIndex())); space.getLeft().getIndex() == -1);
     *@ensures (\forall BoardSpace space; this.getBoardSpaces().contains(space) && (columnOfIndex(space.getIndex()) == rowOfIndex(space.getIndex())); space.getRight().getIndex() == -1);
     *@ensures (\forall BoardSpace space; this.getBoardSpaces().contains(space) && rowOfIndex(space.getIndex()) == 5 && space.getOrientation() == Orientation.UP; space.getVertical().getIndex() == -1)
     */
    private void init() {
        BoardSpace space;
        for (int i = 0; i < Board.LENGTH; i++) {

            space = new BoardSpace();
            space.setSpectranglePiece(null);
            space.setIndex(i);

            if ((rowOfIndex(i) + columnOfIndex(i)) % 2 == 1)
                space.setOrientation(TileOrientation.DOWN);
            else space.setOrientation(TileOrientation.UP);

            if (i == 11 || i == 13 || i == 20) {
                space.setMultiplier(4);
            } else if (i == 2 || i == 26 || i == 34) {
                space.setMultiplier(3);
            } else if (i == 10 || i == 14 || i == 30) {
                space.setMultiplier(2);
            } else {
                space.setMultiplier(1);
            }
            this.spaces.add(space);
        }

        //Setting the neighbors
        space = new BoardSpace();
        for (BoardSpace s : spaces) {
            int x = s.getIndex();
            int r = rowOfIndex(x), c = columnOfIndex(x);

            if (r == (-1) * c) {
                s.setLeft(space);
            }
            if (r == c) {
                s.setRight(space);
            }
            if (r == (int) Math.sqrt(LENGTH - 1) && s.getOrientation() == TileOrientation.UP) {
                s.setVertical(space);
            }
            if (s.getLeft() != space) {
                s.setLeft(getBoardSpaceOfCoord(r, c - 1));
            }
            if (s.getRight() != space) {
                s.setRight(getBoardSpaceOfCoord(r, c + 1));
            }
            if (s.getVertical() != space && s.getOrientation() == TileOrientation.UP) {
                s.setVertical(getBoardSpaceOfCoord(r + 1, c));
            }
            if (s.getVertical() != space && s.getOrientation() == TileOrientation.DOWN) {
                s.setVertical(getBoardSpaceOfCoord(r - 1, c));
            }
        }
    }

    /*
     * This maps board coordinates to a multiplier index
     * @pure
     * @requires 0 <= row && row <= 5 && -5<=col && col <= 5;
     * @ensures \ result >= 0 && \result <= this.LENGTH;
     */

    public int mapsCoordinatesToIndex(int r, int c) {
        return (r * r) + r + c;
    }

    /*
     *@pure
     *@requires x <= this.LENGTH && x >= 0;
     *@ensures 0 <= \result && \result <= 5;
     */
    public int rowOfIndex(int x) {

        for (int i = 0; i <= 5; i++) {
            if (x >= i * i && x < (i + 1) * (i + 1))
                return i;
        }
        return -1;
    }

    /*
     *@pure
     *@requires x <= this.LENGTH && x >= 0;
     *@ensures -5 <= \result && \result <= 5;
     *
     */
    public int columnOfIndex(int x) {
        int r = rowOfIndex(x);
        if (x < r * (r + 1)) {
            int i = 0;
            while (i >= (-r)) {
                if (mapsCoordinatesToIndex(r, i) == x)
                    return i;
                i--;
            }
        } else {
            int i = 0;
            while (i <= r) {
                if (mapsCoordinatesToIndex(r, i) == x)
                    return i;
                i++;
            }
        }
        return -15;
    }

    /*
     * returns the boardspace of a coordinate
     * @param r
     * @param c
     * @return
     */
    /*
     *@pure
     * @requires 0 <= r && r <= 5 && -5 <= c && c <=5;
     * @ensures \result != null;
     *
     */
    public BoardSpace getBoardSpaceOfCoord(int r, int c) {

        for (BoardSpace s : this.spaces) {
            if (columnOfIndex(s.getIndex()) == c && rowOfIndex(s.getIndex()) == r)
                return s;
        }
        return null;
    }

    /*
     * This method shows whether a field is empty or not.
     * @return boolean value of whether the board is empty
     */
    /*
     *@pure
     * @ensures (\exist BoardSpace space; this.getBoardSpace().contains(space) && space.getTile() != null; \result == false);
     */
    public boolean isEmpty() {
        for (BoardSpace space : this.spaces) {
            if (space.getSpectranglePiece() != null) {
                return false;
            }
        }
        return true;
    }

    /*
     * This gets the list of board spaces
     *
     * @return board spaces
     */

    /*
     *@pure
     * @ensure (!\result.equals(null));
     */
    public List<BoardSpace> getBoardSpaces() {
        return spaces;
    }

}
