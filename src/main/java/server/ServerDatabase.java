package server;


import exceptions.PeerNotFoundException;
import model.Game;
import model.Player;
import network.Database;
import network.Peer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerDatabase implements Database {

    private List<Peer> peers;
    private List<Player> players;
    private Map<Peer, Player> users;
    private List<Game> games;


    public ServerDatabase() {
        this.peers = new ArrayList<>();
        this.players = new ArrayList<>();
        this.users = new HashMap<>();
        this.games = new ArrayList<>();
    }


    public synchronized Player getPlayer(Peer peer) {
        return this.users.get(peer);
    }

    public synchronized void insertGame(Game game) {
        this.games.add(game);
    }

    public synchronized void removeGame(Game game) {
        this.games.remove(game);
    }

    public synchronized void insertPeer(Peer peer) {
        this.peers.add(peer);
    }

    public synchronized void removePeer(Peer peer) {
        this.peers.remove(peer);
    }

    public synchronized void insertUser(Peer peer, Player player) throws PeerNotFoundException {
        if (!this.peers.contains(peer)) {
            throw new PeerNotFoundException("Peer not found!");
        }
        this.players.add(player);
        this.users.put(peer, player);
    }

    public synchronized void removeUser(Peer peer) {
        this.users.remove(peer);
    }

    public synchronized List<Player> getIdlePlayers() {
        List<Player> idlePlayers = new ArrayList<Player>();

        for (Player player : this.players) {
            if (player.getGame() == null && player.getPlayerName() != null) {
                idlePlayers.add(player);
            }
        }
        return idlePlayers;
    }


    public synchronized List<Peer> getPeers() {
        return peers;
    }

    public synchronized void setPeers(List<Peer> peers) {
        this.peers = peers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}


