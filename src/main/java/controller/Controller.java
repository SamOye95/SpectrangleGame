package controller;

import network.Database;
import network.Message;
import network.Peer;

import java.lang.reflect.Method;


public abstract class Controller {


    private Database database;

    public Controller(Database database) {
        this.database = database;
    }


    public abstract void forward(Peer peer, Message msg);



    public boolean hasMethod(String method) {
        for (Method m : this.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }

    public void writeErrorMessage(Peer peer, int status, String msg) {
        peer.write(status + " " + msg);
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
