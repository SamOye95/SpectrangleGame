package server;

import exceptions.PeerNotFoundException;
import interfaces.*;
import model.SpectranglePlayer;
import network.Peer;

public class ServerPeer implements {

    private ServerDatabase database;
    private Peer peer;


    public ServerPeer(ServerDatabase database) {
        this.database = database;
    }


    @Override
    public void run() {
        SpectranglePlayer player = new SpectranglePlayer(null, this.peer);

        try {
            this.database.insertUser(peer, player);
        } catch (PeerNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        peer.write("features");
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }

}