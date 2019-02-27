package model;


import exceptions.NotEnoughPlayersException;
import network.Attribute;
import network.Messenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Game implements Runnable, Observer {

    // ------------------Constants------------------------
    public static final int minPlayers = 2;
    public static final int maxPlayers = 4;

    // ------------------Relationships--------------------

    private Board board;
    private RandomBag bag;
    private List<Player> players;

    // ------------------Attributes-----------------------
    private Thread thread;
    private Player turn;
    private PlayerStatus status;

    public Game(List<Player> players, Player host) throws NotEnoughPlayersException {

        if (players.size() < Game.minPlayers || players.size() > Game.maxPlayers) {
            throw new NotEnoughPlayersException("There aren't enough players to start a game.");
        }


        this.players = players;
        this.init();

    }

    /*
     * Starts game
     */
    public void start() {
        this.thread = new Thread(this);
        this.thread.start();
        this.status = PlayerStatus.RUNNING;
    }

    /*
     * @return
     * @pure
     * @ensures (! \ result.equals ( null))
     */
    public String getPlayersStr() {
        String str = "";
        for (Player player : this.players) {
            str += player.getPlayerName() + " ";
        }

        return str.trim();
    }

    /*
     * @param player
     * @param index
     * @param piece
     * @return
     * @requires this.getPlayers().contains(player) && player.getTiles.contains(piece) && index >= 0 && index <= this.getBoard().LENGTH
     * @ensures !this.getTurn().equals(player) ==> \result == 403;
     * @ensures this.getBoard().placeTile(tile, index) == -1 ==> \result == 404;
     * @ensures player.getScore() >= old(player.getScore());
     * @ensures this.getTurn().equals(this.nextPlayer ());
     * @ensures player.getTiles().size() <= \old(player.getTiles().size());
     */
    public int placeTile(Player player, int index, Tile piece) {
        Integer points;

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        points = this.board.placeTile(piece, index);
        if (points == -1) {
            return 404;
        }

        player.getTiles().remove(piece);
        player.addPoints(points);
        this.turn = this.nextPlayer();

        return 0;
    }

    /*
     * @pure
     * @requires this.getPlayers().contains(player) && player.getTiles().contains(tile);
     * @ensures !this.getTurn().equals(player) || this.canMakeMove(player) ==> \result == 403;
     * @ensures this.getTurn().equals(this.nextPlayer ());
     */
    public int switchPiece(Player player, Tile spectranglePiece) {

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        if (this.canMakeMove(player)) {
            return 403;
        }

        player.getTiles().remove(spectranglePiece);
        this.bag.getPieces().add(spectranglePiece);
        player.takeTile();
        this.turn = this.nextPlayer();
        return 0;
    }

    /*
     * @param player
     * @param piece
     * @param newTileStr
     * @return
     * @pure
     * @requires this.getPlayers().contains(player) && player.getTiles().contains(tile) && !newTileStr.equals(null);
     * @ensures !this.getTurn().equals(player) || this.canMakeMove(player) ==> \result == 403;
     * @ensures this.getTurn().equals(this.nextPlayer ());
     */
    public int switchPiece(Player player, Tile piece, String newTileStr) {

        if (!this.getTurn().equals(player)) {
            return 403;
        }

        if (this.canMakeMove(player)) {
            return 403;
        }

        player.getTiles().remove(piece);
        this.bag.getPieces().add(piece);
        player.takeTile(newTileStr);
        this.turn = this.nextPlayer();
        return 0;
    }

    /*
     * @param player
     * @return
     * @requires player!=null && this.getPlayers().contains(player);
     * @ensures this.canMakeMove(player) ==> \result==403;
     */
    public int skipMove(Player player) {
        if (this.canMakeMove(player)) {
            return 403;
        }

        this.turn = this.nextPlayer();
        return 0;
    }

    /*
     * This method check whether a player can place a tile or not
     *
     * @param player the player to be checked
     * @return boolean value of whether the player can place a tile or not
     * @requires player!=null && this.getPlayers().contains(player);
     * @ensures (\ exists Tile t ; player.getTiles ().contains(t) && this.getBoard().canBePlaced(t);\result==true);
     */

    public boolean canMakeMove(Player player) {
        boolean result = false;
        for (Tile tile : player.getTiles()) {
            for (int i = 0; i < 6; i++) {
                tile.rotate();
                if (this.board.canBePlaced(tile)) {
                    result = true;
                }
            }

            if (result) {
                break;
            }
        }
        return result;
    }

    /*
     * @return
     * @ensures (this.getPlayers ().contains(\ result));
     * @ensures this.getPlayers().indexOf(this.getTurn ())<this.getPlayers().size() ==> \result.equals(this.getPlayers().get(1+this.getPlayers().indexOf(this.getTurn())));
     */
    public Player nextPlayer() {
        Integer index = this.players.indexOf(this.turn) + 1;
        Player next = this.players.get(0);

        if (index < this.players.size()) {
            next = this.players.get(index);
        }

        return next;
    }

    /*
     * @param player
     * @requires this.getPlayers().contains(player);
     * @ensures !(this.getPlayers().contains(player)) && player.getScore()==0;
     * @ensures this.getBag().getTiles().size()==4+\old(this.getBag().getTiles().size());
     */
    public void leaveGame(Player player) {
        this.bag.getPieces().addAll(player.getTiles());
        player.setTiles(new ArrayList<Tile>());
        player.setScore(0);
        this.players.remove(player);
    }

    /*
     * This method initialize the game after it is being started.
     *
     * @ensures this.getBoard()!=null && this.getBag()!=null && this.status==Status.NOT_STARTED;
     * @ensures (\ forall Player p ; this.getPlayers ().contains(p);  p.getGame().equals(this) && p.getScore()==0 );
     */
    private void init() {
        this.board = new Board();
        this.bag = new RandomBag();
        this.status = PlayerStatus.NOT_STARTED;

        for (Player player : this.players) {
            player.setGame(this);
            player.setScore(0);
        }

        this.turn = players.get(0);
    }

    /*
     * @return
     * @ensures !(\result.equals(null));
     */
    public RandomBag getBag() {
        return bag;
    }

    /*
     * @param bag
     * @requires bag!=null;
     * @ensures this.getBag().equals(bag);
     */
    public void setBag(RandomBag bag) {
        this.bag = bag;
    }

    /*
     * @return
     * @ensures !(\result.equals(null));
     */
    public List<Player> getPlayers() {
        return players;
    }

    /*
     * @param players
     * @requires players!=null;
     * @ensures this.getPlayers().equals(players);
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /*
     * @return
     * @ensures \result!=null;
     */
    public Board getBoard() {
        return board;
    }

    /*
     * @param board
     * @requires board!=null;
     * @ensures this.getBoard()==board;
     */

    public void setBoard(Board board) {
        this.board = board;
    }

    /*
     * @return
     * @ensures \result!=null;
     */
    public Player getTurn() {
        return turn;
    }

    /*
     * @param turn
     * @requires this.getPlayers().contains(turn);
     * @ensures this.getTurn().equals(turn);
     */
    public void setTurn(Player turn) {
        this.turn = turn;
    }

    @Override
    public void run() {

        //FIRST STEP - TELL ALL PLAYERS THAT THE GAME STARTED AND IN WHICH ORDER THE PLAYERS ARE
        Messenger.broadcast(this.players, "start " + this.getPlayersStr());

        //SECOND STEP - DRAW TILES FOR ALL OF THE PLAYERS
        for (Player player : this.players) {
            List<Tile> pieces = player.takeMaximumTiles();
            for (Tile piece : pieces) {
                Messenger.broadcast(this.players, "takenPiece " + player.getPlayerName() + " " + piece.toString());
            }
        }

        while (this.status.equals(PlayerStatus.RUNNING)) {
            for (Player player : this.players) {
                player.requestMove();
                while (this.turn.equals(player)) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (this.getBag().getPieces().isEmpty()) {
                    this.status = PlayerStatus.FINISHED;
                    Messenger.broadcast(this.players, "end");
                    break;
                } else {
                    player.takeTile();
                }
            }
        }
    }

    /*
     * @param arg0
     * @param arg1
     * @requires arg0!=null && arg1!= null;
     * @requires (( Attribute)arg1).getPlayers().get(0).getPeer()!=null;
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        Attribute attr = (Attribute) arg1;
        Player player = attr.getPlayers().get(0);
        Tile piece;

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
                Tile oldTile = attr.getTiles().get(0);
                Tile newTile = attr.getTiles().get(1);
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