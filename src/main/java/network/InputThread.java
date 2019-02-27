package network;

import java.util.Scanner;

public class InputThread implements Runnable {

    private Peer peer;
    private Thread thread;
    private boolean running;
    private Messenger messenger;


    public InputThread(Peer peer, Messenger messenger) {
        this.peer = peer;
        this.running = true;
        this.messenger = messenger;
    }

    @Override
    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);

        while (this.running) {
            System.out.print("> ");
            input = scanner.nextLine();
            this.messenger.forward(null, input);

            if (input != null) {
                this.peer.write(input);
            }
        }

    }


    public void begin() {
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void end() {
        this.running = false;
        this.thread.interrupt();
    }

}
