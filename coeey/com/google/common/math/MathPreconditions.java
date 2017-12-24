package com.google.common.math;

import com.facebook.react.uimanager.ViewProps;
import com.google.common.annotations.GwtCompatible;
import java.math.BigInteger;
import javax.annotation.Nullable;

@GwtCompatible
final class MathPreconditions {
    static int checkPositive(@Nullable String role, int x) {
        if (x > 0) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 26).append(valueOf).append(" (").append(x).append(") must be > 0").toString());
    }

    static long checkPositive(@Nullable String role, long x) {
        if (x > 0) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 35).append(valueOf).append(" (").append(x).append(") must be > 0").toString());
    }

    static BigInteger checkPositive(@Nullable String role, BigInteger x) {
        if (x.signum() > 0) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        String valueOf2 = String.valueOf(String.valueOf(x));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 15) + valueOf2.length()).append(valueOf).append(" (").append(valueOf2).append(") must be > 0").toString());
    }

    static int checkNonNegative(@Nullable String role, int x) {
        if (x >= 0) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 27).append(valueOf).append(" (").append(x).append(") must be >= 0").toString());
    }

    static long checkNonNegative(@Nullable String role, long x) {
        if (x >= 0) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 36).append(valueOf).append(" (").append(x).append(") must be >= 0").toString());
    }

    static BigInteger checkNonNegative(@Nullable String role, BigInteger x) {
        if (x.signum() >= 0) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        String valueOf2 = String.valueOf(String.valueOf(x));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 16) + valueOf2.length()).append(valueOf).append(" (").append(valueOf2).append(") must be >= 0").toString());
    }

    static double checkNonNegative(@Nullable String role, double x) {
        if (x >= 0.0d) {
            return x;
        }
        String valueOf = String.valueOf(String.valueOf(role));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 40).append(valueOf).append(" (").append(x).append(") must be >= 0").toString());
    }

    static void checkRoundingUnnecessary(boolean condition) {
        if (!condition) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    static void checkInRange(boolean condition) {
        if (!condition) {
            throw new ArithmeticException("not in range");
        }
    }

    static void checkNoOverflow(boolean condition) {
        if (!condition) {
            throw new ArithmeticException(ViewProps.OVERFLOW);
        }
    }

    private MathPreconditions() {
    }
}
