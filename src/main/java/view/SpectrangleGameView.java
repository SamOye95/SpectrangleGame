package view;

import model.SpectrangleBoard;
import model.SpectrangleGame;
import model.SpectranglePiece;
import model.SpectranglePlayer;

public class SpectrangleGameView {

    SpectrangleGame game;

    public SpectrangleGameView() {

    }

    public void take() {
        this.clear();

        SpectrangleBoard board = this.game.getBoard();
        System.out.println(board.toString());
        System.out.println("\n");

        for (SpectranglePlayer player : this.game.getPlayers()) {

            System.out.println(player.getPlayerName() + "-Score:" + player.getScore());
            {
                SpectranglePiece.take(player.getSpectranglePieces());

            }
        }

        System.out.print(">");
    }

    private void clear() {

        for (int i = 0; i < 100; i++) {
            System.out.print("\n");
        }
    }

    public SpectrangleGame getGame() {
        return game;
    }

    public void setGame(SpectrangleGame game) {
        this.game = game;
    }
}
