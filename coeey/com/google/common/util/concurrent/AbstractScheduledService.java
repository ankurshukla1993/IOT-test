package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service.Listener;
import com.google.common.util.concurrent.Service.State;
import humanize.util.Constants;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

@Beta
public abstract class AbstractScheduledService implements Service {
    private static final Logger logger = Logger.getLogger(AbstractScheduledService.class.getName());
    private final AbstractService delegate = new C18331();

    class C18331 extends AbstractService {
        private volatile ScheduledExecutorService executorService;
        private final ReentrantLock lock = new ReentrantLock();
        private volatile Future<?> runningTask;
        private final Runnable task = new C18291();

        class C18291 implements Runnable {
            C18291() {
            }

            public void run() {
                C18331.this.lock.lock();
                try {
                    AbstractScheduledService.this.runOneIteration();
                    C18331.this.lock.unlock();
                } catch (Throwable th) {
                    C18331.this.lock.unlock();
                }
            }
        }

        class C18302 implements Supplier<String> {
            C18302() {
            }

            public String get() {
                String valueOf = String.valueOf(String.valueOf(AbstractScheduledService.this.serviceName()));
                String valueOf2 = String.valueOf(String.valueOf(C18331.this.state()));
                return new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(Constants.SPACE).append(valueOf2).toString();
            }
        }

        class C18313 implements Runnable {
            C18313() {
            }

            public void run() {
                C18331.this.lock.lock();
                try {
                    AbstractScheduledService.this.startUp();
                    C18331.this.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, C18331.this.executorService, C18331.this.task);
                    C18331.this.notifyStarted();
                    C18331.this.lock.unlock();
                } catch (Throwable th) {
                    C18331.this.lock.unlock();
                }
            }
        }

        class C18324 implements Runnable {
            C18324() {
            }

            public void run() {
                try {
                    C18331.this.lock.lock();
                    if (C18331.this.state() != State.STOPPING) {
                        C18331.this.lock.unlock();
                        return;
                    }
                    AbstractScheduledService.this.shutDown();
                    C18331.this.lock.unlock();
                    C18331.this.notifyStopped();
                } catch (Throwable t) {
                    C18331.this.notifyFailed(t);
                    RuntimeException propagate = Throwables.propagate(t);
                }
            }
        }

        C18331() {
        }

        protected final void doStart() {
            this.executorService = MoreExecutors.renamingDecorator(AbstractScheduledService.this.executor(), new C18302());
            this.executorService.execute(new C18313());
        }

        protected final void doStop() {
            this.runningTask.cancel(false);
            this.executorService.execute(new C18324());
        }
    }

    class C18342 implements ThreadFactory {
        C18342() {
        }

        public Thread newThread(Runnable runnable) {
            return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
        }
    }

    public static abstract class Scheduler {
        abstract Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable);

        public static Scheduler newFixedDelaySchedule(long initialDelay, long delay, TimeUnit unit) {
            final long j = initialDelay;
            final long j2 = delay;
            final TimeUnit timeUnit = unit;
            return new Scheduler() {
                public Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return executor.scheduleWithFixedDelay(task, j, j2, timeUnit);
                }
            };
        }

        public static Scheduler newFixedRateSchedule(long initialDelay, long period, TimeUnit unit) {
            final long j = initialDelay;
            final long j2 = period;
            final TimeUnit timeUnit = unit;
            return new Scheduler() {
                public Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return executor.scheduleAtFixedRate(task, j, j2, timeUnit);
                }
            };
        }

        private Scheduler() {
        }
    }

    @Beta
    public static abstract class CustomScheduler extends Scheduler {

        private class ReschedulableCallable extends ForwardingFuture<Void> implements Callable<Void> {
            @GuardedBy("lock")
            private Future<Void> currentFuture;
            private final ScheduledExecutorService executor;
            private final ReentrantLock lock = new ReentrantLock();
            private final AbstractService service;
            private final Runnable wrappedRunnable;

            ReschedulableCallable(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
                this.wrappedRunnable = runnable;
                this.executor = executor;
                this.service = service;
            }

            public Void call() throws Exception {
                this.wrappedRunnable.run();
                reschedule();
                return null;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void reschedule() {
                /*
                r6 = this;
                r2 = r6.lock;
                r2.lock();
                r2 = r6.currentFuture;	 Catch:{ Throwable -> 0x002d }
                if (r2 == 0) goto L_0x0011;
            L_0x0009:
                r2 = r6.currentFuture;	 Catch:{ Throwable -> 0x002d }
                r2 = r2.isCancelled();	 Catch:{ Throwable -> 0x002d }
                if (r2 != 0) goto L_0x0027;
            L_0x0011:
                r2 = com.google.common.util.concurrent.AbstractScheduledService.CustomScheduler.this;	 Catch:{ Throwable -> 0x002d }
                r1 = r2.getNextSchedule();	 Catch:{ Throwable -> 0x002d }
                r2 = r6.executor;	 Catch:{ Throwable -> 0x002d }
                r4 = r1.delay;	 Catch:{ Throwable -> 0x002d }
                r3 = r1.unit;	 Catch:{ Throwable -> 0x002d }
                r2 = r2.schedule(r6, r4, r3);	 Catch:{ Throwable -> 0x002d }
                r6.currentFuture = r2;	 Catch:{ Throwable -> 0x002d }
            L_0x0027:
                r2 = r6.lock;
                r2.unlock();
            L_0x002c:
                return;
            L_0x002d:
                r0 = move-exception;
                r2 = r6.service;	 Catch:{ all -> 0x0039 }
                r2.notifyFailed(r0);	 Catch:{ all -> 0x0039 }
                r2 = r6.lock;
                r2.unlock();
                goto L_0x002c;
            L_0x0039:
                r2 = move-exception;
                r3 = r6.lock;
                r3.unlock();
                throw r2;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractScheduledService.CustomScheduler.ReschedulableCallable.reschedule():void");
            }

            public boolean cancel(boolean mayInterruptIfRunning) {
                this.lock.lock();
                try {
                    boolean cancel = this.currentFuture.cancel(mayInterruptIfRunning);
                    return cancel;
                } finally {
                    this.lock.unlock();
                }
            }

            protected Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel is supported by this future");
            }
        }

        @Beta
        protected static final class Schedule {
            private final long delay;
            private final TimeUnit unit;

            public Schedule(long delay, TimeUnit unit) {
                this.delay = delay;
                this.unit = (TimeUnit) Preconditions.checkNotNull(unit);
            }
        }

        protected abstract Schedule getNextSchedule() throws Exception;

        public CustomScheduler() {
            super();
        }

        final Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
            ReschedulableCallable task = new ReschedulableCallable(service, executor, runnable);
            task.reschedule();
            return task;
        }
    }

    protected abstract void runOneIteration() throws Exception;

    protected abstract Scheduler scheduler();

    protected AbstractScheduledService() {
    }

    protected void startUp() throws Exception {
    }

    protected void shutDown() throws Exception {
    }

    protected ScheduledExecutorService executor() {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new C18342());
        addListener(new Listener() {
            public void terminated(State from) {
                executor.shutdown();
            }

            public void failed(State from, Throwable failure) {
                executor.shutdown();
            }
        }, MoreExecutors.directExecutor());
        return executor;
    }

    protected String serviceName() {
        return getClass().getSimpleName();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(serviceName()));
        String valueOf2 = String.valueOf(String.valueOf(state()));
        return new StringBuilder((valueOf.length() + 3) + valueOf2.length()).append(valueOf).append(" [").append(valueOf2).append("]").toString();
    }

    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    public final State state() {
        return this.delegate.state();
    }

    public final void addListener(Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }
}
