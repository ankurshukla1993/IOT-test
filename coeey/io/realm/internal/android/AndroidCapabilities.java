package io.realm.internal.android;

import android.os.Looper;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import humanize.util.Constants;
import io.realm.internal.Capabilities;
import javax.annotation.Nullable;

public class AndroidCapabilities implements Capabilities {
    @SuppressFBWarnings({"MS_SHOULD_BE_FINAL"})
    public static boolean EMULATE_MAIN_THREAD = false;
    private final boolean isIntentServiceThread = isIntentServiceThread();
    private final Looper looper = Looper.myLooper();

    public boolean canDeliverNotification() {
        return hasLooper() && !this.isIntentServiceThread;
    }

    public void checkCanDeliverNotification(@Nullable String exceptionMessage) {
        if (!hasLooper()) {
            throw new IllegalStateException(exceptionMessage == null ? "" : exceptionMessage + Constants.SPACE + "Realm cannot be automatically updated on a thread without a looper.");
        } else if (this.isIntentServiceThread) {
            throw new IllegalStateException(exceptionMessage == null ? "" : exceptionMessage + Constants.SPACE + "Realm cannot be automatically updated on an IntentService thread.");
        }
    }

    public boolean isMainThread() {
        return this.looper != null && (EMULATE_MAIN_THREAD || this.looper == Looper.getMainLooper());
    }

    private boolean hasLooper() {
        return this.looper != null;
    }

    private static boolean isIntentServiceThread() {
        String threadName = Thread.currentThread().getName();
        return threadName != null && threadName.startsWith("IntentService[");
    }
}
