package controller;

import model.SpectranglePieceOrientation;
import model.SpectranglePlayer;
import network.Message;
import network.Peer;
import server.ServerDatabase;

import java.util.List;

public class SpectrangleServerPlayerController extends SpectrangleController {


    public SpectrangleServerPlayerController(ServerDatabase database) {
        super(database);
    }


    @Override
    public void forward(Peer peer, Message msg, SpectranglePieceOrientation orientation) {
        switch (msg.getCommand().toLowerCase()) {
            case "playerName":
                this.playerName(peer, msg.getStringArgs());
                break;
            case "client_features":
                this.features(peer, msg.getArgs());
                break;
            default:
                break;
        }
    }


    public void playerName(Peer peer, String playerName) {
        SpectranglePlayer player = ((ServerDatabase) this.getDatabase()).getPlayer(peer);
        ServerDatabase database = (ServerDatabase) this.getDatabase();

        if (player == null) {
            return;
        }

        if (player.getGame() != null) {
            peer.write("1 You're not allowed to change your playerName during the game.");
            return;
        }

        for (SpectranglePlayer p : database.getPlayers()) {
            if (playerName.equals(p.getPlayerName())) {
                peer.write("1 That playerName has already been chosen. Pick another one");
                return;
            }
        }

        player.setPlayerName(playerName);
        peer.write("200 " + "Waiting for more players...");
    }

    public void features(Peer peer, List<String> args) {

    }

}
