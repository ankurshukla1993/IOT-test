package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import java.util.concurrent.TimeUnit;

public final class ImmediateThinScheduler extends Scheduler {
    static final Disposable DISPOSED = Disposables.empty();
    public static final Scheduler INSTANCE = new ImmediateThinScheduler();
    static final Worker WORKER = new ImmediateThinWorker();

    static final class ImmediateThinWorker extends Worker {
        ImmediateThinWorker() {
        }

        public void dispose() {
        }

        public boolean isDisposed() {
            return false;
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run) {
            run.run();
            return ImmediateThinScheduler.DISPOSED;
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
            throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
        }

        @NonNull
        public Disposable schedulePeriodically(@NonNull Runnable run, long initialDelay, long period, TimeUnit unit) {
            throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
        }
    }

    static {
        DISPOSED.dispose();
    }

    private ImmediateThinScheduler() {
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run) {
        run.run();
        return DISPOSED;
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run, long delay, TimeUnit unit) {
        throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
    }

    @NonNull
    public Disposable schedulePeriodicallyDirect(@NonNull Runnable run, long initialDelay, long period, TimeUnit unit) {
        throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
    }

    @NonNull
    public Worker createWorker() {
        return WORKER;
    }
}
