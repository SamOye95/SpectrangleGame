package network;

import controller.SpectrangleController;
import model.SpectranglePlayer;

import java.util.ArrayList;
import java.util.List;

public class Messenger {

    private List<SpectrangleController> controllers;
    private List<Integer> statusCodes;


    public Messenger(List<SpectrangleController> controllers) {
        this.controllers = controllers;
        this.statusCodes = new ArrayList<Integer>();
        this.statusCodes.add(400);
        this.statusCodes.add(403);
        this.statusCodes.add(404);
    }

    public static void broadcast(List<SpectranglePlayer> players, String msg) {
        for (SpectranglePlayer player : players) {
            player.getPeer().write(msg);
        }
    }

    public void forward(Peer peer, String message) {
        Message msg = new Message(message);

        if (msg.getStatusCode() != null) {
            this.printStatusCode(peer, msg);
            return;
        }

        for (SpectrangleController controller : this.controllers) {
            if (controller.hasMethod(msg.getCommand())) {
                controller.forward(peer, msg);
            }
        }
    }

    private void printStatusCode(Peer peer, Message msg) {
        System.out.println(msg.getStatusCode() + ": " + msg.getStringArgs());
        System.out.print("> ");
    }

}