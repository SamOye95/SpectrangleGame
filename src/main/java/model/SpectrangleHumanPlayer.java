package model;

import network.Peer;

public class SpectrangleHumanPlayer extends SpectranglePlayer {

    public SpectrangleHumanPlayer(String playerName, Peer peer) {
        super(playerName, peer);
    }

    public SpectrangleHumanPlayer(String playername) {
        super(playername);
    }

    public void requestMove() {
        this.getPeer().write("requestMove");
    }

}
