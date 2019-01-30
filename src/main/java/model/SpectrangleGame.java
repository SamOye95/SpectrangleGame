package model;

import java.util.ArrayList;
import java.util.List;

public class SpectrangleGame implements Runnable {

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


    public void start() {
        this.thread = new Thread(this);
        this.thread.start();
        this.status = SpectranglePlayerStatus.RUNNING;
    }

    public String getPlayersStr() {
        String str = "";
        for (SpectranglePlayer player : this.players) {
            str += player.getPlayerName() + " ";
        }

        return str.trim();
    }

    public int placeSpectranglePiece(SpectranglePlayer player, int index, SpectranglePiece piece) {
        Integer points;

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        points = this.board.placeTile(tile, index);
        if (points == -1) {
            return 404;
        }

        player.getTiles().remove(tile);
        player.addPoints(points);
        this.turn = this.nextPlayer();

        return 0;
    }

    public int switchSpectranglePiece(SpectranglePlayer player, SpectranglePiece spectranglePiece) {

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        if (this.canMakeMove(player)) {
            return 403;
        }

        player.getTiles().remove(tile);
        this.bag.getTiles().add(tile);
        player.drawTile();
        this.turn = this.nextPlayer();
        return 0;
    }

    public int switchTile(Player player, Tile tile, String newTileStr) {

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        if (this.canMakeMove(player)) {
            return 403;
        }

        player.getTiles().remove(tile);
        this.bag.getTiles().add(tile);
        player.drawTile(newTileStr);
        this.turn = this.nextPlayer();
        return 0;
    }

    public int skipMove(Player player) {
        if (this.canMakeMove(player)) {
            return 403;
        }

        this.turn = this.nextPlayer();
        return 0;
    }

    public boolean canMakeMove(Player player) {
        for (Tile tile : player.getTiles()) {
            if (this.board.canBePlaced(tile)) {
                return true;
            }
        }
        return false;
    }

    public Player nextPlayer() {
        Integer index = this.players.indexOf(this.turn) + 1;
        Player next = this.players.get(0);

        if (index < this.players.size()) {
            next = this.players.get(index);
        }

        return next;
    }

    public void leaveGame(Player player) {
        this.bag.getTiles().addAll(player.getTiles());
        player.setTiles(new ArrayList<Tile>());
        player.setScore(0);
        this.players.remove(player);
    }

    //***************************************************
    //------------------PRIVATE METHODS------------------
    //***************************************************
    private void init() {
        this.board = new Board();
        this.bag = new Bag();
        this.status = Status.NOT_STARTED;

        for (Player player : this.players) {
            player.setGame(this);
            player.setScore(0);
        }

        this.turn = players.get(0);
    }

    //***************************************************
    //------------------GETTERS/SETTERS------------------
    //***************************************************
    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    //***************************************************
    //------------------THREAD---------------------------
    //***************************************************
    @Override
    public void run() {

        //FIRST STEP - TELL ALL PLAYERS THAT THE GAME STARTED AND IN WHICH ORDER THE PLAYERS ARE
        Messenger.broadcast(this.players, "start " + this.getPlayersStr());

        //SECOND STEP - DRAW TILES FOR ALL OF THE PLAYERS
        for (Player player : this.players) {
            List<Tile> tiles = player.drawMaxTiles();
            for (Tile tile : tiles) {
                Messenger.broadcast(this.players, "drawnTile " + player.getNickname() + " " + tile.toString());
            }
        }

        while (this.status.equals(Status.RUNNING)) {
            for (Player player : this.players) {
                player.getPeer().write("requestMove");
                while (this.turn.equals(player)) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (this.getBag().getTiles().isEmpty()) {
                    this.status = Status.FINISHED;
                    Messenger.broadcast(this.players, "end");
                    break;
                }

                Tile tile = player.drawTile();
                Messenger.broadcast(this.players, "drawnTile " + player.getNickname() + " " + tile.toString());
            }
        }
    }
}
