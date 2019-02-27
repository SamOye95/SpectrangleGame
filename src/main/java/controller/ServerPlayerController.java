package controller;

import model.Player;
import network.Message;
import network.Peer;
import server.ServerDatabase;

import java.util.List;

public class ServerPlayerController extends Controller {


    public ServerPlayerController(ServerDatabase database) {
        super(database);
    }


    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand()) {
            case "playerName":
                if (msg.getArgs().size() < 1) {
                    return;
                }
                this.playerName(peer, msg.getStringArgs());
                break;
            case "features":
                this.features(peer, msg.getArgs());
                break;
            default:
                break;
        }
    }


    public void playerName(Peer peer, String playerName) {
        Player player = ((ServerDatabase) this.getDatabase()).getPlayer(peer);
        ServerDatabase database = (ServerDatabase) this.getDatabase();

        if (player == null) {
            return;
        }

        if (player.getGame() != null) {
            peer.write("403 You're not allowed to change your nickname during the game.");
            return;
        }

        for (Player p : database.getPlayers()) {
            if (playerName.equalsIgnoreCase(p.getPlayerName())) {
                peer.write("403 That nickname has already been chosen. Pick another one");
                return;
            }
        }

        player.setPlayerName(playerName);
        peer.write("200 " + "Waiting for more players...");
    }

    public void features(Peer peer, List<String> args) {

    }

}
