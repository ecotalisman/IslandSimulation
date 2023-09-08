package app.island.exceptions;

public class IslandException extends RuntimeException {
    public IslandException(String message, Throwable cause) {
        super(message, cause);
    }
}
