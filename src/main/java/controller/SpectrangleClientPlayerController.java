package controller;

import client.ClientDatabase;
import model.SpectrangleHumanPlayer;
import model.SpectranglePlayer;
import network.Database;
import network.Message;
import network.Peer;

import java.util.List;

public class SpectrangleClientPlayerController extends SpectrangleController {


    public SpectrangleClientPlayerController(Database database) {
        super(database);
    }


    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand().toLowerCase()) {
            case "features":
                this.features(msg.getArgs());
                break;
            case "playerName":
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
        SpectranglePlayer player = database.getPlayer();

        if (player != null) {
            player.setPlayerName(playerName);
        } else {
            player = new SpectrangleHumanPlayer(playerName);
            database.setPlayer(player);
        }
    }


}
