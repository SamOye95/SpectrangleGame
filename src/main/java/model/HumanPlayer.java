package model;

import network.Peer;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName, Peer peer) {
        super(playerName, peer);
    }

    public HumanPlayer(String playername) {
        super(playername);
    }

    public void requestMove() {
        this.getPeer().write("requestMove");
    }

}
