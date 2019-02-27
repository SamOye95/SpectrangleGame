package exceptions;

public class NotEnoughPlayersException extends Exception {
    public NotEnoughPlayersException(String msg) {
        super(msg);
    }
}
