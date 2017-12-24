package io.reactivex.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import java.util.concurrent.Callable;

public final class AndroidSchedulers {
    private static final Scheduler MAIN_THREAD = RxAndroidPlugins.initMainThreadScheduler(new C24141());

    static class C24141 implements Callable<Scheduler> {
        C24141() {
        }

        public Scheduler call() throws Exception {
            return MainHolder.DEFAULT;
        }
    }

    private static final class MainHolder {
        static final Scheduler DEFAULT = new HandlerScheduler(new Handler(Looper.getMainLooper()));

        private MainHolder() {
        }
    }

    public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    public static Scheduler from(Looper looper) {
        if (looper != null) {
            return new HandlerScheduler(new Handler(looper));
        }
        throw new NullPointerException("looper == null");
    }

    private AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }
}
