package client;

import controller.SpectrangleClientGameController;
import controller.SpectrangleClientPlayerController;
import controller.SpectrangleController;
import network.InputThread;
import network.Messenger;
import network.Peer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {


    private Socket socket;
    private Integer port;
    private String host;
    private ClientDatabase database;
    private Messenger messenger;


    public Client(String host, Integer port) throws IOException {
        this.host = host;
        this.port = port;
        this.database = new ClientDatabase();

        //Controller list
        List<SpectrangleController> controllers = new ArrayList<SpectrangleController>();
        controllers.add(new SpectrangleClientPlayerController(this.database));
        controllers.add(new SpectrangleClientGameController(this.database));


        this.messenger = new Messenger(controllers);
        this.socket = new Socket(this.host, this.port);
        this.database.setPeer(new Peer(this.socket, this.messenger, new ClientPeer(this.database)));
        this.database.setInputThread(new InputThread(this.database.getPeer(), this.messenger));
    }


    public static void main(String args[]) throws InterruptedException {
        try {
            Client client = new Client("120.0.0.1", 8080);
            client.database.getInputThread().begin();

        } catch (UnknownHostException e) {
            System.out.println("Could not find host. Terminating!");
        } catch (IOException e) {
            System.out.println("An error ocurred. Terminating!");
        }
    }
}
