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
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.R, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.G, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.B, SpectranglePieceColor.B, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.Y, SpectranglePieceColor.Y, 6));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.P, SpectranglePieceColor.P, SpectranglePieceColor.P, 6));
        // 5-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.Y, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.P, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.B, SpectranglePieceColor.R, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.B, SpectranglePieceColor.P, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.R, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.B, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.Y, SpectranglePieceColor.G, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.Y, SpectranglePieceColor.B, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.P, SpectranglePieceColor.P, SpectranglePieceColor.Y, 5));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.P, SpectranglePieceColor.P, SpectranglePieceColor.G, 5));
        // 4-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.B, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.G, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.B, SpectranglePieceColor.G, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.B, SpectranglePieceColor.Y, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.Y, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.P, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.Y, SpectranglePieceColor.R, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.Y, SpectranglePieceColor.P, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.P, SpectranglePieceColor.P, SpectranglePieceColor.R, 4));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.P, SpectranglePieceColor.P, SpectranglePieceColor.B, 4));
        // 3-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.B, SpectranglePieceColor.P, 3));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.G, SpectranglePieceColor.Y, 3));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.G, SpectranglePieceColor.P, 3));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.R, SpectranglePieceColor.B, 3));
        // 2-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.R, SpectranglePieceColor.P, 2));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.P, SpectranglePieceColor.R, 2));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.P, SpectranglePieceColor.G, 2));
        // 1-point pieces
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.R, SpectranglePieceColor.P, 1));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.B, SpectranglePieceColor.Y, SpectranglePieceColor.G, 1));
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.Y, SpectranglePieceColor.B, 1));
        // joker piece
        pieces.add(new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.W, SpectranglePieceColor.W, SpectranglePieceColor.W, 1));
    }

}



