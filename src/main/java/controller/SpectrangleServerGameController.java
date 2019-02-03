package controller;

import model.SpectrangleGame;
import model.SpectranglePlayer;
import network.Message;
import network.Messenger;
import network.Peer;
import server.ServerDatabase;

public class SpectrangleServerGameController extends SpectrangleController {

    public SpectrangleServerGameController(ServerDatabase database) {
        super(database);
    }


    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand().toLowerCase()) {
            case "placePiece":
                this.placePiece(peer, msg.getArgs().get(0), msg.getArgs().get(1));
                break;
            case "switchPiece":
                this.switchPiece(peer, msg.getArgs().get(0));
                break;
            case "skipMove":
                this.skipMove(peer);
                break;
            case "leave":
                this.leave(peer);
                break;
            default:
                break;
        }
    }


    public void placePiece(Peer peer, String indexStr, String tileStr) {
        Integer index;

        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            peer.write("404 Invalid index. Try again.");
            return;
        }

        ServerDatabase database = (ServerDatabase) this.getDatabase();
        SpectranglePlayer player = database.getPlayer(peer);
        int status = player.placeSpectranglePiece(index, tileStr);

        switch (status) {
            case 403:
                peer.write("403 It's not your turn.");
                break;
            case 404:
                peer.write("404 You don't have that tile or the index is not valid. Try again.");
                break;
            default:
                Messenger.broadcast(player.getGame().getPlayers(), "placedTile " + player.getPlayerName() + " " + index + " " + tileStr);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void switchPiece(Peer peer, String tileStr) {
        ServerDatabase database = (ServerDatabase) this.getDatabase();
        SpectranglePlayer player = database.getPlayer(peer);

        int status = player.switchPiece(tileStr);

        switch (status) {
            case 403:
                peer.write("403 It's either not your turn or you can place a tile.");
                break;
            case 404:
                peer.write("404 You don't have that tile or it can be played. Try again.");
                break;
            default:
                break;
        }
    }

    public void skipMove(Peer peer) {
        ServerDatabase database = (ServerDatabase) this.getDatabase();
        SpectranglePlayer player = database.getPlayer(peer);

        int status = player.skipMove();

        switch (status) {
            case 403:
                peer.write("403 It's either not your turn or you can place a tile.");
                break;
            default:
                break;
        }
    }

    public void leave(Peer peer) {
        ServerDatabase database = (ServerDatabase) this.getDatabase();
        SpectranglePlayer player = database.getPlayer(peer);
        SpectrangleGame game = player.getGame();

        if (game == null) {
            peer.write("403 You're not currently in a game.");
        }

        player.leaveGame();

    }
}