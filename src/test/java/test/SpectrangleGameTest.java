package test;

import model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

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

        game.setTurn(player);
        assertTrue(player2.equals(game.nextPlayer()));

        game.setTurn(player3);
        assertTrue(player.equals(game.nextPlayer()));

    }
}