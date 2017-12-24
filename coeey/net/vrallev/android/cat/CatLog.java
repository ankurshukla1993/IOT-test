package net.vrallev.android.cat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.vrallev.android.cat.print.AndroidLog;
import net.vrallev.android.cat.print.CatPrinter;

public abstract class CatLog {
    private final List<CatPrinter> mPrinters = new ArrayList();

    protected abstract String getTag();

    protected CatLog() {
        this.mPrinters.add(new AndroidLog());
    }

    public synchronized void addPrinter(CatPrinter printer) {
        this.mPrinters.add(printer);
    }

    protected synchronized List<? extends CatPrinter> getPrinters() {
        return this.mPrinters;
    }

    public void mo6990d(String message) {
        println(3, message, null);
    }

    public void mo6991d(String message, Object... args) {
        println(3, format(message, args), null);
    }

    public void mo6992d(Throwable t, String message, Object... args) {
        println(3, format(message, args), t);
    }

    public void mo6998i(String message) {
        println(4, message, null);
    }

    public void mo6999i(String message, Object... args) {
        println(4, format(message, args), null);
    }

    public void mo7000i(Throwable t, String message, Object... args) {
        println(4, format(message, args), t);
    }

    public void mo7004w(String message) {
        println(5, message, null);
    }

    public void mo7005w(String message, Object... args) {
        println(5, format(message, args), null);
    }

    public void mo7007w(Throwable t, String message, Object... args) {
        println(5, format(message, args), t);
    }

    public void mo7006w(Throwable t) {
        if (t == null) {
            t = new Exception("null exception logged");
        }
        println(5, t.getMessage(), t);
    }

    public void mo6995e(Throwable t) {
        if (t == null) {
            t = new Exception("null exception logged");
        }
        println(6, t.getMessage(), t);
    }

    public void mo6993e(String message) {
        println(6, message, null);
    }

    public void mo6994e(String message, Object... args) {
        println(6, format(message, args), null);
    }

    public void mo6996e(Throwable t, String message, Object... args) {
        println(6, format(message, args), t);
    }

    public void mo7001v(String message) {
        println(2, message, null);
    }

    public void mo7002v(String message, Object... args) {
        println(2, format(message, args), null);
    }

    public void mo7003v(Throwable t, String message, Object... args) {
        println(2, format(message, args), t);
    }

    protected void println(int priority, String message, Throwable t) {
        if (TextUtils.isEmpty(message)) {
            message = "empty message";
        }
        CatGlobal.print(priority, getTag(), message, t, getPrinters());
    }

    private static String format(String message, Object[] args) {
        if (message == null) {
            return "null";
        }
        return String.format(Locale.US, message, args);
    }
}
