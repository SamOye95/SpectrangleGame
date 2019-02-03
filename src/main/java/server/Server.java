package server;

import controller.SpectrangleController;
import controller.SpectrangleServerGameController;
import controller.SpectrangleServerPlayerController;
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


        public Server(Integer port) throws IOException {
            this.port = port;
            this.database = new ServerDatabase();
            this.socket = new ServerSocket(this.port);

            //Controller list
            List<SpectrangleController> controllers = new ArrayList<SpectrangleController>();
            controllers.add(new SpectrangleServerPlayerController(this.database));
            controllers.add(new SpectrangleServerGameController(this.database));

            //Message handler
            this.messenger = new Messenger(controllers);
        }

        public static void main(String args[]) {
            ServerManager manager;
            Server server;
            Peer peer;

            try {
                server = new Server(8080);
            } catch (IOException e) {
                System.out.println("Unable to start the server. Terminating!");
                return;
            }

            //Starts new thread
            manager = new ServerManager(server.database);
            new Thread(manager).start();
            System.out.println("Server up and running.");

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

    @Override
    public void update(Observable arg0, Object arg1) {
            Peer peer = (Peer) arg0;
            String msg = (String) arg1;

            if (msg.equals("disconnected")) {
                this.database.removePeer(peer);
                System.out.print("Client at " + peer.getAddress() + " has disconnected. Clients left: " + this.database.getPeers().size() + "\n");
            }
        }
    }


