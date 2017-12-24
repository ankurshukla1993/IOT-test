package com.evernote.android.job.util;

import android.support.annotation.NonNull;
import java.util.Arrays;
import net.vrallev.android.cat.instance.CatLazy;
import net.vrallev.android.cat.print.CatPrinter;

public class JobCat extends CatLazy {
    private static volatile boolean logcatEnabled = true;
    private static volatile CatPrinter[] printers = new CatPrinter[0];
    private final String mTag;

    public static synchronized boolean addLogPrinter(@NonNull CatPrinter printer) {
        boolean z = false;
        synchronized (JobCat.class) {
            for (CatPrinter printer1 : printers) {
                if (printer.equals(printer1)) {
                    break;
                }
            }
            for (int i = 0; i < printers.length; i++) {
                if (printers[i] == null) {
                    printers[i] = printer;
                    z = true;
                    break;
                }
            }
            int index = printers.length;
            printers = (CatPrinter[]) Arrays.copyOf(printers, printers.length + 2);
            printers[index] = printer;
            z = true;
        }
        return z;
    }

    public static synchronized void removeLogPrinter(@NonNull CatPrinter printer) {
        synchronized (JobCat.class) {
            for (int i = 0; i < printers.length; i++) {
                if (printer.equals(printers[i])) {
                    printers[i] = null;
                }
            }
        }
    }

    public static void setLogcatEnabled(boolean enabled) {
        logcatEnabled = enabled;
    }

    public static boolean isLogcatEnabled() {
        return logcatEnabled;
    }

    public JobCat() {
        this((String) null);
    }

    public JobCat(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    public JobCat(String tag) {
        this.mTag = tag;
    }

    public String getTag() {
        return this.mTag == null ? super.getTag() : this.mTag;
    }

    protected void println(int priority, String message, Throwable t) {
        if (logcatEnabled) {
            super.println(priority, message, t);
        }
        CatPrinter[] printers = printers;
        if (printers.length > 0) {
            String tag = getTag();
            for (CatPrinter printer : printers) {
                if (printer != null) {
                    printer.println(priority, tag, message, t);
                }
            }
        }
    }
}
