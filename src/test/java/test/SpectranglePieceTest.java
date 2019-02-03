package test;


import model.SpectranglePiece;
import model.SpectranglePieceColor;
import model.SpectranglePieceOrientation;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SpectranglePieceTest {

    private SpectranglePiece piece1, piece2, piece3, piece4;

    @Before
    public void setUp() {
        piece1 = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.B, SpectranglePieceColor.G, 3);
        piece2 = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.B, SpectranglePieceColor.G, 3);

        piece3 = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.Y, SpectranglePieceColor.G, SpectranglePieceColor.B, 3);
        piece4 = new SpectranglePiece(SpectranglePieceOrientation.UP, SpectranglePieceColor.G, SpectranglePieceColor.B, SpectranglePieceColor.Y, 3);
    }

    @Test
    public void rotateTest() {
        piece1.rotate();
        assertEquals("YBG3", piece1.toString());
        assertEquals(SpectranglePieceOrientation.DOWN, piece1.getSpectranglePieceOrientation());
        piece1.rotate();
        assertEquals("YBG3", piece1.toString());
        assertEquals(SpectranglePieceOrientation.UP, piece1.getSpectranglePieceOrientation());
        piece1.rotate();
        assertEquals("GYB3", piece1.toString());
        assertEquals(SpectranglePieceOrientation.DOWN, piece1.getSpectranglePieceOrientation());
        piece1.rotate();
        assertEquals("GYB3", piece1.toString());
        assertEquals(SpectranglePieceOrientation.UP, piece1.getSpectranglePieceOrientation());
        piece1.rotate();
        assertEquals("BGY3", piece1.toString());
        assertEquals(SpectranglePieceOrientation.DOWN, piece1.getSpectranglePieceOrientation());
        piece1.rotate();
        assertEquals("BGY3", piece1.toString());
        assertEquals(SpectranglePieceOrientation.UP, piece1.getSpectranglePieceOrientation());
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
