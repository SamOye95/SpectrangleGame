package controller;


import client.ClientDatabase;
import model.SpectrangleGame;
import model.SpectrangleHumanPlayer;
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
        switch (msg.getCommand().toLowerCase()) {
            case "start":
                if (msg.getArgs().size() < SpectrangleGame.minPlayers) return;
                this.start(msg.getArgs());
                break;
            case "takenPiece":
                if (msg.getArgs().size() < 2) return;
                this.takenPiece(msg.getArgs().get(0), msg.getArgs().get(1));
                break;
            case "placedTile":
                if (msg.getArgs().size() < 3) return;
                this.placedTile(msg.getArgs().get(0), msg.getArgs().get(1), msg.getArgs().get(2));
                break;
            case "switchedTile":
                if (msg.getArgs().size() < 3) return;
                this.switchedTile(msg.getArgs().get(0), msg.getArgs().get(1), msg.getArgs().get(2));
                break;
            case "skippedMove":
                if (msg.getArgs().size() < 1) return;
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
                players.add(new SpectrangleHumanPlayer(nickname));
            }
        }

        SpectrangleGame game = new SpectrangleGame(players, null);
        database.setGame(game);
        this.view.setGame(game);
        this.view.take(true);
    }

    public void takenPiece(String nickname, String pieceString) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        List<SpectranglePlayer> players = database.getGame().getPlayers();

        for (SpectranglePlayer player : players) {
            if (nickname.equals(player.getPlayerName())) {
                player.takeSpectralPiece(pieceString);
            }
        }

        this.view.take(true);
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

        this.view.take(true);
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
        this.view.take(true);
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
        this.view.take(true);
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
            if (spectranglePiece.isSamePiece(tileStr)) {
                spectranglePiece.rotate();
            }
        }
        this.view.take(false);
    }

}
