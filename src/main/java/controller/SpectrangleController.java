package controller;

import network.Database;
import network.Message;
import network.Peer;

import java.lang.reflect.Method;


public abstract class SpectrangleController {


    private Database database;

    public SpectrangleController(Database database) {
        this.database = database;
    }


    public abstract void forward(Peer peer, Message msg);


    public boolean hasMethod(String method) {
        for (Method m : this.getClass().getMethods()) {
            if (m.getName().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
