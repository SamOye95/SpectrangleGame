package model;

import view.SpectrangleBoardPrinter;

import java.util.ArrayList;
import java.util.List;

public class SpectrangleBoard {


    public static final int LENGTH = 36;
    private List<SpectrangleBoardSpace> spaces;


    public SpectrangleBoard() {
        this.spaces = new ArrayList<SpectrangleBoardSpace>();
        this.init();
    }

    public int placeSpectranglePiece(SpectranglePiece piece, int index) {
        SpectrangleBoardSpace space = null;
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
            piece.invert();
            edges = this.canBePlaced(space, piece);
        }

        if (edges == 0) {
            piece.invert();
            return -1;
        }

        points = piece.getPoints() * edges * space.getMultiplier();

        space.setSpectranglePiece(piece);
        return points;
    }


    public int canBePlaced(SpectrangleBoardSpace space, SpectranglePiece piece) {
        int edges = 0;

        if (this.isEmpty()) {

            if (!piece.getSpectranglePieceOrientation().equals(space.getOrientation())) {
                piece.invert();
            }

            return 1;
        }

        if (!piece.getSpectranglePieceOrientation().equals(space.getOrientation())) {
            return 0;
        }

        SpectrangleBoardSpace left;
        if ((left = space.getLeft()) != null) {
            if (left.getSpectranglePiece() != null) {
                if (left.getSpectranglePiece().getColorRight() == piece.getColorLeft() || piece.getColorLeft() == SpectranglePieceColor.WHITE || left.getSpectranglePiece().getColorRight() == SpectranglePieceColor.WHITE) {
                    edges++;
                }
            }
        }

        SpectrangleBoardSpace right;
        if ((right = space.getRight()) != null) {
            if (right.getSpectranglePiece() != null) {
                if (right.getSpectranglePiece().getColorLeft() == piece.getColorRight() || piece.getColorRight() == SpectranglePieceColor.WHITE || right.getSpectranglePiece().getColorLeft() == SpectranglePieceColor.WHITE) {
                    edges++;
                }
            }
        }


        SpectrangleBoardSpace vertical;
        if ((vertical = space.getVertical()) != null) {
            if (vertical.getSpectranglePiece() != null) {
                if (vertical.getSpectranglePiece().getColorVertical() == piece.getColorVertical() || piece.getColorVertical() == SpectranglePieceColor.WHITE || vertical.getSpectranglePiece().getColorVertical() == SpectranglePieceColor.WHITE) {
                    edges++;
                }
            }
        }

        return edges;
    }

    public boolean canBePlaced(SpectranglePiece piece) {
        if (this.isEmpty()) {
            return true;
        }

        for (SpectrangleBoardSpace space : this.spaces) {
            if (this.canBePlaced(space, piece) != 0) {
                return true;
            }
        }

        return false;
    }

    public String toString() {
        List<Integer> values = new ArrayList<Integer>();
        List<SpectranglePieceColor> vertical = new ArrayList<SpectranglePieceColor>();
        List<SpectranglePieceColor> left = new ArrayList<SpectranglePieceColor>();
        List<SpectranglePieceColor> right = new ArrayList<SpectranglePieceColor>();

        Integer value;
        SpectranglePieceColor chr_vertical, chr_left, chr_right;
        for (SpectrangleBoardSpace space : this.spaces) {
            value = null;
            chr_vertical = chr_left = chr_right = null;

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


    private void init() {
        SpectrangleBoardSpace space;
        for (int i = 0; i < SpectrangleBoard.LENGTH; i++) {

            //Initialize a new space
            space = new SpectrangleBoardSpace();
            space.setSpectranglePiece(null);
            space.setIndex(i);

            //Set the orientation
            if ((rowOfIndex(i) + columnOfIndex(i)) % 2 == 1)
                space.setOrientation(SpectranglePieceOrientation.DOWN);
            else space.setOrientation(SpectranglePieceOrientation.UP);

            //Setting the bonus
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
        space = new SpectrangleBoardSpace();
        for (SpectrangleBoardSpace s : spaces) {
            int x = s.getIndex();
            int r = rowOfIndex(x), c = columnOfIndex(x);

            if (r == (-1) * c) {
                s.setLeft(space);
            }
            if (r == c) {
                s.setRight(space);
            }
            if (r == (int) Math.sqrt(LENGTH - 1) && s.getOrientation() == SpectranglePieceOrientation.UP) {
                s.setVertical(space);
            }
            if (s.getLeft() != space) {
                s.setLeft(getSpectrangleBoardSpaceOfCoord(r, c - 1));
            }
            if (s.getRight() != space) {
                s.setRight(getSpectrangleBoardSpaceOfCoord(r, c + 1));
            }
            if (s.getVertical() != space && s.getOrientation() == SpectranglePieceOrientation.UP) {
                s.setVertical(getSpectrangleBoardSpaceOfCoord(r + 1, c));
            }
            if (s.getVertical() != space && s.getOrientation() == SpectranglePieceOrientation.DOWN) {
                s.setVertical(getSpectrangleBoardSpaceOfCoord(r - 1, c));
            }
        }
    }

    public int coordToIndex(int row, int col) {
        return (row * row) + row + col;
    }

    public int rowOfIndex(int x) {

        for (int i = 0; i <= 5; i++) {
            if (x >= i * i && x < (i + 1) * (i + 1))
                return i;
        }
        return -1;
    }

    public int columnOfIndex(int x) {
        int r = rowOfIndex(x);
        if (x < r * (r + 1)) {
            int i = 0;
            while (i >= (-r)) {
                if (coordToIndex(r, i) == x)
                    return i;
                i--;
            }
        } else {
            int i = 0;
            while (i <= r) {
                if (coordToIndex(r, i) == x)
                    return i;
                i++;
            }
        }
        return -15;
    }

    public SpectrangleBoardSpace getSpectrangleBoardSpaceOfCoord(int r, int c) {

        for (SpectrangleBoardSpace s : this.spaces) {
            if (columnOfIndex(s.getIndex()) == c && rowOfIndex(s.getIndex()) == r)
                return s;
        }
        return null;
    }

    public boolean isEmpty() {
        for (SpectrangleBoardSpace space : this.spaces) {
            if (space.getSpectranglePiece() != null) {
                return false;
            }
        }
        return true;
    }

    public List<SpectrangleBoardSpace> getSpectrangleBoardSpaces() {
        return spaces;
    }

}
