package server;


import exceptions.PeerNotFoundException;
import model.SpectrangleGame;
import model.SpectranglePlayer;
import network.Database;
import network.Peer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerDatabase implements Database {

    private List<Peer> peers;
    private List<SpectranglePlayer> players;
    private Map<Peer, SpectranglePlayer> users;
    private List<SpectrangleGame> games;


    public ServerDatabase() {
        this.peers = new ArrayList<Peer>();
        this.players = new ArrayList<SpectranglePlayer>();
        this.users = new HashMap<Peer, SpectranglePlayer>();
        this.games = new ArrayList<SpectrangleGame>();
    }


    public synchronized SpectranglePlayer getPlayer(Peer peer) {
        return this.users.get(peer);
    }

    public synchronized void insertGame(SpectrangleGame game) {
        this.games.add(game);
    }

    public synchronized void removeGame(SpectrangleGame game) {
        this.games.remove(game);
    }

    public synchronized void insertPeer(Peer peer) {
        this.peers.add(peer);
    }

    public synchronized void removePeer(Peer peer) {
        this.peers.remove(peer);
    }

    public synchronized void insertUser(Peer peer, SpectranglePlayer player) throws PeerNotFoundException {
        if (!this.peers.contains(peer)) {
            throw new PeerNotFoundException("Peer not found!");
        }
        this.players.add(player);
        this.users.put(peer, player);
    }

    public synchronized void removeUser(Peer peer) {
        this.users.remove(peer);
    }

    public synchronized List<SpectranglePlayer> getIdlePlayers() {
        List<SpectranglePlayer> idlePlayers = new ArrayList<SpectranglePlayer>();

        for (SpectranglePlayer player : this.players) {
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

    public List<SpectranglePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<SpectranglePlayer> players) {
        this.players = players;
    }
}


