package model;

import network.Attribute;
import network.Peer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class SpectranglePlayer extends Observable {


    private SpectrangleGame game;
    private List<SpectranglePiece> spectranglePieces;
    private String playerName;
    private int score;

    private SpectrangleLobby lobby;
    private Peer peer;

    /**
     * Constructs a player
     *
     * @param playerName
     */
    public SpectranglePlayer(String playerName) {
        this.playerName = playerName;
        this.spectranglePieces = new ArrayList<SpectranglePiece>();
        this.score = 0;
    }

    public SpectranglePlayer(String playerName, Peer peer) {
        this.playerName = playerName;
        this.score = 0;
        this.peer = peer;
        this.spectranglePieces = new ArrayList<SpectranglePiece>();


    }

    /**
     * return a list of the maximum pieces
     * @return
     */
    public List<SpectranglePiece> takeMaximumSpectranglePieces() {
        List<SpectranglePiece> spectranglePieces = new ArrayList<SpectranglePiece>();
        while (this.spectranglePieces.size() < 4) {
            spectranglePieces.add(this.takeSpectranglePiece());

        }
        return spectranglePieces;
    }

    /**
     * Randomly takes spectrangle piece
     * @return
     */
    public SpectranglePiece takeSpectranglePiece() {
        if (this.spectranglePieces.size() >= 4) {
            return null;
        }

        SpectranglePiece piece = this.game.getBag().takeSpectranglePiece();
        this.spectranglePieces.add(piece);

        Attribute attribute = new Attribute("takenPiece");
        attribute.addPlayer(this);
        attribute.addTile(piece);

        return piece;
    }

    public SpectranglePiece takeSpectralPiece(String spectranglePiece) {

        if (this.spectranglePieces.size() >= 4) {
            return null;
        }

        SpectranglePiece sp = this.game.getBag().takeSpectranglePiece(spectranglePiece);
        this.spectranglePieces.add(sp);
        return sp;
    }

    /**
     * returns the last piece
     * @return
     */
    public SpectranglePiece lastSpectranglePiece() {
        return this.spectranglePieces.get(this.spectranglePieces.size() - 1);
    }

    public int placeSpectranglePiece(int index, String spectranglePieceString) {
        SpectranglePiece spectranglePiece = null;
        int status;

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

        status = this.game.placeSpectranglePiece(this, index, spectranglePiece);
        if (status == 0) {
            Attribute attr = new Attribute("placedTile");
            attr.addPlayer(this);
            attr.addIndex(index);
            attr.addTile(spectranglePiece);
            this.warnObservers(attr);
        }

        return status;
    }

    public int switchPiece(String spectranglePieceString) {
        SpectranglePiece spectranglePiece = null;

        for (SpectranglePiece sp : this.spectranglePieces) {
            if (sp.isSamePiece(spectranglePieceString)) {
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
        int status;

        for (SpectranglePiece sp : this.spectranglePieces) {
            if (sp.isSamePiece(oldPiece)) {
                spectranglePiece = sp;
                break;
            }
        }

        if (spectranglePiece == null) {
            return 404;
        }

        status = this.game.switchPiece(this, spectranglePiece);

        if (status == 0) {
            Attribute attr = new Attribute("switchedTile");
            attr.addTile(spectranglePiece);
            attr.addTile(this.lastSpectranglePiece());
            attr.addPlayer(this);
            this.warnObservers(attr);
        }

        return status;
    }

    public void addPoints(int points) {
        this.score += points;
    }

    public int skipMove() {
        int status;
        status = this.game.skipMove(this);

        if (status == 0) {
            Attribute attr = new Attribute("skippedMove");
            attr.addPlayer(this);
            this.warnObservers(attr);
        }

        return status;
    }

    public void leaveGame() {
        this.game.leaveGame(this);
        Attribute attr = new Attribute("playerLeft");
        attr.addPlayer(this);
        this.warnObservers(attr);
    }

    public abstract void requestMove();

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

    public List<SpectranglePiece> getSpectranglePieces() {
        return spectranglePieces;
    }

    public void setSpectranglePieces(List<SpectranglePiece> spectranglePieces) {
        this.spectranglePieces = spectranglePieces;
    }

    public void warnObservers(Attribute attr) {
        this.setChanged();
        this.notifyObservers(attr);
    }
}

