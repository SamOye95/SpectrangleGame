package test;

import model.Board;
import model.BoardSpace;
import model.Tile;
import model.TileOrientation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BoardTest {
    @Test
    public void testCoordToIndex() {
        Board board = new Board();
        assertEquals(board.mapsCoordinatesToIndex(0, 0), 0);
        assertEquals(board.mapsCoordinatesToIndex(1, -1), 1);
        assertEquals(board.mapsCoordinatesToIndex(5, -4), 26);
        assertEquals(board.mapsCoordinatesToIndex(5, 1), 31);
        assertEquals(board.mapsCoordinatesToIndex(2, 0), 6);
        assertEquals(board.mapsCoordinatesToIndex(5, 5), 35);
    }

    @Test
    public void testIndexToCoord() {
        Board board = new Board();
        assertEquals(board.columnOfIndex(0), 0);
        assertEquals(board.rowOfIndex(0), 0);

        assertEquals(board.columnOfIndex(1), -1);
        assertEquals(board.rowOfIndex(1), 1);


        assertEquals(board.columnOfIndex(26), -4);
        assertEquals(board.rowOfIndex(26), 5);

        assertEquals(board.columnOfIndex(31), 1);
        assertEquals(board.rowOfIndex(31), 5);

        assertEquals(board.columnOfIndex(6), 0);
        assertEquals(board.rowOfIndex(6), 2);

        assertEquals(board.columnOfIndex(35), 5);
        assertEquals(board.rowOfIndex(35), 5);
    }

    @Test
    public void testEmptySpaces() {
        Board board = new Board();
        boolean ok = true;
        for (BoardSpace s : board.getBoardSpaces())
            if (s.getSpectranglePiece() != null)
                ok = false;
        assertTrue(ok);
    }

    @Test
    public void testSpaceOfCoord() {
        Board
                board = new Board();

        assertTrue(board.getBoardSpaces().get(0).equals(board.getBoardSpaceOfCoord(0, 0)));
        assertTrue(board.getBoardSpaces().get(1).equals(board.getBoardSpaceOfCoord(1, -1)));
        assertTrue(board.getBoardSpaces().get(26).equals(board.getBoardSpaceOfCoord(5, -4)));
        assertTrue(board.getBoardSpaces().get(31).equals(board.getBoardSpaceOfCoord(5, 1)));


    }

    @Test
    public void testNeighbours() {
        Board board = new Board();
        assertTrue(board.getBoardSpaces().get(12).getLeft().equals(board.getBoardSpaces().get(11)));
        assertTrue(board.getBoardSpaces().get(23).getLeft().equals(board.getBoardSpaces().get(22)));
        assertTrue(board.getBoardSpaces().get(34).getLeft().equals(board.getBoardSpaces().get(33)));


        assertTrue(board.getBoardSpaces().get(10).getRight().equals(board.getBoardSpaces().get(11)));
        assertTrue(board.getBoardSpaces().get(22).getRight().equals(board.getBoardSpaces().get(23)));
        assertTrue(board.getBoardSpaces().get(25).getRight().equals(board.getBoardSpaces().get(26)));

        assertTrue(board.getBoardSpaces().get(28).getVertical().equals(board.getBoardSpaces().get(18)));
        assertTrue(board.getBoardSpaces().get(3).getVertical().equals(board.getBoardSpaces().get(7)));
        assertTrue(board.getBoardSpaces().get(4).getVertical().equals(board.getBoardSpaces().get(10)));

    }

    @Test
    public void testPlacePiece() {
        Board board = new Board();
        Tile piece = new Tile(TileOrientation.UP, 'R', 'R', 'R', 6);


        board.placeTile(piece, 0);
        assertTrue(board.getBoardSpaceOfCoord(0, 0).getSpectranglePiece().equals(piece));


        assertEquals(board.placeTile(piece, 50), -1);
    }


    @Test
    public void testCanBePlaced() {
        Board board = new Board();
        Tile piece = new Tile(TileOrientation.UP, 'R', 'R', 'R', 6);

        board.placeTile(piece, 2);
        board.placeTile(piece, 1);
        board.placeTile(piece, 5);
        board.placeTile(piece, 6);
        board.placeTile(piece, 7);

        Tile tile5 = new Tile(TileOrientation.UP, 'G', 'G', 'G', 6);
        board.placeTile(tile5, 3);
        assertEquals(board.canBePlaced(board.getBoardSpaceOfCoord(1, 1), tile5), 0);


    }
}
