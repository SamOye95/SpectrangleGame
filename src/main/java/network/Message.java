package network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message {

    private String command;
    private List<String> args;
    private Integer errorCode;


    public Message(String msg) {
        this.args = new ArrayList<>(Arrays.asList(msg.split(" ")));
        this.command = args.remove(0);

        try {
            this.errorCode = Integer.parseInt(command);
        } catch (NumberFormatException e) {
        }
    }


    public String getStringArgs() {
        return String.join(" ", this.args);
    }

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

    public Integer getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


}