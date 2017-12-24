package com.facebook.stetho.common;

public final class StringUtil {
    private StringUtil() {
    }

    public static String removePrefix(String string, String prefix, String previousAttempt) {
        return string != previousAttempt ? previousAttempt : removePrefix(string, prefix);
    }

    public static String removePrefix(String string, String prefix) {
        if (string.startsWith(prefix)) {
            return string.substring(prefix.length());
        }
        return string;
    }

    public static String removeAll(String string, char target) {
        int length = string.length();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c != target) {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
