package client;

import controller.ClientGameController;
import controller.ClientPlayerController;
import controller.Controller;
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

    /*
     * @param host
     * @param port
     * @throws IOException
     */
    public Client(String host, Integer port) throws IOException {
        this.host = host;
        this.port = port;
        this.database = new ClientDatabase();

        //Controller list
        List<Controller> controllers = new ArrayList<Controller>();
        controllers.add(new ClientPlayerController(this.database));
        controllers.add(new ClientGameController(this.database));


        this.messenger = new Messenger(controllers);
        this.socket = new Socket(this.host, this.port);
        this.database.setPeer(new Peer(this.socket, this.messenger, new ClientPeer(this.database)));
        this.database.setInputThread(new InputThread(this.database.getPeer(), this.messenger));
    }

    /*
     * Main function to create a client to connect to the server.
     * @param args IP address and port number
     */
    public static void main(String args[]) {


        String ip_addr;
        int port;

        try {
            ip_addr = "localhost";
            port = 8086;
        } catch (NumberFormatException e) {
            System.out.println("Invalid port. Terminating!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Argument missing. Terminating!");
            return;
        }
        try {
            Client client = new Client(ip_addr, port);
            client.database.getInputThread().begin();

        } catch (UnknownHostException e) {
            System.out.println("Could not find host. Terminating!");
        } catch (IOException e) {
            System.out.println("An error ocurred. Terminating!");
        }
    }
}
