package ua.tunepoint.web.exception;

public class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }

    public ServerException() {
    }
}
