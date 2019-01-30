package client;

import model.SpectrangleGame;
import model.SpectranglePlayer;
import network.InputThread;
import network.Peer;

public class ClientDatabase {


    private Peer peer;
    private InputThread inputThread;
    private SpectrangleGame game;
    private SpectranglePlayer player;


    public ClientDatabase() {

    }


    public Peer getPeer() {
        return peer;
    }

    public void setPeer(Peer peer) {
        this.peer = peer;
    }

    public InputThread getInputThread() {
        return inputThread;
    }

    public void setInputThread(InputThread inputThread) {
        this.inputThread = inputThread;
    }

    public SpectrangleGame getGame() {
        return game;
    }

    public void setGame(SpectrangleGame game) {
        this.game = game;
    }

    public SpectranglePlayer getPlayer() {
        return player;
    }

    public void setPlayer(SpectranglePlayer player) {
        this.player = player;
    }
}
