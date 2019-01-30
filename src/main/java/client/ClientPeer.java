package client;

import network.Peer;

public class ClientPeer {


    private ClientDatabase database;
    private Peer peer;

    public ClientPeer(ClientDatabase database) {
        this.database = database;
    }


    @Override
    public void run() {

    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }
}
