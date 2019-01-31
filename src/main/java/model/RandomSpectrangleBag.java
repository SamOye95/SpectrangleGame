package model;


import exceptions.EmptyBagException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomSpectrangleBag implements SpectrangleBag {

    private ArrayList<SpectranglePiece> pieces;
    private Random random;

    /**
     * Constructor that creates a random bag
     */
    public RandomSpectrangleBag() {
        pieces = new ArrayList<SpectranglePiece>();
        random = new Random();

    }

    /**
     * @param spectranglePiece
     */
    public void addSpectranglePiece(SpectranglePiece spectranglePiece) {


    }

    /**
     * @return The piece removed from the bag
     * @throws EmptyBagException
     */


    public SpectranglePiece takeSpectranglePiece() throws EmptyBagException {

        if (pieces.size() == 0) {
            throw new EmptyBagException();
        }

        return pieces.remove(random.nextInt(getTheNumberOfRemainingPieces()));


    }

    public SpectranglePiece takeSpectranglePiece(String piece) {
        for (SpectranglePiece sp : this.pieces) {
            if (sp.toString().equals(piece)) {
                this.pieces.remove(sp);
                return sp;
            }
        }

        return null;
    }

    /**
     * @return
     */
    public int getTheNumberOfRemainingPieces() {
        return pieces.size();

    }

    public List<SpectranglePiece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<SpectranglePiece> pieces) {
        this.pieces = pieces;
    }

    public void resetBag() {
        pieces.clear();

        // 6-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.RED, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, 6));
        // 5-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.YELLOW, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.PURPLE, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.RED, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.PURPLE, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.RED, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.BLUE, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.GREEN, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.BLUE, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.YELLOW, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.GREEN, 5));
        // 4-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.BLUE, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.GREEN, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.GREEN, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.YELLOW, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.YELLOW, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.PURPLE, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.RED, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.PURPLE, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.RED, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.BLUE, 4));
        // 3-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.BLUE, SpectranglePieceColor.PURPLE, 3));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.GREEN, SpectranglePieceColor.YELLOW, 3));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.GREEN, SpectranglePieceColor.PURPLE, 3));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.RED, SpectranglePieceColor.BLUE, 3));
        // 2-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.RED, SpectranglePieceColor.PURPLE, 2));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.PURPLE, SpectranglePieceColor.RED, 2));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.YELLOW, SpectranglePieceColor.PURPLE, SpectranglePieceColor.GREEN, 2));
        // 1-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.GREEN, SpectranglePieceColor.RED, SpectranglePieceColor.PURPLE, 1));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.BLUE, SpectranglePieceColor.YELLOW, SpectranglePieceColor.GREEN, 1));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.RED, SpectranglePieceColor.YELLOW, SpectranglePieceColor.BLUE, 1));
        // joker piece
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.WHITE, SpectranglePieceColor.WHITE, SpectranglePieceColor.WHITE, 1));
    }

}



