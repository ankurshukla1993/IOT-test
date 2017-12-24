package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.util.ObservableQueueDrain;
import io.reactivex.internal.util.QueueDrainHelper;

public abstract class QueueDrainObserver<T, U, V> extends QueueDrainSubscriberPad2 implements Observer<T>, ObservableQueueDrain<U, V> {
    protected final Observer<? super V> actual;
    protected volatile boolean cancelled;
    protected volatile boolean done;
    protected Throwable error;
    protected final SimplePlainQueue<U> queue;

    public QueueDrainObserver(Observer<? super V> actual, SimplePlainQueue<U> queue) {
        this.actual = actual;
        this.queue = queue;
    }

    public final boolean cancelled() {
        return this.cancelled;
    }

    public final boolean done() {
        return this.done;
    }

    public final boolean enter() {
        return this.wip.getAndIncrement() == 0;
    }

    public final boolean fastEnter() {
        return this.wip.get() == 0 && this.wip.compareAndSet(0, 1);
    }

    protected final void fastPathEmit(U value, boolean delayError, Disposable dispose) {
        Observer<? super V> s = this.actual;
        SimplePlainQueue<U> q = this.queue;
        if (this.wip.get() == 0 && this.wip.compareAndSet(0, 1)) {
            accept(s, value);
            if (leave(-1) == 0) {
                return;
            }
        }
        q.offer(value);
        if (!enter()) {
            return;
        }
        QueueDrainHelper.drainLoop(q, s, delayError, dispose, this);
    }

    protected final void fastPathOrderedEmit(U value, boolean delayError, Disposable disposable) {
        Observer<? super V> s = this.actual;
        SimplePlainQueue<U> q = this.queue;
        if (this.wip.get() != 0 || !this.wip.compareAndSet(0, 1)) {
            q.offer(value);
            if (!enter()) {
                return;
            }
        } else if (q.isEmpty()) {
            accept(s, value);
            if (leave(-1) == 0) {
                return;
            }
        } else {
            q.offer(value);
        }
        QueueDrainHelper.drainLoop(q, s, delayError, disposable, this);
    }

    public final Throwable error() {
        return this.error;
    }

    public final int leave(int m) {
        return this.wip.addAndGet(m);
    }

    public void accept(Observer<? super V> observer, U u) {
    }
}
