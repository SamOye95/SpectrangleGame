package network;

import controller.SpectrangleController;
import model.SpectranglePieceOrientation;
import model.SpectranglePlayer;

import java.util.ArrayList;
import java.util.List;

public class Messenger {

    private List<SpectrangleController> controllers;
    private List<Integer> errorCodes;


    public Messenger(List<SpectrangleController> controllers) {
        this.controllers = controllers;
        this.errorCodes = new ArrayList<Integer>();
        this.errorCodes.add(0);// general error
        this.errorCodes.add(1);// username is taken
        this.errorCodes.add(2);// invalid move
        this.errorCodes.add(3);// not your turn
        this.errorCodes.add(4);// unknown command
        this.errorCodes.add(5);// invalid command
        this.errorCodes.add(6);// invalid parameter
    }

    public static void broadcast(List<SpectranglePlayer> players, String msg) {
        for (SpectranglePlayer player : players) {
            player.getPeer().write(msg);
        }
    }

    public void forward(Peer peer, String message, SpectranglePieceOrientation orientation) {
        Message msg = new Message(message);

        if (msg.getErrorCode() != null) {
            this.printStatusCode(peer, msg);
            return;
        }

        for (SpectrangleController controller : this.controllers) {
            if (controller.hasMethod(msg.getCommand())) {
                controller.forward(peer, msg, null);
            }
        }
    }

    private void printStatusCode(Peer peer, Message msg) {
        System.out.println(msg.getErrorCode() + ": " + msg.getStringArgs());
        System.out.print("> ");
    }

}