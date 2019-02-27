package server;

import controller.Controller;
import controller.ServerGameController;
import controller.ServerPlayerController;
import network.Messenger;
import network.Peer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



public class Server implements Observer {

        private Integer port;
        private ServerSocket socket;
        private ServerDatabase database;
        private Messenger messenger;


    /*
     * This constructor creates a server.
     *
     * @param port
     * @throws IOException
     */
    public Server(Integer port) throws IOException {
        this.port = port;
        this.database = new ServerDatabase();
        this.socket = new ServerSocket(this.port);

        //Controller list
        List<Controller> controllers = new ArrayList<Controller>();
        controllers.add(new ServerPlayerController(this.database));
        controllers.add(new ServerGameController(this.database));

        //Message handler
        this.messenger = new Messenger(controllers);
    }

    /*
     * This main function is used to start the server
     * @param args the port number of the server
     *
     */
    public static void main(String args[]) {
        ServerManager manager;
        Server server;
        Peer peer;

        Integer port;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid port. Terminating!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Port missing!");
            return;
        }

        try {
            server = new Server(port);
        } catch (IOException e) {
            System.out.println("Unable to start the server. Terminating!");
            return;
        }

        //Starts new thread
        manager = new ServerManager(server.database);
        new Thread(manager).start();
        System.out.println("Server up and running.");

        //This starts the server.
        while (true) {
            try {
                peer = new Peer(server.socket.accept(), server.messenger, new ServerPeer(server.database));
                peer.addObserver(server);
                server.database.insertPeer(peer);
                System.out.println("New client connected!");
            } catch (IOException e) {
                System.out.println("Client failed to connect. Terminating!");
                return;
            }
        }

    }


    public void update(Observable arg0, Object arg1) {
            Peer peer = (Peer) arg0;
            String msg = (String) arg1;

        if (msg.equalsIgnoreCase("disconnected")) {
                this.database.removePeer(peer);
                System.out.print("Client at " + peer.getAddress() + " has disconnected. Clients left: " + this.database.getPeers().size() + "\n");
            }
        }
    }


