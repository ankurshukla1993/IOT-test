package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class TestScheduler extends Scheduler {
    long counter;
    final Queue<TimedRunnable> queue = new PriorityBlockingQueue(11);
    volatile long time;

    final class TestWorker extends Worker {
        volatile boolean disposed;

        final class QueueRemove implements Runnable {
            final TimedRunnable timedAction;

            QueueRemove(TimedRunnable timedAction) {
                this.timedAction = timedAction;
            }

            public void run() {
                TestScheduler.this.queue.remove(this.timedAction);
            }
        }

        TestWorker() {
        }

        public void dispose() {
            this.disposed = true;
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run, long delayTime, @NonNull TimeUnit unit) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            long toNanos = TestScheduler.this.time + unit.toNanos(delayTime);
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedAction = new TimedRunnable(this, toNanos, run, j);
            TestScheduler.this.queue.add(timedAction);
            return Disposables.fromRunnable(new QueueRemove(timedAction));
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable run) {
            if (this.disposed) {
                return EmptyDisposable.INSTANCE;
            }
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.counter;
            testScheduler.counter = 1 + j;
            TimedRunnable timedAction = new TimedRunnable(this, 0, run, j);
            TestScheduler.this.queue.add(timedAction);
            return Disposables.fromRunnable(new QueueRemove(timedAction));
        }

        public long now(@NonNull TimeUnit unit) {
            return TestScheduler.this.now(unit);
        }
    }

    static final class TimedRunnable implements Comparable<TimedRunnable> {
        final long count;
        final Runnable run;
        final TestWorker scheduler;
        final long time;

        TimedRunnable(TestWorker scheduler, long time, Runnable run, long count) {
            this.time = time;
            this.run = run;
            this.scheduler = scheduler;
            this.count = count;
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", new Object[]{Long.valueOf(this.time), this.run.toString()});
        }

        public int compareTo(TimedRunnable o) {
            if (this.time == o.time) {
                return ObjectHelper.compare(this.count, o.count);
            }
            return ObjectHelper.compare(this.time, o.time);
        }
    }

    public long now(@NonNull TimeUnit unit) {
        return unit.convert(this.time, TimeUnit.NANOSECONDS);
    }

    public void advanceTimeBy(long delayTime, TimeUnit unit) {
        advanceTimeTo(this.time + unit.toNanos(delayTime), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long delayTime, TimeUnit unit) {
        triggerActions(unit.toNanos(delayTime));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long targetTimeInNanoseconds) {
        while (!this.queue.isEmpty()) {
            TimedRunnable current = (TimedRunnable) this.queue.peek();
            if (current.time > targetTimeInNanoseconds) {
                break;
            }
            this.time = current.time == 0 ? this.time : current.time;
            this.queue.remove();
            if (!current.scheduler.disposed) {
                current.run.run();
            }
        }
        this.time = targetTimeInNanoseconds;
    }

    @NonNull
    public Worker createWorker() {
        return new TestWorker();
    }
}
