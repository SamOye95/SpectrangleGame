package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message {
    //***************************************************
    //---------------------ATTRIBUTES--------------------
    //***************************************************
    private String command;
    private List<String> args;
    private Integer statusCode;

    //***************************************************
    //---------------------CONSTRUCTORS------------------
    //***************************************************
    public Message(String msg) {
        this.args = new ArrayList<String>(Arrays.asList(msg.split(" ")));
        this.command = args.remove(0);

        try {
            this.statusCode = Integer.parseInt(command);
        } catch (NumberFormatException e) {
        }
    }

    //***************************************************
    //---------------------PUBLIC FUNCTIONS--------------
    //***************************************************
    public String getStringArgs() {
        return String.join(" ", this.args);
    }

    //***************************************************
    //---------------------GETTERS/SETTERS---------------
    //***************************************************
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}