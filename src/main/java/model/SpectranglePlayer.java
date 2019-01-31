package model;

import network.Peer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpectranglePlayer {


    private SpectrangleGame game;
    private List<SpectranglePiece> spectranglePieces;
    private SpectrangleScoreBoard scoreBoard;

    private String playerName;
    private int score;

    private SpectrangleLobby lobby;
    private Peer peer;


    public SpectranglePlayer(String playerName) {
        this.playerName = playerName;
        this.spectranglePieces = new ArrayList<SpectranglePiece>();
        this.scoreBoard = new SpectrangleScoreBoard();
        this.score = 0;
    }

    public SpectranglePlayer(String playerName, Peer peer) {
        this.playerName = playerName;
        this.score = 0;
        this.peer = peer;
        this.scoreBoard = new SpectrangleScoreBoard();
        this.spectranglePieces = new ArrayList<SpectranglePiece>();


    }
    public List<SpectranglePiece> takeMaximumSpectranglePieces() {
        List<SpectranglePiece> spectranglePieces = new ArrayList<SpectranglePiece>();
        while (this.spectranglePieces.size() < 4) {
            spectranglePieces.add(this.takeSpectranglePiece());

        }
        return spectranglePieces;
    }

    public SpectranglePiece takeSpectranglePiece() {
        int numberOfPieces, index;

        numberOfPieces = this.spectranglePieces.size();
        if (numberOfPieces == 0) {
            return null;
        }

        index = new Random().nextInt(this.spectranglePieces.size());
        return this.spectranglePieces.remove(index);
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
        return this.spectranglePieces.get(this.spectranglePieces.size() - 1);
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

        for (int i = 0; i < 6; i++) {
            if (spectranglePieceString.equals(spectranglePiece.toString())) {
                break;
            }
            spectranglePiece.rotate();
        }

        return this.game.placeSpectranglePiece(this, index, spectranglePiece);

    }

    public int switchPiece(String spectranglePieceString) {
        SpectranglePiece spectranglePiece = null;

        for (SpectranglePiece sp : this.spectranglePieces) {
            if (sp.isEquivalent(spectranglePieceString)) {
                spectranglePiece = sp;
                break;
            }

        }

        if (spectranglePiece == null) {
            return 404;
        }
        return this.game.switchPiece(this, spectranglePiece);

    }


    public int switchPiece(String oldPiece, String newPiece) {
        SpectranglePiece spectranglePiece = null;

        for (SpectranglePiece sp : this.spectranglePieces) {
            if (sp.isEquivalent(oldPiece)) {
                spectranglePiece = sp;
                break;
            }
        }

        if (spectranglePiece == null) {
            return 404;
        }

        return this.game.switchPiece(this, spectranglePiece, newPiece);
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public int skipMove() {
        return this.game.skipMove(this);
    }

    public void leaveGame() {
        this.game.leaveGame(this);
    }

    public SpectrangleGame getGame() {
        return game;
    }

    public void setGame(SpectrangleGame game) {
        this.game = game;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public SpectrangleLobby getLobby() {
        return lobby;
    }

    public void setLobby(SpectrangleLobby lobby) {
        this.lobby = lobby;
    }

    public Peer getPeer() {
        return peer;
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public SpectrangleScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(SpectrangleScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public List<SpectranglePiece> getSpectranglePieces() {
        return spectranglePieces;
    }

    public void setSpectranglePieces(List<SpectranglePiece> spectranglePieces) {
        this.spectranglePieces = spectranglePieces;
    }
}

