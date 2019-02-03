package network;

import model.SpectranglePiece;
import model.SpectranglePlayer;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private List<SpectranglePiece> tiles;
    private List<SpectranglePlayer> players;
    private List<Integer> indexes;
    private String action;

    public Attribute(String action) {
        this.action = action;
        this.tiles = new ArrayList<SpectranglePiece>();
        this.players = new ArrayList<SpectranglePlayer>();
        this.indexes = new ArrayList<Integer>();
    }

    public void addTile(SpectranglePiece tile) {
        this.tiles.add(tile);
    }

    public void addPlayer(SpectranglePlayer player) {
        this.players.add(player);
    }

    public void addIndex(Integer index) {
        this.indexes.add(index);
    }

    public List<SpectranglePiece> getTiles() {
        return tiles;
    }

    public void setTiles(List<SpectranglePiece> tiles) {
        this.tiles = tiles;
    }

    public List<SpectranglePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<SpectranglePlayer> players) {
        this.players = players;
    }

    public List<Integer> getIndex() {
        return indexes;
    }

    public void setIndex(List<Integer> index) {
        this.indexes = index;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
