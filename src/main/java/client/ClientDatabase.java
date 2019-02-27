package client;

import model.Game;
import model.Player;
import network.Database;
import network.InputThread;
import network.Peer;

public class ClientDatabase implements Database {


    private Peer peer;
    private InputThread inputThread;
    private Game game;
    private Player player;


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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
