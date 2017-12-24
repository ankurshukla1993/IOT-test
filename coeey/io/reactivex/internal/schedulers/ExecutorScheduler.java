package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ExecutorScheduler extends Scheduler {
    static final Scheduler HELPER = Schedulers.single();
    @NonNull
    final Executor executor;

    final class DelayedDispose implements Runnable {
        private final DelayedRunnable dr;

        DelayedDispose(DelayedRunnable dr) {
            this.dr = dr;
        }

        public void run() {
            this.dr.direct.replace(ExecutorScheduler.this.scheduleDirect(this.dr));
        }
    }

    static final class DelayedRunnable extends AtomicReference<Runnable> implements Runnable, Disposable {
        private static final long serialVersionUID = -4101336210206799084L;
        final SequentialDisposable direct = new SequentialDisposable();
        final SequentialDisposable timed = new SequentialDisposable();

        DelayedRunnable(Runnable run) {
            super(run);
        }

        public void run() {
            Runnable r = (Runnable) get();
            if (r != null) {
                try {
                    r.run();
                } finally {
                    Object obj = null;
                    lazySet(null);
                    this.timed.lazySet(DisposableHelper.DISPOSED);
                    this.direct.lazySet(DisposableHelper.DISPOSED);
                }
            }
        }

        public boolean isDisposed() {
            return get() == null;
        }

        public void dispose() {
            if (getAndSet(null) != null) {
                this.timed.dispose();
                this.direct.dispose();
            }
        }
    }

    public static final class ExecutorWorker extends Worker implements Runnable {
        volatile boolean disposed;
        final Executor executor;
        final MpscLinkedQueue<Runnable> queue;
        final CompositeDisposable tasks = new CompositeDisposable();
        final AtomicInteger wip = new AtomicInteger();

        static final class BooleanRunnable extends AtomicBoolean implements Runnable, Disposable {
            private static final long serialVersionUID = -2421395018820541164L;
            final Runnable actual;

            BooleanRunnable(Runnable actual) {
                this.actual = actual;
            }

            public void run() {
                if (!get()) {
                    try {
                        this.actual.run();
                    } finally {
                        lazySet(true);
                    }
                }
            }

            public void dispose() {
                lazySet(true);
            }

            public boolean isDisposed() {
                return get();
            }
        }

        final class SequentialDispose implements Runnable {
            private final Runnable decoratedRun;
            private final SequentialDisposable mar;

            SequentialDispose(SequentialDisposable mar, Runnable decoratedRun) {
                this.mar = mar;
                this.decoratedRun = decoratedRun;
            }

            public void run() {
                this.mar.replace(ExecutorWorker.this.schedule(this.decoratedRun));
            }
        }

        public ExecutorWorker(Executor executor) {
            this.executor = executor;
            this.queue = new MpscLinkedQueue();
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            Disposable br = new BooleanRunnable(RxJavaPlugins.onSchedule(run));
            this.queue.offer(br);
            if (this.wip.getAndIncrement() != 0) {
                return br;
            }
            try {
                this.executor.execute(this);
                return br;
            } catch (RejectedExecutionException ex) {
                this.disposed = true;
                this.queue.clear();
                RxJavaPlugins.onError(ex);
                return EmptyDisposable.INSTANCE;
            }
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
            if (delay <= 0) {
                return schedule(run);
            }
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            SequentialDisposable first = new SequentialDisposable();
            Disposable mar = new SequentialDisposable(first);
            ScheduledRunnable sr = new ScheduledRunnable(new SequentialDispose(mar, RxJavaPlugins.onSchedule(run)), this.tasks);
            this.tasks.add(sr);
            if (this.executor instanceof ScheduledExecutorService) {
                try {
                    sr.setFuture(((ScheduledExecutorService) this.executor).schedule(sr, delay, unit));
                } catch (RejectedExecutionException ex) {
                    this.disposed = true;
                    RxJavaPlugins.onError(ex);
                    return EmptyDisposable.INSTANCE;
                }
            }
            sr.setFuture(new DisposeOnCancel(ExecutorScheduler.HELPER.scheduleDirect(sr, delay, unit)));
            first.replace(sr);
            return mar;
        }

        public void dispose() {
            if (!this.disposed) {
                this.disposed = true;
                this.tasks.dispose();
                if (this.wip.getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public void run() {
            int missed = 1;
            MpscLinkedQueue<Runnable> q = this.queue;
            while (!this.disposed) {
                while (true) {
                    Runnable run = (Runnable) q.poll();
                    if (run == null) {
                        break;
                    }
                    run.run();
                    if (this.disposed) {
                        q.clear();
                        return;
                    }
                }
                if (this.disposed) {
                    q.clear();
                    return;
                }
                missed = this.wip.addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
            q.clear();
        }
    }

    public ExecutorScheduler(@NonNull Executor executor) {
        this.executor = executor;
    }

    @NonNull
    public Worker createWorker() {
        return new ExecutorWorker(this.executor);
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run) {
        Runnable decoratedRun = RxJavaPlugins.onSchedule(run);
        try {
            if (this.executor instanceof ExecutorService) {
                ScheduledDirectTask task = new ScheduledDirectTask(decoratedRun);
                task.setFuture(((ExecutorService) this.executor).submit(task));
                return task;
            }
            BooleanRunnable br = new BooleanRunnable(decoratedRun);
            this.executor.execute(br);
            return br;
        } catch (RejectedExecutionException ex) {
            RxJavaPlugins.onError(ex);
            return EmptyDisposable.INSTANCE;
        }
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable run, long delay, TimeUnit unit) {
        Runnable decoratedRun = RxJavaPlugins.onSchedule(run);
        if (this.executor instanceof ScheduledExecutorService) {
            try {
                ScheduledDirectTask task = new ScheduledDirectTask(decoratedRun);
                task.setFuture(((ScheduledExecutorService) this.executor).schedule(task, delay, unit));
                return task;
            } catch (RejectedExecutionException ex) {
                RxJavaPlugins.onError(ex);
                return EmptyDisposable.INSTANCE;
            }
        }
        AtomicReference dr = new DelayedRunnable(decoratedRun);
        dr.timed.replace(HELPER.scheduleDirect(new DelayedDispose(dr), delay, unit));
        return dr;
    }

    @NonNull
    public Disposable schedulePeriodicallyDirect(@NonNull Runnable run, long initialDelay, long period, TimeUnit unit) {
        if (!(this.executor instanceof ScheduledExecutorService)) {
            return super.schedulePeriodicallyDirect(run, initialDelay, period, unit);
        }
        try {
            ScheduledDirectPeriodicTask task = new ScheduledDirectPeriodicTask(RxJavaPlugins.onSchedule(run));
            task.setFuture(((ScheduledExecutorService) this.executor).scheduleAtFixedRate(task, initialDelay, period, unit));
            return task;
        } catch (RejectedExecutionException ex) {
            RxJavaPlugins.onError(ex);
            return EmptyDisposable.INSTANCE;
        }
    }
}
