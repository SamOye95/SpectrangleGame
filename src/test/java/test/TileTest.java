package test;


import model.Tile;
import model.TileOrientation;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TileTest {

    private Tile piece1, piece2, piece3, piece4;

    @Before
    public void setUp() {
        piece1 = new Tile(TileOrientation.UP, 'Y', 'B', 'G', 3);
        piece2 = new Tile(TileOrientation.UP, 'Y', 'B', 'G', 3);

        piece3 = new Tile(TileOrientation.UP, 'Y', 'G', 'B', 3);
        piece4 = new Tile(TileOrientation.UP, 'G', 'B', 'Y', 3);
    }

    @Test
    public void rotateTest() {
        piece1.rotate();
        assertEquals("YBG3", piece1.toString());
        assertEquals(TileOrientation.DOWN, piece1.getTileOrientation());
        piece1.rotate();
        assertEquals("YBG3", piece1.toString());
        assertEquals(TileOrientation.UP, piece1.getTileOrientation());
        piece1.rotate();
        assertEquals("GYB3", piece1.toString());
        assertEquals(TileOrientation.DOWN, piece1.getTileOrientation());
        piece1.rotate();
        assertEquals("GYB3", piece1.toString());
        assertEquals(TileOrientation.UP, piece1.getTileOrientation());
        piece1.rotate();
        assertEquals("BGY3", piece1.toString());
        assertEquals(TileOrientation.DOWN, piece1.getTileOrientation());
        piece1.rotate();
        assertEquals("BGY3", piece1.toString());
        assertEquals(TileOrientation.UP, piece1.getTileOrientation());
    }

    @Test
    public void isEquivalentTest() {

        for (int i = 0; i < 6; i++) {
            piece1.rotate();
            assertTrue(piece2.isSamePiece(piece1.toString()));
        }

        for (int i = 0; i < 6; i++) {
            piece1.rotate();
            assertFalse(piece3.isSamePiece(piece1.toString()));
        }

        for (int i = 0; i < 6; i++) {
            piece1.rotate();
            assertFalse(piece4.isSamePiece(piece1.toString()));
        }

    }

}
