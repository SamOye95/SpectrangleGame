package controller;


import client.ClientDatabase;
import model.SpectrangleGame;
import model.SpectranglePiece;
import model.SpectranglePlayer;
import network.Message;
import network.Peer;
import view.SpectrangleGameView;

import java.util.ArrayList;
import java.util.List;

public class SpectrangleClientGameController extends SpectrangleController {


    private SpectrangleGameView view;


    public SpectrangleClientGameController(ClientDatabase database) {
        super(database);
        this.view = new SpectrangleGameView();
    }


    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand()) {
            case "start":
                this.start(msg.getArgs());
                break;
            case "drawnTile":
                this.drawnTile(msg.getArgs().get(0), msg.getArgs().get(1));
                break;
            case "placedTile":
                this.placedTile(msg.getArgs().get(0), msg.getArgs().get(1), msg.getArgs().get(2));
                break;
            case "switchedTile":
                this.switchedTile(msg.getArgs().get(0), msg.getArgs().get(1), msg.getArgs().get(2));
                break;
            case "skippedMove":
                this.skippedMove(msg.getArgs().get(0));
                break;
            case "requestMove":
                this.requestMove();
                break;
            case "rotate":
                this.rotate(msg.getArgs().get(0));
                break;
            case "players":
                this.players(msg.getArgs());
                break;
            case "end":
                this.end();
                break;
            default:
                break;
        }
    }


    private void serverMessage(String msg) {
        System.out.println("Server: " + msg);
        System.out.print("> ");
    }

    public void start(List<String> args) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();

        List<SpectranglePlayer> players = new ArrayList<SpectranglePlayer>();

        for (String nickname : args) {
            if (nickname.equals(database.getPlayer().getPlayerName())) {
                players.add(database.getPlayer());
            } else {
                players.add(new SpectranglePlayer(nickname));
            }
        }

        SpectrangleGame game = new SpectrangleGame(players, null);
        database.setGame(game);
        this.view.setGame(game);
        this.view.take();
    }

    public void drawnTile(String nickname, String pieceString) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        List<SpectranglePlayer> players = database.getGame().getPlayers();

        for (SpectranglePlayer player : players) {
            if (nickname.equals(player.getPlayerName())) {
                player.takeSpectralPiece(pieceString);
            }
        }

        this.view.take();
    }

    public void placedTile(String nickname, String indexStr, String tileStr) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        SpectrangleGame game = database.getGame();
        SpectranglePlayer player = null;
        Integer index;

        for (SpectranglePlayer p : game.getPlayers()) {
            if (nickname.equals(p.getPlayerName())) {
                player = p;
                break;
            }
        }

        if (player == null) {
            return;
        }

        try {
            index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            return;
        }

        player.placeSpectranglePiece(index, tileStr);

        this.view.take();
    }

    public void switchedTile(String nickname, String oldTileStr, String newTileStr) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        SpectrangleGame game = database.getGame();
        SpectranglePlayer player = null;

        for (SpectranglePlayer p : game.getPlayers()) {
            if (nickname.equals(p.getPlayerName())) {
                player = p;
                break;
            }
        }

        if (player == null) {
            return;
        }

        player.switchPiece(oldTileStr, newTileStr);
        this.view.take();
    }

    public void requestMove() {
        this.serverMessage("It's your turn. Enter move.");
    }

    public void skippedMove(String nickname) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        SpectrangleGame game = database.getGame();
        SpectranglePlayer player = null;

        for (SpectranglePlayer p : game.getPlayers()) {
            if (nickname.equals(p.getPlayerName())) {
                player = p;
                break;
            }
        }

        if (player == null) {
            return;
        }

        player.skipMove();
        this.view.take();
    }

    public void end() {
        this.serverMessage("Game over!");
    }

    public void players(List<String> args) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        SpectrangleGame game = database.getGame();

        for (SpectranglePlayer player : game.getPlayers()) {
            if (!args.contains(player.getPlayerName())) {
                player.leaveGame();
            }
        }
    }

    public void rotate(String tileStr) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        SpectrangleGame game = database.getGame();
        SpectranglePlayer player = database.getPlayer();

        for (SpectranglePiece spectranglePiece : player.getSpectranglePieces()) {
            if (spectranglePiece.isEquivalent(tileStr)) {
                spectranglePiece.rotate();
            }
        }
        this.view.take();
    }

}
