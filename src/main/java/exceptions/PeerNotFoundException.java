package exceptions;

public class PeerNotFoundException extends Exception {
    public PeerNotFoundException(String message) {
        super(message);
    }
}
