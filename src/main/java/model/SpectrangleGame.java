package model;


import network.Attribute;
import network.Messenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class SpectrangleGame implements Runnable, Observer {

    public static final int minPlayers = 2;
    public static final int maxPlayers = 4;

    private SpectrangleBoard board;
    private RandomSpectrangleBag bag;
    private List<SpectranglePlayer> players;

    private Thread thread;
    private SpectranglePlayer turn;
    private SpectranglePlayerStatus status;

    public SpectrangleGame(List<SpectranglePlayer> players, SpectranglePlayer host) {

        if (players.size() < SpectrangleGame.minPlayers || players.size() > SpectrangleGame.maxPlayers) {
            //Throw an exception
        }

        if (!players.contains(host)) {
            //Throw an exception
        }

        this.players = players;

    }

    /**
     * Starts game
     */
    public void start() {
        this.thread = new Thread(this);
        this.thread.start();
        this.status = SpectranglePlayerStatus.RUNNING;
    }

    /**
     * @return
     * @pure
     * @ensures (! \ result.equals ( null))
     */
    public String getPlayersStr() {
        String str = "";
        for (SpectranglePlayer player : this.players) {
            str += player.getPlayerName() + " ";
        }

        return str.trim();
    }

    /**
     * @param player
     * @param index
     * @param piece
     * @return
     * @requires this.getPlayers().contains(player) && player.getSpectranglePieces.contains(piece)
     */
    public int placeSpectranglePiece(SpectranglePlayer player, int index, SpectranglePiece piece) {
        Integer points;

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        points = this.board.placeSpectranglePiece(piece, index);
        if (points == -1) {
            return 404;
        }

        player.getSpectranglePieces().remove(piece);
        player.addPoints(points);
        this.turn = this.nextPlayer();

        return 0;
    }

    public int switchPiece(SpectranglePlayer player, SpectranglePiece spectranglePiece) {

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        if (this.canMakeMove(player)) {
            return 403;
        }

        player.getSpectranglePieces().remove(spectranglePiece);
        this.bag.getPieces().add(spectranglePiece);
        player.takeSpectranglePiece();
        this.turn = this.nextPlayer();
        return 0;
    }

    public int switchPiece(SpectranglePlayer player, SpectranglePiece piece, String newTileStr) {

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        if (this.canMakeMove(player)) {
            return 403;
        }

        player.getSpectranglePieces().remove(piece);
        this.bag.getPieces().add(piece);
        player.takeSpectralPiece(newTileStr);
        this.turn = this.nextPlayer();
        return 0;
    }

    public int skipMove(SpectranglePlayer player) {
        if (this.canMakeMove(player)) {
            return 403;
        }

        this.turn = this.nextPlayer();
        return 0;
    }

    public boolean canMakeMove(SpectranglePlayer player) {
        for (SpectranglePiece piece : player.getSpectranglePieces()) {
            if (this.board.canBePlaced(piece)) {
                return true;
            }
        }
        return false;
    }

    public SpectranglePlayer nextPlayer() {
        Integer index = this.players.indexOf(this.turn) + 1;
        SpectranglePlayer next = this.players.get(0);

        if (index < this.players.size()) {
            next = this.players.get(index);
        }

        return next;
    }

    public void leaveGame(SpectranglePlayer player) {
        this.bag.getPieces().addAll(player.getSpectranglePieces());
        player.setSpectranglePieces(new ArrayList<SpectranglePiece>());
        player.setScore(0);
        this.players.remove(player);
    }

    private void init() {
        this.board = new SpectrangleBoard();
        this.bag = new RandomSpectrangleBag();
        this.status = SpectranglePlayerStatus.NOT_STARTED;

        for (SpectranglePlayer player : this.players) {
            player.setGame(this);
            player.setScore(0);
        }

        this.turn = players.get(0);
    }


    public RandomSpectrangleBag getBag() {
        return bag;
    }

    public void setBag(RandomSpectrangleBag bag) {
        this.bag = bag;
    }

    public List<SpectranglePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<SpectranglePlayer> players) {
        this.players = players;
    }

    public SpectrangleBoard getBoard() {
        return board;
    }

    public void setBoard(SpectrangleBoard board) {
        this.board = board;
    }

    public SpectranglePlayer getTurn() {
        return turn;
    }

    public void setTurn(SpectranglePlayer turn) {
        this.turn = turn;
    }

    @Override
    public void run() {

        //FIRST STEP - TELL ALL PLAYERS THAT THE GAME STARTED AND IN WHICH ORDER THE PLAYERS ARE
        Messenger.broadcast(this.players, "start " + this.getPlayersStr());

        //SECOND STEP - DRAW TILES FOR ALL OF THE PLAYERS
        for (SpectranglePlayer player : this.players) {
            List<SpectranglePiece> pieces = player.takeMaximumSpectranglePieces();
            for (SpectranglePiece piece : pieces) {
                Messenger.broadcast(this.players, "takenPiece " + player.getPlayerName() + " " + piece.toString());
            }
        }

        while (this.status.equals(SpectranglePlayerStatus.RUNNING)) {
            for (SpectranglePlayer player : this.players) {
                player.getPeer().write("requestMove");
                while (this.turn.equals(player)) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (this.getBag().getPieces().isEmpty()) {
                    this.status = SpectranglePlayerStatus.FINISHED;
                    Messenger.broadcast(this.players, "end");
                    break;
                } else {
                    player.takeSpectranglePiece();
                }
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        Attribute attr = (Attribute) arg1;
        SpectranglePlayer player = attr.getPlayers().get(0);
        SpectranglePiece piece;

        if (player.getPeer() == null) {
            return;
        }

        switch (attr.getAction()) {
            case "placedTile":
                Integer index = attr.getIndex().get(0);
                piece = attr.getTiles().get(0);
                Messenger.broadcast(this.players, "placedTile " + player.getPlayerName() + " " + index + " " + piece.toString());
                break;
            case "takenPiece":
                piece = attr.getTiles().get(0);
                Messenger.broadcast(this.players, "takenPiece " + player.getPlayerName() + " " + piece.toString());
                break;
            case "switchedTile":
                SpectranglePiece oldTile = attr.getTiles().get(0);
                SpectranglePiece newTile = attr.getTiles().get(1);
                Messenger.broadcast(this.players, "switchedTile " + player.getPlayerName() + " " + oldTile + " " + newTile);
            case "skippedMove":
                Messenger.broadcast(this.players, "skippedMove " + player.getPlayerName());
                break;
            case "playerLeft":
                Messenger.broadcast(this.players, "players " + this.getPlayersStr());
                break;
            default:
                break;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}