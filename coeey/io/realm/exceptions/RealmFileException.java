package io.realm.exceptions;

import io.realm.internal.Keep;
import java.util.Locale;

@Keep
public class RealmFileException extends RuntimeException {
    private final Kind kind;

    @Keep
    public enum Kind {
        ACCESS_ERROR,
        BAD_HISTORY,
        PERMISSION_DENIED,
        EXISTS,
        NOT_FOUND,
        INCOMPATIBLE_LOCK_FILE,
        FORMAT_UPGRADE_REQUIRED;

        static Kind getKind(byte value) {
            switch (value) {
                case (byte) 0:
                    return ACCESS_ERROR;
                case (byte) 1:
                    return BAD_HISTORY;
                case (byte) 2:
                    return PERMISSION_DENIED;
                case (byte) 3:
                    return EXISTS;
                case (byte) 4:
                    return NOT_FOUND;
                case (byte) 5:
                    return INCOMPATIBLE_LOCK_FILE;
                case (byte) 6:
                    return FORMAT_UPGRADE_REQUIRED;
                default:
                    throw new RuntimeException("Unknown value for RealmFileException kind.");
            }
        }
    }

    public RealmFileException(byte value, String message) {
        super(message);
        this.kind = Kind.getKind(value);
    }

    public RealmFileException(Kind kind, String message) {
        super(message);
        this.kind = kind;
    }

    public RealmFileException(Kind kind, Throwable cause) {
        super(cause);
        this.kind = kind;
    }

    public RealmFileException(Kind kind, String message, Throwable cause) {
        super(message, cause);
        this.kind = kind;
    }

    public Kind getKind() {
        return this.kind;
    }

    public String toString() {
        return String.format(Locale.US, "%s Kind: %s.", new Object[]{super.toString(), this.kind});
    }
}
