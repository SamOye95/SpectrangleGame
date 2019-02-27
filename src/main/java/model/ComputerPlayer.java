package model;

public class ComputerPlayer extends Player {

    public ComputerPlayer() {
        super("CPU");
    }

    @Override
    public void requestMove() {
        Board board = this.getGame().getBoard();
        BoardSpace target_slot;
        Tile target_tile;
        int max_points = 0;


        for (BoardSpace space : board.getBoardSpaces()) {
            for (Tile tile : this.getTiles()) {
                int points = board.canBePlaced(space, tile);
                points = points * tile.getPoints() * space.getMultiplier();

                if (points > max_points) {
                    max_points = points;
                    target_slot = space;
                    target_tile = tile;
                }
            }
        }


    }

}
