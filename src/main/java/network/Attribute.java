package network;

import model.Player;
import model.Tile;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private List<Tile> tiles;
    private List<Player> players;
    private List<Integer> indexes;
    private String action;

    public Attribute(String action) {
        this.action = action;
        this.tiles = new ArrayList<>();
        this.players = new ArrayList<>();
        this.indexes = new ArrayList<>();
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addIndex(Integer index) {
        this.indexes.add(index);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Integer> getIndex() {
        return indexes;
    }

    public String getAction() {
        return action;
    }

}
