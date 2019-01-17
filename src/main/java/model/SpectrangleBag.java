package model;

import java.util.ArrayList;


public class SpectrangleBag {

    private ArrayList<SpectranglePiece> pieces;

    public SpectrangleBag() {
        pieces = new ArrayList<SpectranglePiece>();
        resetBag();
    }


    public void resetBag() {
        pieces.clear();

        // 6-point tiles
        pieces.add(new SpectranglePiece(6, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.RED));
        pieces.add(new SpectranglePiece(6, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN));
        pieces.add(new SpectranglePiece(6, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE));
        pieces.add(new SpectranglePiece(6, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW));
        pieces.add(new SpectranglePiece(6, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE));
        // 5-point tiles
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.YELLOW));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.RED));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.RED));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.BLUE));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.GREEN));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.BLUE));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.YELLOW));
        pieces.add(new SpectranglePiece(5, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.GREEN));
        // 4-point tiles
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.BLUE));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.RED, SpectranglePieceColor.RED, SpectranglePieceColor.GREEN));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.GREEN));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.BLUE, SpectranglePieceColor.BLUE, SpectranglePieceColor.YELLOW));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.YELLOW));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.GREEN, SpectranglePieceColor.GREEN, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.RED));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.YELLOW, SpectranglePieceColor.YELLOW, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.RED));
        pieces.add(new SpectranglePiece(4, SpectranglePieceColor.PURPLE, SpectranglePieceColor.PURPLE, SpectranglePieceColor.BLUE));
        // 3-point tiles
        pieces.add(new SpectranglePiece(3, SpectranglePieceColor.YELLOW, SpectranglePieceColor.BLUE, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(3, SpectranglePieceColor.RED, SpectranglePieceColor.GREEN, SpectranglePieceColor.YELLOW));
        pieces.add(new SpectranglePiece(3, SpectranglePieceColor.BLUE, SpectranglePieceColor.GREEN, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(3, SpectranglePieceColor.GREEN, SpectranglePieceColor.RED, SpectranglePieceColor.BLUE));
        // 2-point tiles
        pieces.add(new SpectranglePiece(2, SpectranglePieceColor.BLUE, SpectranglePieceColor.RED, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(2, SpectranglePieceColor.YELLOW, SpectranglePieceColor.PURPLE, SpectranglePieceColor.RED));
        pieces.add(new SpectranglePiece(2, SpectranglePieceColor.YELLOW, SpectranglePieceColor.PURPLE, SpectranglePieceColor.GREEN));
        // 1-point tiles
        pieces.add(new SpectranglePiece(1, SpectranglePieceColor.GREEN, SpectranglePieceColor.RED, SpectranglePieceColor.PURPLE));
        pieces.add(new SpectranglePiece(1, SpectranglePieceColor.BLUE, SpectranglePieceColor.YELLOW, SpectranglePieceColor.GREEN));
        pieces.add(new SpectranglePiece(1, SpectranglePieceColor.RED, SpectranglePieceColor.YELLOW, SpectranglePieceColor.BLUE));
        // joker
        pieces.add(new SpectranglePiece(1, SpectranglePieceColor.WHITE, SpectranglePieceColor.WHITE, SpectranglePieceColor.WHITE));
    }

}
