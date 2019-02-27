package view;

import model.Board;
import model.Game;
import model.Player;
import model.Tile;

public class SpectrangleGameView {

    Game game;

    public SpectrangleGameView() {

    }

    public void take(boolean showEntry) {
        this.clear();

        Board board = this.game.getBoard();
        System.out.println(board.toString());
        System.out.println("\n");

        for (Player player : this.game.getPlayers()) {
            System.out.println(player.getPlayerName() + "-Score:" + player.getScore());
            Tile.take(player.getTiles());

        }

        if (showEntry) {
            System.out.print("> ");
        }
    }

    private void clear() {

        for (int i = 0; i < 100; i++) {
            System.out.print("\n");
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
