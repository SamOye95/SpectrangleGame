package model;

import view.SpectrangleBoardPrinter;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class SpectrangleBoard {

    // Constants for the game
    public final static SpectranglePieceColor THE_JOKER = SpectranglePieceColor.WHITE;
    private static final int ThirtySixPieceBoard = 36;
    private List<Integer> values;
    private List<Character> vertical;
    private List<Character> left;
    private List<Character> right;

    // The size of the board
    private int numRows;
    private int numCols;
    private List<Integer> multipliers;
    // Multipliers for the spaces on the board.
    // Note: the multipliers array is [row][col], which is different from pretty
    //       much all the rest of this code (which is [col][row])


    // The pieces on the board.
    private SpectranglePiece[] boardPieces;

    // The pieces in the player's hands.
    private SpectranglePiece[] playerPieces;

    // The list of valid moves for each player's piece.
    private Vector[] playerPieceMoves;

    // The current player scores
    private int[] playerScores;
    // The location of each player's most recent move.
    private int[] lastMove;

    // The number of players in the game.
    private int numPlayers;

    // The number of pieces each player has.
    private int piecesPerPlayer;

    // Indicates if this is the first move of the game or not.
    private boolean firstMove = true;

    // The number of tiles still in the bag.
    private int tilesToGo;

    /**
     * Constructor which creates the model.
     *
     * @param numPlayers
     */
    public SpectrangleBoard(int numPlayers) {

        // Create items that depend on the number of players
        this.numPlayers = numPlayers;
        piecesPerPlayer = (ThirtySixPieceBoard == 36) ? 4 : 5;
        playerPieces = new SpectranglePiece[numPlayers][piecesPerPlayer];
        playerPieceMoves = new Vector[numPlayers][piecesPerPlayer];
        playerScores = new int[numPlayers];
        lastMove = new int[numPlayers];


        // Create items that depend on the board size
        values = Arrays.asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        vertical = Arrays.asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        left = Arrays.asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        right = Arrays.asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        multipliers = Arrays.asList(1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 2, 4, 1, 4, 2, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 3, 1, 1, 1, 2, 1, 1, 1, 3, 1);


        numRows = vertical.size();
        numCols = left.size();
        boardPieces = new SpectranglePiece[numCols][numRows];

        // Reset the game
        reset();
    }

    /**
     * Reset the game to the starting configuration.
     */
    public void reset() {
        // Clear the board of all pieces
        for (int c = 0; c < boardPieces.length; c++) {
            for (int r = 0; r < boardPieces[0].length; r++) {
                boardPieces[c][r] = null;
            }
        }

        // Remove all pieces from player's hands
        for (int p = 0; p < playerPieces.length; p++) {
            for (int i = 0; i < playerPieces[0].length; i++) {
                playerPieces[p][i] = null;
                playerPieceMoves[p][i] = new Vector();
            }
        }

        // Clear the player scores & last moves
        for (int p = 0; p < playerScores.length; p++) {
            playerScores[p] = 0;
            lastMove[p] = 0;
        }

        // Reset the number of tiles to go.
        tilesToGo = ThirtySixPieceBoard;

        // This is now the first move
        firstMove = true;


    }

    /**
     * Return information about the game.
     */
    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumPiecesPerPlayer() {
        return piecesPerPlayer;
    }

    /**
     * Return the multiplier to use when playing a piece on the given space
     * of the board.
     */
    public int getMultiplier(int index) {
        return multipliers.get(index);
    }

    /**
     * Return the piece on the board at the given space.
     */
    public SpectranglePiece getPieceAt(int c, int r) {
        return boardPieces[c][r];
    }

    /**
     * Return the requested player's piece.
     */
    public SpectranglePiece getPlayerPiece(int seatNum, int pieceIndex) {
        try {
            return playerPieces[seatNum][pieceIndex];
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Return the score for a player.
     */
    public int getScore(int seatNum) {
        return playerScores[seatNum];
    }

    public int[] getScores() {
        return playerScores;
    }

    /**
     * Return the last move for a player.
     */
    public int getLastMove(int seatNum) {
        return lastMove[seatNum];
    }

    /**
     * Return the number of valid moves for the given player piece.
     * Note: On the first turn of the game, every piece is valid to play, but
     * the playerPieceMoves vectors aren't setup (since they all would
     * have every possible empty space on the board in them), so the
     * first move is handled special.
     */
    public int numValidMovesForPiece(int seatNum, int pieceIndex) {
        try {
            return firstMove ? 1 : playerPieceMoves[seatNum][pieceIndex].size();
        } catch (Exception e) {
            return 0;
        }
    }

    /*
     * Determine if it is valid for the given play to be discarded.
     *
     * @param seatNum     The seat number of the player
     * @param pieceIndex  The piece number within the hand which is desired to check.
     * @return true if the given piece can be discarded.
     */
    public boolean canDiscard(int seatNum, int pieceIndex) {
        try {
            return !hasValidMove(seatNum) &&
                    (playerPieces[seatNum][pieceIndex] != null);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Return the array of valid orientations for the given player's piece
     * that go in the given logical spot on the board.
     *
     * @param seatNum    The player number whose hand is desired.
     * @param pieceIndex The piece number within the hand whose orientations is desired.
     * @param loc        The location on the board for which orientations are desired.
     * @return an array of pieces that have been flipped/rotated into the valid
     * orientations, or null if this move is not valid.
     */
//    public SpectranglePiece[] getValidOrientationsForPiece(int seatNum, int pieceIndex, Point loc) {
//        if (firstMove) {
//            // Handle the first move special.
//            return (getMultiplier(loc.x, loc.y) == 1) ?
//                    makePieceRotations(playerPieces[seatNum][pieceIndex]) :
//                    null;
//        }
//
//        // Search the valid move vector to find the one that corresponds to this
//        // location on the board.
//        Vector moves = playerPieceMoves[seatNum][pieceIndex];
//        for (int i = 0; i < moves.size(); i++) {
//            SpectrangleValidMoveElement el = (SpectrangleValidMoveElement) moves.get(i);
//            if (el.isSameSpace(loc)) {
//                return el.getOrientations();
//            }
//        }
//
//        return null;
//    }

    /**
     * Return if the given player has a valid move or not.
     * TODO: Perhaps cache this info in an array rather than computing it every
     * time this is called?
     */
    public boolean hasValidMove(int seatNum) {
        if (firstMove) {
            // Handle the first move special.
            return true;
        }

        for (int i = 0; i < piecesPerPlayer; i++) {
            if (playerPieceMoves[seatNum][i].size() != 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * Give a piece to a player for use in his hand.
     *
     * @param seatNum    The seat number of the player who is getting the piece.
     * @param piece      The piece being given.
     * @param pieceIndex The location in the player's hand where the piece is to
     *                   be placed.
     */
    public void givePiece(int seatNum, SpectranglePiece piece, int pieceIndex) {
        // Put the piece in the player's hand
        playerPieces[seatNum][pieceIndex] = piece;

        // Clear the valid moves for this piece.
        playerPieceMoves[seatNum][pieceIndex].clear();

        // Reduce the number of tiles still in the bag.
        if (piece != null) {
            tilesToGo -= 1;
        }

        // If this is not the first move of the game, then scan for valid moves
        if (!firstMove) {
            // Set up all of the valid moves for this piece.
            for (int col = 0; col < numCols; col++) {
                for (int row = 0; row < numRows; row++) {
                    // If this is a space on the board and it's empty, then try to
                    // see if the piece fits here.
                    if ((multipliers.get(pieceIndex) > 0) &&
                            (boardPieces[col][row] == null)) {
                        // Check this space for a valid move.
                        Point loc = new Point(col, row);
                        SpectranglePiece[] orientations = getAllPlaceOrientations(piece, loc);
                        if (orientations != null) {
                            playerPieceMoves[seatNum][pieceIndex].add(
                                    new SpectrangleValidMoveElement(loc, orientations));
                        }
                    }
                }
            }
        }

    }


    /**
     * Make a move on the board.
     *
     * @param seatNum The player making the move
     * @param piece   The piece being placed
     * @param loc     The board location where the piece is being placed
     * @return true if this is a valid move and has been made
     * false if this is not a valid move and has not been made
     */
    public boolean makeMove(int seatNum, SpectranglePiece piece, int loc) {
        int neighbors;

        // Verify that this is a legal move.
        if (firstMove) {
            // For the first move, we need to verify that the multiplier for
            // the space is 1.  That is the only constraint.
            if (multipliers.get(loc) != 1) {
                return false;
            }
            neighbors = 1;
        } else {
            // Verify that the piece given goes on the location given.
            neighbors = canPlacePiece(piece, loc);
            if ((neighbors <= 0) || (multipliers.get(loc) == 0)) {
                return false;
            }
        }

        // Put the piece on the board
        boardPieces[col][row] = piece;

        // Add to the score for the player
        playerScores[seatNum] += (piece.getValue() * neighbors * multipliers[row][col]);

        // Remove this space from all other player's valid move arrays (since
        // it's just been taken)
        removeFromValidMoves(loc);

        // For the three neighboring spaces, if any are empty, then we need to
        // remove & re-check if each player's pieces can fit there.
        recheckValidMoves(getLeftLocation(col, row));
        recheckValidMoves(getRightLocation(col, row));
        recheckValidMoves(getBottomLocation(col, row));

        // Save the last move information
        lastMove[seatNum] = loc;

        // It is no longer the first move of the game.
        firstMove = false;


        return true;
    }


    /*
     * This routine will check the given location and remove it from valid moves
     * and then see which player's pieces may be validly placed there and with
     * what orientation.
     */
    private void recheckValidMoves(Point newLoc) {
        if (newLoc != null) {
            int col = newLoc.x;
            int row = newLoc.y;
            if (boardPieces[col][row] == null) {
                // Empty spot, so need to revalidate it.
                removeFromValidMoves(newLoc);
                checkAddToAllValidMoves(newLoc);
            }
        }
    }

    /*
     * This routine will check to see if the given space is a valid move for
     * all pieces in all player's hands and, if so, will add the location to
     * the vector of valid moves for that piece.
     */
    private void checkAddToAllValidMoves(Point newLoc) {
        for (int p = 0; p < numPlayers; p++) {
            for (int i = 0; i < piecesPerPlayer; i++) {
                SpectranglePiece[] orientations =
                        getAllPlaceOrientations(playerPieces[p][i], newLoc);
                if (orientations != null) {
                    playerPieceMoves[p][i].add(
                            new SpectrangleValidMoveElement(newLoc, orientations));
                }
            }
        }
    }

    /*
     * This routine will remove the given space from all valid move vectors
     * of all player pieces.
     */
    private void removeFromValidMoves(Point oldLoc) {
        for (int p = 0; p < numPlayers; p++) {
            for (int i = 0; i < piecesPerPlayer; i++) {
                removeMove(playerPieceMoves[p][i], oldLoc);
            }
        }
    }

    /*
     * This routine will remove a valid move element from the given vector that
     * corresponds to the given board location.
     */
    private void removeMove(Vector validMoves, Point oldLoc) {
        for (int i = 0; i < validMoves.size(); i++) {
            SpectrangleValidMoveElement el = (SpectrangleValidMoveElement) validMoves.get(i);
            if (el.isSameSpace(oldLoc)) {
                validMoves.remove(i);
                return;
            }
        }
    }


    /**
     * Get an array of the valid orientations for the given piece in the given
     * spot.
     *
     * @param thePiece The piece to place.
     * @param loc      The logical location on the board to test.
     * @return an array of pieces that have been fliped/rotated.  If a piece
     * cannot be place validly on the given board space, then that entry in
     * the array is nulled out.
     * If the piece cannot be placed there at all, then null is returned.
     */
    private SpectranglePiece[] getAllPlaceOrientations(SpectranglePiece thePiece, Point loc) {
        if (thePiece == null) {
            // Nothing to do
            return null;
        }

        // Create the 6 rotation/reflections of the piece.
        SpectranglePiece[] pieces = SpectranglePiece.makePieceRotations(thePiece);
        int numPieces = pieces.length;

        // For each one, if the piece doesn't fit, then null out it's spot.
        for (int i = 0; i < pieces.length; i++) {
            if (canPlacePiece(pieces[i], loc) <= 0) {
                pieces[i] = null;
                numPieces -= 1;
            }
        }

        // Return the array of pieces (or null, if the array is empty)
        return (numPieces == 0) ? null : pieces;
    }



    /**
     * Determine if the give piece can be placed in the given location.
     * This will *not* do any rotation/reflections on the piece; it uses
     * the orientation provided.
     *
     * @param thePiece The piece to be placed.
     * @param loc      The location to place the piece.
     * @return the number of neighbors that the piece will have if placed at the
     * given location, or -1 if the piece cannot be placed here.
     */
    public int canPlacePiece(SpectranglePiece thePiece, int loc) {
        int neighborCount = 0;


        // Check left neighbor.
        SpectranglePiece leftNeighbor = getLeftNeighbor(col, row);
        if (leftNeighbor != null) {
            if (!SpectranglePieceColor.colorMatch(leftNeighbor.getColor(SpectranglePiece.RIGHT_SECTION),
                    thePiece.getColor(SpectranglePiece.LEFT_SECTION))) {
                return -1;
            }
            neighborCount += 1;
        }

        // Check right neighbor.
        SpectranglePiece rightNeighbor = getRightNeighbor(col, row);
        if (rightNeighbor != null) {
            if (!SpectranglePieceColor.colorMatch(rightNeighbor.getColor(SpectranglePiece.LEFT_SECTION),
                    thePiece.getColor(SpectranglePiece.RIGHT_SECTION))) {
                return -1;
            }
            neighborCount += 1;
        }

        // Check bottom neighbor.
        SpectranglePiece bottomNeighbor = getBottomNeighbor(col, row);
        if (bottomNeighbor != null) {
            if (!SpectranglePieceColor.colorMatch(bottomNeighbor.getColor(SpectranglePiece.BOTTOM_SECTION),
                    thePiece.getColor(SpectranglePiece.BOTTOM_SECTION))) {
                return -1;
            }
            neighborCount += 1;
        }

        return neighborCount;
    }

    /**
     * Return the left neighbor piece to the space given.
     */
    public SpectranglePiece getLeftNeighbor(int c, int r) {
        if (c != 0) {
            return boardPieces[c - 1][r];
        }
        return null;
    }

    /**
     * Return the right neighbor piece to the space given.
     */
    public SpectranglePiece getRightNeighbor(int c, int r) {
        if (c != (boardPieces.length - 1)) {
            return boardPieces[c + 1][r];
        }
        return null;
    }

    /**
     * Return the bottom neighbor piece to the space given.
     */
    public SpectranglePiece getBottomNeighbor(int c, int r) {
        if (isUpwardTriangle(c, r)) {
            if (r != (boardPieces[0].length - 1)) {
                return boardPieces[c][r + 1];
            }
        } else {
            if (r != 0) {
                return boardPieces[c][r - 1];
            }
        }
        return null;
    }

    /**
     * Return the point on the board that is to the left of the given location.
     * or null, if there is no point on the board to the left.
     */
    public Point getLeftLocation(int c, int r) {
        try {
            if (multipliers[r][c - 1] != 0)
                return new Point(c - 1, r);
        } catch (IndexOutOfBoundsException e) {
        }
        return null;
    }

    /**
     * Return the point on the board that is to the right of the given location.
     * or null, if there is no point on the board to the right.
     */
    public Point getRightLocation(int c, int r) {
        try {
            if (multipliers[r][c + 1] != 0)
                return new Point(c + 1, r);
        } catch (IndexOutOfBoundsException e) {
        }
        return null;
    }

    /**
     * Return the point on the board that is next to the bottom of the given location.
     * or null, if there is no point on the board to the bottom.
     */
    public Point getBottomLocation(int c, int r) {
        try {
            r += (isUpwardTriangle(c, r) ? 1 : -1);
            if (multipliers[r][c] != 0)
                return new Point(c, r);
        } catch (IndexOutOfBoundsException e) {
        }
        return null;
    }

    /**
     * Determine if the game is over or not.
     */
    public boolean isGameOver() {
        // The game is over when all players have no moves and the number of
        // tiles still in the bag is zero.

        if (tilesToGo > 0) {
            return false;
        }

        for (int i = 0; i < numPlayers; i++) {
            if (hasValidMove(i)) {
                return false;
            }
        }

        return true;
    }


    /**
     * This method will determine if the given triangle is an "up" triangle or
     * not.  The up-ness computed by this is a logical property of the triangle
     * not a physical one.  That means that rotation & flipping is not taken
     * into account.  This should be used for logical calculations such as
     * determining neighbors.
     *
     * @param col The column of the triangle to draw.
     * @param row The row of the triangle to draw.
     * @return true  => The triangle is "up"
     * false => The triangle is "down"
     */
    private boolean isUpwardTriangle(int col, int row) {
        return (((col + row) & 0x01) == 0);
    }


    @Override
    public String toString() {
        return SpectrangleBoardPrinter.getBoardString(values, vertical, left, right);
    }

}
