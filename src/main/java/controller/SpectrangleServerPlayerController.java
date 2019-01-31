package controller;

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
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand()) {
            case "nickname":
                this.nickname(peer, msg.getStringArgs());
                break;
            case "features":
                this.features(peer, msg.getArgs());
                break;
            default:
                break;
        }
    }


    public void nickname(Peer peer, String nickname) {
        SpectranglePlayer player = ((ServerDatabase) this.getDatabase()).getPlayer(peer);
        ServerDatabase database = (ServerDatabase) this.getDatabase();

        if (player == null) {
            return;
        }

        if (player.getGame() != null) {
            peer.write("403 You're not allowed to change your nickname during the game.");
            return;
        }

        for (SpectranglePlayer p : database.getPlayers()) {
            if (nickname.equals(p.getPlayerName())) {
                peer.write("403 That nickname has already been chosen. Pick another one");
                return;
            }
        }

        player.setPlayerName(nickname);
        peer.write("200 " + "Waiting for more players...");
    }

    public void features(Peer peer, List<String> args) {

    }

}
