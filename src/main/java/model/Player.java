package model;

import network.Attribute;
import network.Peer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class Player extends Observable {

    // ------------------ Instance variables ----------------
    private Game game;
    private List<Tile> tiles;

    //------------------ATTRIBUTES---------------------
    private String playerName;
    private int score;
    private Peer peer;

    // -- Constructor -----------------------------------------------

    /*
     * Constructs a player
     * @param playerName
     * @requires !playerName.equals(null);
     * @ensures this.getPlayername().equals(playerName);
     * @ensures this.getScore() == 0 && !this.getTiles().equals(null);
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.tiles = new ArrayList<>();
        this.score = 0;
    }

    /*
     * Constructs a player
     *
     * @param playerName
     * @param peer
     * @requires !playerName.equals(null) && !peer.equals(null);
     * @ensures this.getPlayerName().equals(playerName) && this.getPeer().equals(peer);
     * @ensures this.getScore() == 0 && !this.getTiles().equals(null);
     */
    public Player(String playerName, Peer peer) {
        this.playerName = playerName;
        this.score = 0;
        this.peer = peer;
        this.tiles = new ArrayList<>();


    }

    /*
     * return a list of the maximum taken pieces
     * @return
     * @ensures !\result.equals(null);
     * @ensures this.getTiles().size() == 4;
     */
    public List<Tile> takeMaximumTiles() {
        List<Tile> spectranglePieces = new ArrayList<Tile>();
        while (this.tiles.size() < 4) {
            spectranglePieces.add(this.takeTile());

        }
        return spectranglePieces;
    }

    /*
     *
     * @return
     * @ensures !this.getTiles().equals(\old(this.getTiles()));
     * @ensures \result != null;
     */
    public Tile takeTile() {
        if (this.tiles.size() >= 4) {
            return null;
        }

        Tile piece = this.game.getBag().takeTile();
        this.tiles.add(piece);

        Attribute attribute = new Attribute("takenPiece");
        attribute.addPlayer(this);
        attribute.addTile(piece);
        this.warnObservers(attribute);

        return piece;
    }

    /*
     * @param tile
     * @return
     * @requires !tile.equals(null) && this.getGame().getBag().getTiles().contains(tile);
     * @ensures !this.getTiles().equals(\old(this.getTiles()));
     */
    public Tile takeTile(String tile) {

        if (this.tiles.size() >= 4) {
            return null;
        }

        Tile sp = this.game.getBag().takeTile(tile);
        this.tiles.add(sp);
        return sp;
    }

    /*
     * returns the last piece
     * @return
     * @ensures !\result.equals(null);
     */
    public Tile lastTile() {
        return this.tiles.get(this.tiles.size() - 1);
    }

    /*
     * @param index
     * @param tileString
     * @return
     * @requires (index > = 0 & & index < = this.getGame ().getBoard().LENGTH && !( tileString.equals(null)));
     * @ensures (\ exists Tile t ; this.getTiles ().contains(t) && t.isEquivalent(tileString); \result != 404 );
     * @ensures \result != 404 ==> !this.getGame().getBoard().equals(\old(this.getGame().getBoard()));
     */
    public int placeTile(int index, String tileString) {
        Tile tile = null;
        int status;

        for (Tile sp : this.tiles) {
            if (sp.isSamePiece(tileString)) {
                tile = sp;
            }
        }

        if (tile == null) {

            return 404;
        }

        for (int i = 0; i < 6; i++) {
            if (tileString.equalsIgnoreCase(tile.toString())) {
                break;
            }
            tile.rotate();
        }

        status = this.game.placeTile(this, index, tile);
        if (status == 0) {
            Attribute attr = new Attribute("placedTile");
            attr.addPlayer(this);
            attr.addIndex(index);
            attr.addTile(tile);
            this.warnObservers(attr);
        }

        return status;
    }

    /*
     *
     * @param tileString
     * @return
     * @requires !tileStr.equals(null);
     * @ensures (\ exists Tile t ; this.getTiles ().contains(t) && !t.isEquivalent(tileStr) ; \result == 404 );
     * @ensures  \result!=404 ==> !this.getTiles().equals(\old(this.getTiles()));
     */
    public int switchPiece(String tileString) {
        Tile spectranglePiece = null;
        int status;

        for (Tile sp : this.tiles) {
            if (sp.isSamePiece(tileString)) {
                spectranglePiece = sp;
                break;
            }

        }

        if (spectranglePiece == null) {
            return 404;
        }
        status = this.game.switchPiece(this, spectranglePiece);

        if (status == 0) {
            Attribute attribute = new Attribute("switchPiece");
            attribute.addTile(spectranglePiece);
            attribute.addTile(this.lastTile());
            attribute.addPlayer(this);
            this.warnObservers(attribute);
        }

        return status;
    }

    /*
     *
     * @param oldPiece
     * @param newPiece
     * @return
     * @requires !oldPiece.equals(null) && !newPiece.equals(null);
     * @ensures (\ exists Tile t ; this.getTiles ().contains(t) && !t.isEquivalent(oldTileStr) ; \result == 404 ) ;
     * @ensures \result!=404 ==> !this.getTiles().equals(\old(this.getTiles()));
     *
     *
     */
    public int switchPiece(String oldPiece, String newPiece) {
        Tile tile = null;

        for (Tile sp : this.tiles) {
            if (sp.isSamePiece(oldPiece)) {
                tile = sp;
                break;
            }
        }

        if (tile == null) {
            return 404;
        }

        return this.game.switchPiece(this, tile, newPiece);
    }

    /*
     *
     * @param points
     *  @requires points > 0;
     *  @ensures this.getScore() > \old(this.getScore());
     */
    public void addPoints(int points) {
        this.score += points;
    }

    /*
     *
     * @return
     *
     * @requires this.getGame().getTurn().equals(this);
     * @ensures !this.getGame().getTurn().equals(this);
     */
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

    /*
     * @requires !this.getGame().equals(null);
     * @ensures this.getGame().equals(null);
     */

    public void leaveGame() {
        this.game.leaveGame(this);
        Attribute attr = new Attribute("playerLeft");
        attr.addPlayer(this);
        this.warnObservers(attr);
    }

    public abstract void requestMove();

    /*
     *
     * @return
     * @pure
     * @ ensures !\result.equals(null);
     */
    public Game getGame() {
        return game;
    }

    /*
     * @requires !game.equals(null);
     * @ensures this.getGame().equals(game);
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
        this.addObserver(game);
    }

    /*
     * @pure
     * @ensures !\result.equals(null);
     * @return
     */
    public Integer getScore() {
        return score;
    }

    /*
     * @requires score != 0;
     * @ensures getScore()==score;
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /*
     * @pure
     * @ensures !\result.equals(null);
     *
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /*
     * @requires !playerName.equals(null);
     * @ensures this.getPlayerName().equals(null);
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /*
     * @pure
     * @ensures !\result.equals(null);
     *
     * @return
     */
    public Peer getPeer() {
        return peer;
    }

    /*
     * @requires !peer.equals(null);
     * @ensures this.getPeer().equals(peer);
     * @param peer
     */
    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    /*
     * @pure
     * @ensures \result.size()>=0 && \result.size()<=4;
     * @return
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /*
     *  @requires !tiles.equals(null);
     * @ensures this.getTiles().equals(tiles);
     * @param tiles
     */
    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    /*
     * @requires !attr.equals(null);
     * @param attr
     */
    public void warnObservers(Attribute attr) {
        this.setChanged();
        this.notifyObservers(attr);
    }
}

