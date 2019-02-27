package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * This class implements the bag in the Game
 *
 * @author Samuel Oyediran
 */

public class RandomBag implements Bag {

    // ------------------ Instance variables ----------------

    private final Random random;
    private ArrayList<Tile> pieces;

    /*
     * Constructor that creates a random bag
     */
    public RandomBag() {
        pieces = new ArrayList<>();
        this.resetBag();
        random = new Random();
    }


    /*
     * This method removes a tile from the bag
     * @return a randomly taken tile
     *
     */

    public Tile takeTile() {
        int piecesLength, index;

        piecesLength = this.pieces.size();

        if (piecesLength == 0) {
            return null;
        }

        index = random.nextInt(this.pieces.size());
        return this.pieces.remove(index);


    }

    /*
     * This method gives a player a random tile
     *
     * @return a randomly taken tile
     */

    public Tile takeTile(String piece) {
        for (Tile sp : this.pieces) {
            if (sp.toString().equalsIgnoreCase(piece)) {
                this.pieces.remove(sp);
                return sp;
            }
        }

        return null;
    }

    /*
     * @return
     */
    public int getTheNumberOfRemainingPieces() {
        return pieces.size();

    }

    public List<Tile> getPieces() {
        return pieces;
    }

    /*
     * This methods resets the bag with 36 tiles
     */
    public void resetBag() {
        pieces.clear();

        // 6-point pieces
        pieces.add(new Tile(TileOrientation.UP, 'R', 'R', 'R', 6));
        pieces.add(new Tile(TileOrientation.UP, 'G', 'G', 'G', 6));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'B', 'B', 6));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'Y', 'Y', 6));
        pieces.add(new Tile(TileOrientation.UP, 'P', 'P', 'P', 6));
        // 5-point pieces
        pieces.add(new Tile(TileOrientation.UP, 'R', 'R', 'Y', 5));
        pieces.add(new Tile(TileOrientation.UP, 'R', 'R', 'P', 5));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'B', 'R', 5));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'B', 'P', 5));
        pieces.add(new Tile(TileOrientation.UP, 'G', 'G', 'R', 5));
        pieces.add(new Tile(TileOrientation.UP, 'G', 'G', 'B', 5));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'Y', 'G', 5));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'Y', 'B', 5));
        pieces.add(new Tile(TileOrientation.UP, 'P', 'P', 'Y', 5));
        pieces.add(new Tile(TileOrientation.UP, 'P', 'P', 'G', 5));
        // 4-point pieces
        pieces.add(new Tile(TileOrientation.UP, 'R', 'R', 'B', 4));
        pieces.add(new Tile(TileOrientation.UP, 'R', 'R', 'G', 4));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'B', 'G', 4));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'B', 'Y', 4));
        pieces.add(new Tile(TileOrientation.UP, 'G', 'G', 'Y', 4));
        pieces.add(new Tile(TileOrientation.UP, 'G', 'G', 'P', 4));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'Y', 'R', 4));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'Y', 'P', 4));
        pieces.add(new Tile(TileOrientation.UP, 'P', 'P', 'R', 4));
        pieces.add(new Tile(TileOrientation.UP, 'P', 'P', 'B', 4));
        // 3-point pieces
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'B', 'P', 3));
        pieces.add(new Tile(TileOrientation.UP, 'R', 'G', 'Y', 3));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'G', 'P', 3));
        pieces.add(new Tile(TileOrientation.UP, 'G', 'R', 'B', 3));
        // 2-point pieces
        pieces.add(new Tile(TileOrientation.UP, 'B', 'R', 'P', 2));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'P', 'R', 2));
        pieces.add(new Tile(TileOrientation.UP, 'Y', 'P', 'G', 2));
        // 1-point pieces
        pieces.add(new Tile(TileOrientation.UP, 'G', 'R', 'P', 1));
        pieces.add(new Tile(TileOrientation.UP, 'B', 'Y', 'G', 1));
        pieces.add(new Tile(TileOrientation.UP, 'R', 'Y', 'B', 1));
        // joker piece
        pieces.add(new Tile(TileOrientation.UP, 'W', 'W', 'W', 1));
    }

}



