package test;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SpectrangleGameTest {

    @Test
    public void testInitialConditions() {
        SpectrangleHumanPlayer player = new SpectrangleHumanPlayer("RandomName");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<>();
        players.add(player);
        SpectrangleGame game = new SpectrangleGame(players, host);


        assertTrue(game.getBag() != null);
        assertTrue(game.getBoard() != null);
        assertTrue(game.getTurn() == player);

    }

    @Test
    public void testGetPlayerStr() {
        SpectrangleHumanPlayer player1 = new SpectrangleHumanPlayer("RandomName");
        SpectrangleHumanPlayer player2 = new SpectrangleHumanPlayer("RandomName2");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<SpectranglePlayer>();
        players.add(player1);
        players.add(player2);
        SpectrangleGame game = new SpectrangleGame(players, host);
        assertTrue("RandomName RandomName2".equals(game.getPlayersStr()));

    }

    @Test
    public void testNextPlayer() {
        SpectrangleHumanPlayer player1 = new SpectrangleHumanPlayer("RandomName");
        SpectrangleHumanPlayer player2 = new SpectrangleHumanPlayer("RandomName2");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        SpectrangleGame game = new SpectrangleGame(players, host);

        assertTrue(player2.equals(game.nextPlayer()));
        game.setTurn(game.nextPlayer());
        assertTrue(player1.equals(game.nextPlayer()));

    }

    @Test
    public void testPlaceTile() {

        SpectrangleHumanPlayer player = new SpectrangleHumanPlayer("name");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<SpectranglePlayer>();
        players.add(player);
        SpectrangleGame game = new SpectrangleGame(players, host);

        SpectranglePiece piece = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.R, 6);

        game.placeSpectranglePiece(player, 0, piece);

        assertTrue(game.getBoard().getSpectrangleBoardSpaceOfCoord(0, 0).getSpectranglePiece().equals(piece));


    }

    @Test
    public void testLeaveGame() {


        SpectrangleHumanPlayer player = new SpectrangleHumanPlayer("RandomName");
        SpectrangleHumanPlayer player2 = new SpectrangleHumanPlayer("RandomName2");
        SpectrangleHumanPlayer player3 = new SpectrangleHumanPlayer("RandomName3");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<SpectranglePlayer>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        SpectrangleGame game = new SpectrangleGame(players, host);

        game.leaveGame(player);
        assertEquals(2, game.getPlayers().size());

    }

    @Test
    public void testSwitchTile() {
        // creating a new game
        SpectrangleHumanPlayer player = new SpectrangleHumanPlayer("name");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> jucatori = new ArrayList<SpectranglePlayer>();
        jucatori.add(player);
        jucatori.add(host);
        SpectrangleGame game = new SpectrangleGame(jucatori, host);

        // creating 2 different tiles
        SpectranglePiece tile = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.R, 6);
        SpectranglePiece tile2 = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.G, 6);

        // creating 2 lists of tiles
        List<SpectranglePiece> playersTile = new ArrayList<SpectranglePiece>();
        List<SpectranglePiece> playersTileCopy = new ArrayList<SpectranglePiece>();

        // adding the red tile to both of them
        playersTile.add(tile);
        playersTileCopy.add(tile);

        // assigning the first one as the player's tiles
        player.setSpectranglePieces(playersTile);

        // putting the green tile on the index 0
        game.getBoard().getBoardSpaces().get(0).setSpectranglePiece(tile2);

        // testing correct behaviour
        game.switchPiece(player, tile);
        assertFalse(player.getSpectranglePieces().equals(playersTileCopy));

    }

    @Test
    public void testCanMakeMove() {
        SpectrangleHumanPlayer player = new SpectrangleHumanPlayer("name");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<SpectranglePlayer>();
        players.add(player);
        SpectrangleGame game = new SpectrangleGame(players, host);

        SpectranglePiece piece = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.R, 6);
        List<SpectranglePiece> playersTile = new ArrayList<SpectranglePiece>();

        playersTile.add(piece);
        player.setSpectranglePieces(playersTile);

        assertTrue(game.canMakeMove(player));


    }

    @Test
    public void testSkipMove() {
        SpectrangleHumanPlayer player = new SpectrangleHumanPlayer("RandomName");
        SpectrangleHumanPlayer player2 = new SpectrangleHumanPlayer("RandomName2");
        SpectrangleHumanPlayer player3 = new SpectrangleHumanPlayer("RandomName3");
        SpectrangleHumanPlayer host = new SpectrangleHumanPlayer("Host");
        List<SpectranglePlayer> players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        players.add(player3);
        SpectrangleGame game = new SpectrangleGame(players, host);


        // creating 2 different tiles
        SpectranglePiece piece = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.R, SpectranglePieceColor.R, SpectranglePieceColor.R, 6);
        SpectranglePiece piece1 = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.G, SpectranglePieceColor.G, 6);

        // giving the red tile to the first player
        List<SpectranglePiece> playersTile = new ArrayList<SpectranglePiece>();
        playersTile.add(piece);
        player.setSpectranglePieces(playersTile);

        
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