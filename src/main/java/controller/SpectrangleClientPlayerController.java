package controller;


import network.Message;
import network.Peer;

public class SpectrangleClientPlayerController extends SpectrangleController {


    //***************************************************
    //------------------CONSTRUCTORS---------------------
    //***************************************************
    public PlayerController(ClientDatabase database) {
        super(database);
    }

    //***************************************************
    //------------------PUBLIC METHODS-------------------
    //***************************************************
    @Override
    public void forward(Peer peer, Message msg) {
        switch (msg.getCommand()) {
            case "features":
                this.features(msg.getArgs());
                break;
            case "nickname":
                this.nickname(msg.getArgs().get(0));
                break;
            default:
                break;
        }
    }

    //***************************************************
    //------------------SERVER COMMANDS------------------
    //***************************************************
    private void serverMessage(String msg) {
        System.out.println("Server: " + msg);
        System.out.print("> ");
    }

    public void features(List<String> args) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        database.getPeer().write("features");
    }

    public void nickname(String nickname) {
        ClientDatabase database = (ClientDatabase) this.getDatabase();
        Player player = database.getPlayer();

        if (player != null) {
            player.setNickname(nickname);
        } else {
            player = new Player(nickname);
            database.setPlayer(player);
        }
    }


}
