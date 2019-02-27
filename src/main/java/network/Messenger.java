package network;

import controller.Controller;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class Messenger {

    private List<Controller> controllers;
    private List<Integer> errorCodes;


    public Messenger(List<Controller> controllers) {
        this.controllers = controllers;
        this.errorCodes = new ArrayList<>();
        this.errorCodes.add(400);// general error
        this.errorCodes.add(403);
        this.errorCodes.add(404);

    }

    public static void broadcast(List<Player> players, String msg) {
        for (Player player : players) {
            player.getPeer().write(msg);
        }
    }

    private void printStatusCode(Peer peer, Message msg) {
        System.out.println(msg.getErrorCode() + ": " + msg.getStringArgs());
        System.out.print("> ");
    }

    public void forward(Peer peer, String message) {
        Message msg = new Message(message);

        if (msg.getErrorCode() != null) {
            this.printStatusCode(peer, msg);
            return;
        }

        for (Controller controller : this.controllers) {
            if (controller.hasMethod(msg.getCommand())) {
                controller.forward(peer, msg);
            }
        }
    }

}