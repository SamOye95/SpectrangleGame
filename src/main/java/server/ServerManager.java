package server;

import model.SpectrangleGame;
import model.SpectranglePlayer;

import java.util.List;

public class ServerManager implements Runnable {

    private ServerDatabase database;

    public ServerManager(ServerDatabase database) {
        this.database = database;
    }


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
        List<SpectranglePlayer> players = this.database.getIdlePlayers();

        if (players.size() >= SpectrangleGame.minPlayers) {
            SpectranglePlayer host = players.get(0);
            SpectrangleGame game = new SpectrangleGame(players, host);

            game.start();
            this.database.insertGame(game);
        }
    }

}