package model;


import exceptions.PeerNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SpectrangleLobby {

    private List<SpectranglePlayer> players;
    private SpectranglePlayer host;
    private SpectrangleGame game;

    public SpectrangleLobby(SpectranglePlayer player) throws PeerNotFoundException {
        if (player == null) {
            throw new PeerNotFoundException("Player Not Found");
        }

        this.players = new ArrayList<SpectranglePlayer>();
        this.host = player;
        this.addPlayer(player);
    }

    public SpectrangleGame startGame() throws PeerNotFoundException {

        if (this.players.size() < 2) {
            throw new PeerNotFoundException("Not enough players to start game");
        }
        return game;
    }

    public boolean addPlayer(SpectranglePlayer player) {
        if (player == null) {
            return false;
        }

        player.setLobby(this);
        this.players.add(player);
        return true;
    }


    public void removePlayer(SpectranglePlayer player) {
        this.players.remove(player);
    }

    public boolean available() {
        return this.players.size() < 4 && this.game == null;
    }

    public SpectrangleGame getGame() {
        return game;
    }

    public void setGame(SpectrangleGame game) {
        this.game = game;
    }

    public List<SpectranglePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<SpectranglePlayer> players) {
        this.players = players;
    }

}
