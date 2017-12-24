package org.joda.time.tz;

public class ZoneInfoLogger {
    static ThreadLocal<Boolean> cVerbose = new C25571();

    static class C25571 extends ThreadLocal<Boolean> {
        C25571() {
        }

        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    }

    public static boolean verbose() {
        return ((Boolean) cVerbose.get()).booleanValue();
    }

    public static void set(boolean z) {
        cVerbose.set(Boolean.valueOf(z));
    }
}
