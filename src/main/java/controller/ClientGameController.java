package controller;


import client.ClientDatabase;
import exceptions.NotEnoughPlayersException;
import model.Game;
import model.HumanPlayer;
import model.Player;
import model.Tile;
import network.Message;
import network.Peer;
import view.SpectrangleGameView;

import java.util.ArrayList;
import java.util.List;

public class ClientGameController extends Controller {


    private SpectrangleGameView view;


    public ClientGameController(ClientDatabase database) {
        super(database);
        this.view = new SpectrangleGameView();
    }

    /*
     * This method forwards the first word of the message, and identifies the command based on that
     * word. It then calls the correct function for that command
     * If the command cannot be forwarded, either by this function or one of the other functions,
     * then a message will be sent to the client stating that it sent an invalid command.
     *
     * @param peer
     * @param msg
     */
    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand()) {
            case "start": // start <nn1> <nn2> [nn3] [nn4]
                if (msg.getArgs().size() < Game.minPlayers) {
                    return;
                }
                this.start(msg.getArgs());
                break;
            case "takenPiece": // drawnTile <player> <tile>
                if (msg.getArgs().size() < 2) {
                    return;
                }
                this.takenPiece(msg.getArgs().get(0), msg.getArgs().get(1));
                break;
            case "placedTile": // placedTile <index> <tile>
                if (msg.getArgs().size() < 3) {
                    return;
                }
                this.placedTile(msg.getArgs().get(0), msg.getArgs().get(1), msg.getArgs().get(2));
                break;
            case "switchedTile": // switchedTile <index> <oldTile> <newTile>
                if (msg.getArgs().size() < 3) {
                    return;
                }
                this.switchedTile(msg.getArgs().get(0), msg.getArgs().get(1), msg.getArgs().get(2));
                break;
            case "skippedMove": // skippedMove <player>
                if (msg.getArgs().size() < 1) {
                    return;
                }
                this.skippedMove(msg.getArgs().get(0));
                break;
            case "requestMove":
                this.requestMove();
                break;
            case "rotate":
                if (msg.getArgs().size() < 1) {
                    return;
                }
                this.rotate(msg.getArgs().get(0));
                break;
            case "players": //players <nn1> [nn2] [nn3] [nn4]
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

        List<Player> players = new ArrayList<Player>();


        for (String playerName : args) {
            if (playerName.equalsIgnoreCase(database.getPlayer().getPlayerName())) {
                players.add(database.getPlayer());
            } else {
                players.add(new HumanPlayer(playerName));
            }
        }

        Game game = null;
        try {
            game = new Game(players, null);
        } catch (NotEnoughPlayersException e) {
            e.printStackTrace();
            return;
        }
        database.setGame(game);
        this.view.setGame(game);
        this.view.take(true);
    }

    /*
     *
     * @param playerName
     * @param pieceString
     */
    public void takenPiece(String playerName, String pieceString) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        List<Player> players = database.getGame().getPlayers();

        for (Player player : players) {
            if (playerName.equalsIgnoreCase(player.getPlayerName())) {
                player.takeTile(pieceString);
            }
        }

        this.view.take(true);
    }

    public void placedTile(String playerName, String indexStr, String tileStr) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        Game game = database.getGame();
        Player player = null;
        Integer index;

        for (Player p : game.getPlayers()) {
            if (playerName.equalsIgnoreCase(p.getPlayerName())) {
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

        player.placeTile(index, tileStr);

        this.view.take(true);
    }

    public void switchedTile(String playerName, String oldTileStr, String newTileStr) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        Game game = database.getGame();
        Player player = null;

        for (Player p : game.getPlayers()) {
            if (playerName.equalsIgnoreCase(p.getPlayerName())) {
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

    public void skippedMove(String playerName) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        Game game = database.getGame();
        Player player = null;

        for (Player p : game.getPlayers()) {
            if (playerName.equalsIgnoreCase(p.getPlayerName())) {
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
        Game game = database.getGame();

        for (Player player : game.getPlayers()) {
            if (!args.contains(player.getPlayerName())) {
                player.leaveGame();
            }
        }
    }

    public void rotate(String tileStr) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        Player player = database.getPlayer();

        for (Tile spectranglePiece : player.getTiles()) {
            if (spectranglePiece.isSamePiece(tileStr)) {
                spectranglePiece.rotate();
            }
        }
        this.view.take(false);
    }

}
