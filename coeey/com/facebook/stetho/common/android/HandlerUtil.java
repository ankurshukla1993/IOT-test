package com.facebook.stetho.common.android;

import android.os.Handler;
import android.os.Looper;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;

public final class HandlerUtil {

    private static abstract class WaitableRunnable<V> implements Runnable {
        private Exception mException;
        private boolean mIsDone;
        private V mValue;

        protected abstract V onRun();

        protected WaitableRunnable() {
        }

        public final void run() {
            try {
                this.mValue = onRun();
                this.mException = null;
                synchronized (this) {
                    this.mIsDone = true;
                    notifyAll();
                }
            } catch (Exception e) {
                this.mValue = null;
                this.mException = e;
                synchronized (this) {
                    this.mIsDone = true;
                    notifyAll();
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.mIsDone = true;
                    notifyAll();
                }
            }
        }

        public V invoke(Handler handler) {
            if (handler.post(this)) {
                join();
                if (this.mException == null) {
                    return this.mValue;
                }
                throw new RuntimeException(this.mException);
            }
            throw new RuntimeException("Handler.post() returned false");
        }

        private void join() {
            synchronized (this) {
                while (!this.mIsDone) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    private HandlerUtil() {
    }

    public static boolean checkThreadAccess(Handler handler) {
        return Looper.myLooper() == handler.getLooper();
    }

    public static void verifyThreadAccess(Handler handler) {
        Util.throwIfNot(checkThreadAccess(handler));
    }

    public static <V> V postAndWait(Handler handler, final UncheckedCallable<V> c) {
        if (!checkThreadAccess(handler)) {
            return new WaitableRunnable<V>() {
                protected V onRun() {
                    return c.call();
                }
            }.invoke(handler);
        }
        try {
            return c.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void postAndWait(Handler handler, final Runnable r) {
        if (checkThreadAccess(handler)) {
            try {
                r.run();
                return;
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        new WaitableRunnable<Void>() {
            protected Void onRun() {
                r.run();
                return null;
            }
        }.invoke(handler);
    }
}
