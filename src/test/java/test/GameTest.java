package test;

import exceptions.NotEnoughPlayersException;
import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GameTest {

    @Test
    public void testInitialConditions() throws NotEnoughPlayersException {
        HumanPlayer player = new HumanPlayer("RandomName");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<>();
        players.add(player);
        Game game = new Game(players, host);


        assertTrue(game.getBag() != null);
        assertTrue(game.getBoard() != null);
        assertTrue(game.getTurn() == player);

    }

    @Test
    public void testGetPlayerStr() throws NotEnoughPlayersException {
        HumanPlayer player1 = new HumanPlayer("RandomName");
        HumanPlayer player2 = new HumanPlayer("RandomName2");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(players, host);
        assertTrue("RandomName RandomName2".equals(game.getPlayersStr()));

    }

    @Test
    public void testNextPlayer() throws NotEnoughPlayersException {
        HumanPlayer player1 = new HumanPlayer("RandomName");
        HumanPlayer player2 = new HumanPlayer("RandomName2");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        Game game = new Game(players, host);

        assertTrue(player2.equals(game.nextPlayer()));
        game.setTurn(game.nextPlayer());
        assertTrue(player1.equals(game.nextPlayer()));

    }

    @Test
    public void testPlaceTile() throws NotEnoughPlayersException {

        HumanPlayer player = new HumanPlayer("name");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<Player>();
        players.add(player);
        Game game = new Game(players, host);

        Tile piece = new Tile(TileOrientation.UP, 'R', 'R', 'R', 6);

        game.placeTile(player, 0, piece);

        assertTrue(game.getBoard().getBoardSpaceOfCoord(0, 0).getSpectranglePiece().equals(piece));


    }

    @Test
    public void testLeaveGame() throws NotEnoughPlayersException {


        HumanPlayer player = new HumanPlayer("RandomName");
        HumanPlayer player2 = new HumanPlayer("RandomName2");
        HumanPlayer player3 = new HumanPlayer("RandomName3");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<Player>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        Game game = new Game(players, host);

        game.leaveGame(player);
        assertEquals(2, game.getPlayers().size());

    }

    @Test
    public void testSwitchTile() throws NotEnoughPlayersException {
        // creating a new game
        HumanPlayer player = new HumanPlayer("name");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<Player>();
        players.add(player);
        players.add(host);
        Game game = new Game(players, host);

        // creating 2 different tiles
        Tile tile = new Tile(TileOrientation.UP, 'R', 'R', 'R', 6);
        Tile tile2 = new Tile(TileOrientation.UP, 'G', 'G', 'G', 6);

        // creating 2 lists of tiles
        List<Tile> playersTile = new ArrayList<Tile>();
        List<Tile> playersTileCopy = new ArrayList<Tile>();

        // adding the red tile to both of them
        playersTile.add(tile);
        playersTileCopy.add(tile);

        // assigning the first one as the player's tiles
        player.setTiles(playersTile);

        // putting the green tile on the index 0
        game.getBoard().getBoardSpaces().get(0).setSpectranglePiece(tile2);

        // testing correct behaviour
        game.switchPiece(player, tile, "PPP6");
        assertFalse(player.getTiles().equals(playersTileCopy));

    }

    @Test
    public void testCanMakeMove() throws NotEnoughPlayersException {
        HumanPlayer player = new HumanPlayer("name");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<Player>();
        players.add(player);
        Game game = new Game(players, host);

        Tile piece = new Tile(TileOrientation.UP, 'R', 'R', 'R', 6);
        List<Tile> playersTile = new ArrayList<Tile>();

        playersTile.add(piece);
        player.setTiles(playersTile);

        assertTrue(game.canMakeMove(player));


    }

    @Test
    public void testSkipMove() throws NotEnoughPlayersException {
        HumanPlayer player = new HumanPlayer("RandomName");
        HumanPlayer player2 = new HumanPlayer("RandomName2");
        HumanPlayer player3 = new HumanPlayer("RandomName3");
        HumanPlayer host = new HumanPlayer("Host");
        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        Game game = new Game(players, host);


        // creating 2 different tiles
        Tile piece = new Tile(TileOrientation.UP, 'R', 'R', 'R', 6);
        Tile piece1 = new Tile(TileOrientation.UP, 'G', 'G', 'G', 6);

        // giving the red tile to the first player
        List<Tile> playersTile = new ArrayList<Tile>();
        playersTile.add(piece);
        player.setTiles(playersTile);

        
        game.setTurn(player);

        // testing correct behaviour
        assertEquals(game.skipMove(player), 403);

        // placing the green tile on the board
        game.getBoard().getBoardSpaces().get(0).setSpectranglePiece(piece1);

        // testing for correct behaviour
        game.skipMove(player);
        assertTrue(game.getTurn().equals(player2));
        
    }
}