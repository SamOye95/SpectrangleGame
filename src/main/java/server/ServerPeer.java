package server;

import exceptions.PeerNotFoundException;
import model.HumanPlayer;
import network.Peer;
import network.Setup;

public class ServerPeer implements Setup {

    private ServerDatabase database;
    private Peer peer;


    public ServerPeer(ServerDatabase database) {
        this.database = database;
    }


    @Override
    public void run() {
        HumanPlayer player = new HumanPlayer(null, this.peer);

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