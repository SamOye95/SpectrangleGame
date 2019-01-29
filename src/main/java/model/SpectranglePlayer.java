package model;

import java.util.ArrayList;
import java.util.List;

public class SpectranglePlayer {

    private SpectrangleGame game;
    private List<SpectranglePiece> spectranglePieces;
    private SpectrangleScoreBoard scoreBoard;

    private String playerName;
    private int score;


    public SpectranglePlayer(String playerName) {
        this.playerName = playerName;
        this.spectranglePieces = new ArrayList<SpectranglePiece>();
        this.scoreBoard = new SpectrangleScoreBoard();
    }

    public List<SpectranglePiece> takeMaximumSpectranglePieces() {
        List<SpectranglePiece> spectranglePieces = new ArrayList<SpectranglePiece>();
        while (this.spectranglePieces.size() < 4) {
            spectranglePieces.add(this.takeSpectralPiece());

        }
        return spectranglePieces;
    }

    public SpectranglePiece takeSpectranglePiece() {
        if (this.spectranglePieces.size() >= 4) {
            return null;
        }

        SpectranglePiece spectranglePiece = this.game.getBag().getBag().takeSpectranglePiece();
        this.spectranglePieces.add(spectranglePiece)
    }

    public SpectranglePiece takeSpectralPiece(String spectranglePiece) {

        if (this.spectranglePieces.size() >= 4) {
            return null;
        }

        SpectranglePiece sp = this.game.getBag().takeSpectranglePiece(spectranglePiece);
        this.spectranglePieces.add(sp);
        return sp;
    }

    public SpectranglePiece lastSpectranglePiece() {
        return this.spectranglePieces.get(this.spectranglePieces.size() - 1)
    }

    public int placeSpectranglePiece(int index, String spectranglePieceString) {
        SpectranglePiece spectranglePiece = null;

        for (SpectranglePiece sp : this.spectranglePieces) {
            if (spectranglePieceString.equals(sp.toString())) {
                spectranglePiece = sp;
            }
        }

        if (spectranglePiece == null) {

            return 404;
        }

        return this.game.placeSpectranglePiece(this, index, spectranglePiece);

    }


}

