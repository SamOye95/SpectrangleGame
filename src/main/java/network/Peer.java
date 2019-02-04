package network;



import java.io.*;
import java.net.Socket;
import java.util.Observable;

public class Peer extends Observable implements Runnable {

    private Thread thread;
    private Socket socket;
    private Messenger messenger;
    private BufferedReader reader;
    private PrintWriter writer;

    /**
     * @param socket
     * @param messenger
     * @param setup
     * @requires (socket ! = null) && (messenger != null) &&(Setup != null)
     * Constructor. creates a peer object based in the given parameters.
     */
    public Peer(Socket socket, Messenger messenger, Setup setup) {
        this.socket = socket;
        this.messenger = messenger;
        this.init();

        setup.setPeer(this);
        new Thread(setup).start();

        this.thread = new Thread(this);
        this.thread.start();
    }


    public void run() {
        String message;

        try {
            while ((message = this.read()) != null) {
                this.messenger.forward(this, message, null);
            }

        } catch (IOException e) {
            this.setChanged();
            this.notifyObservers("disconnected");
        }
    }


    public String read() throws IOException {
        return this.reader.readLine();
    }

    public void write(String message) {
        this.writer.println(message);
    }

    public String getAddress() {
        return this.socket.getInetAddress().getHostAddress();
    }


    public void init() {
        try {
            InputStream in = this.socket.getInputStream();
            OutputStream out = this.socket.getOutputStream();

            this.reader = new BufferedReader(new InputStreamReader(in));
            this.writer = new PrintWriter(out, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}