package android.arch.persistence.room;

public class EmptyResultSetException extends RuntimeException {
    public EmptyResultSetException(String message) {
        super(message);
    }
}
