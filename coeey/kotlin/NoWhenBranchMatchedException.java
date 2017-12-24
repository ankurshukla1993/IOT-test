package kotlin;

public class NoWhenBranchMatchedException extends RuntimeException {
    public NoWhenBranchMatchedException(String message) {
        super(message);
    }

    public NoWhenBranchMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoWhenBranchMatchedException(Throwable cause) {
        super(cause);
    }
}
