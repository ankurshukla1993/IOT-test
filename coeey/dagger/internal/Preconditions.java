package dagger.internal;

public final class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object errorMessageArg) {
        if (reference != null) {
            return reference;
        }
        if (!errorMessageTemplate.contains("%s")) {
            throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
        } else if (errorMessageTemplate.indexOf("%s") != errorMessageTemplate.lastIndexOf("%s")) {
            throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
        } else {
            throw new NullPointerException(errorMessageTemplate.replaceFirst("%s", String.valueOf(errorMessageArg)));
        }
    }

    private Preconditions() {
    }
}
