package controller;

public abstract class SpectrangleController {

    //***************************************************
    //---------------------ATTRIBUTES--------------------
    //***************************************************
    private Database database;

    //***************************************************
    //---------------------CONSTRUCTORS------------------
    //***************************************************
    public Controller(Database database) {
        this.database = database;
    }

    //***************************************************
    //---------------------ABSTRACT METHODS--------------
    //***************************************************
    public abstract void forward(Peer peer, Message msg);

    //***************************************************
    //---------------------PUBLIC METHODS----------------
    //***************************************************
    public boolean hasMethod(String method) {
        for (Method m : this.getClass().getMethods()) {
            if (m.getName().equals(method)) {
                return true;
            }
        }
        return false;
    }

    //***************************************************
    //---------------------PUBLIC METHODS----------------
    //***************************************************
    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
