package controller;

import client.ClientDatabase;
import model.HumanPlayer;
import model.Player;
import network.Database;
import network.Message;
import network.Peer;

import java.util.List;

public class ClientPlayerController extends Controller {


    public ClientPlayerController(Database database) {
        super(database);
    }


    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand()) {
            case "features": // features [feature 1] [feature2] [...]
                this.features(msg.getArgs());
                break;
            case "playerName": //playerName <playerName>
                this.playerName(msg.getArgs().get(0));
                break;
            default:
                break;
        }
    }


    private void serverMessage(String msg) {
        System.out.println("Server: " + msg);
        System.out.print("> ");
    }

    public void features(List<String> args) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        database.getPeer().write("features");
    }

    public void playerName(String playerName) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        Player player = database.getPlayer();

        if (player != null) {
            player.setPlayerName(playerName);
        } else {
            player = new HumanPlayer(playerName);
            database.setPlayer(player);
        }
    }


}
