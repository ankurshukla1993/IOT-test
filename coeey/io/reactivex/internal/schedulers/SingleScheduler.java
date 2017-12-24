package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleScheduler extends Scheduler {
    private static final String KEY_SINGLE_PRIORITY = "rx2.single-priority";
    static final ScheduledExecutorService SHUTDOWN = Executors.newScheduledThreadPool(0);
    static final RxThreadFactory SINGLE_THREAD_FACTORY = new RxThreadFactory(THREAD_NAME_PREFIX, Math.max(1, Math.min(10, Integer.getInteger(KEY_SINGLE_PRIORITY, 5).intValue())), true);
    private static final String THREAD_NAME_PREFIX = "RxSingleScheduler";
    final AtomicReference<ScheduledExecutorService> executor;
    final ThreadFactory threadFactory;

    static final class ScheduledWorker extends Worker {
        volatile boolean disposed;
        final ScheduledExecutorService executor;
        final CompositeDisposable tasks = new CompositeDisposable();

        ScheduledWorker(ScheduledExecutorService executor) {
            this.executor = executor;
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            Future<?> f;
            Disposable sr = new ScheduledRunnable(RxJavaPlugins.onSchedule(run), this.tasks);
            this.tasks.add(sr);
            if (delay <= 0) {
                try {
                    f = this.executor.submit(sr);
                } catch (RejectedExecutionException ex) {
                    dispose();
                    RxJavaPlugins.onError(ex);
                    return EmptyDisposable.INSTANCE;
                }
            }
            f = this.executor.schedule(sr, delay, unit);
            sr.setFuture(f);
            return sr;
        }

        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.tasks.dispose();
            }
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }

    static {
        SHUTDOWN.shutdown();
    }

    public SingleScheduler() {
        this(SINGLE_THREAD_FACTORY);
    }

    public SingleScheduler(ThreadFactory threadFactory) {
        this.executor = new AtomicReference();
        this.threadFactory = threadFactory;
        this.executor.lazySet(createExecutor(threadFactory));
    }

    static ScheduledExecutorService createExecutor(ThreadFactory threadFactory) {
        return SchedulerPoolFactory.create(threadFactory);
    }

    public void start() {
        ScheduledExecutorService next = null;
        ScheduledExecutorService current;
        do {
            current = (ScheduledExecutorService) this.executor.get();
            if (current != SHUTDOWN) {
                if (next != null) {
                    next.shutdown();
                    return;
                }
                return;
            } else if (next == null) {
                next = createExecutor(this.threadFactory);
            }
        } while (!this.executor.compareAndSet(current, next));
    }

    public void shutdown() {
        if (((ScheduledExecutorService) this.executor.get()) != SHUTDOWN) {
            ScheduledExecutorService current = (ScheduledExecutorService) this.executor.getAndSet(SHUTDOWN);
            if (current != SHUTDOWN) {
                current.shutdownNow();
            }
        }
    }

    @NonNull
    public Worker createWorker() {
        return new ScheduledWorker((ScheduledExecutorService) this.executor.get());
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run, long delay, TimeUnit unit) {
        Future<?> f;
        ScheduledDirectTask task = new ScheduledDirectTask(RxJavaPlugins.onSchedule(run));
        if (delay <= 0) {
            try {
                f = ((ScheduledExecutorService) this.executor.get()).submit(task);
            } catch (RejectedExecutionException ex) {
                RxJavaPlugins.onError(ex);
                return EmptyDisposable.INSTANCE;
            }
        }
        f = ((ScheduledExecutorService) this.executor.get()).schedule(task, delay, unit);
        task.setFuture(f);
        return task;
    }

    @NonNull
    public Disposable schedulePeriodicallyDirect(@NonNull Runnable run, long initialDelay, long period, TimeUnit unit) {
        Runnable decoratedRun = RxJavaPlugins.onSchedule(run);
        if (period <= 0) {
            Future<?> f;
            ScheduledExecutorService exec = (ScheduledExecutorService) this.executor.get();
            InstantPeriodicTask periodicWrapper = new InstantPeriodicTask(decoratedRun, exec);
            if (initialDelay <= 0) {
                try {
                    f = exec.submit(periodicWrapper);
                } catch (RejectedExecutionException ex) {
                    RxJavaPlugins.onError(ex);
                    return EmptyDisposable.INSTANCE;
                }
            }
            f = exec.schedule(periodicWrapper, initialDelay, unit);
            periodicWrapper.setFirst(f);
            return periodicWrapper;
        }
        ScheduledDirectPeriodicTask task = new ScheduledDirectPeriodicTask(decoratedRun);
        try {
            task.setFuture(((ScheduledExecutorService) this.executor.get()).scheduleAtFixedRate(task, initialDelay, period, unit));
            return task;
        } catch (RejectedExecutionException ex2) {
            RxJavaPlugins.onError(ex2);
            return EmptyDisposable.INSTANCE;
        }
    }
}
