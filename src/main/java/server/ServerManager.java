package server;

import exceptions.NotEnoughPlayersException;
import model.Game;
import model.Player;

import java.util.List;

public class ServerManager implements Runnable {

    private ServerDatabase database;

    public ServerManager(ServerDatabase database) {
        this.database = database;
    }

    @Override
    public void run() {
        while (true) {

            this.createGames();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error in server manager");
            }
        }
    }


    private void createGames() {
        List<Player> players = this.database.getIdlePlayers();

        if (players.size() >= Game.minPlayers) {
            Player host = players.get(0);
            Game game = null;
            try {
                game = new Game(players, host);
            } catch (NotEnoughPlayersException e) {
                e.printStackTrace();
                return;
            }

            game.start();
            this.database.insertGame(game);
        }
    }

}