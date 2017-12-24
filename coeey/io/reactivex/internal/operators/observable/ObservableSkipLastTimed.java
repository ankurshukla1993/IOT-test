package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableSkipLastTimed<T> extends AbstractObservableWithUpstream<T, T> {
    final int bufferSize;
    final boolean delayError;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    static final class SkipLastTimedObserver<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -5677354903406201275L;
        final Observer<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        Disposable f2725s;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        SkipLastTimedObserver(Observer<? super T> actual, long time, TimeUnit unit, Scheduler scheduler, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.queue = new SpscLinkedArrayQueue(bufferSize);
            this.delayError = delayError;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2725s, s)) {
                this.f2725s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.queue.offer(Long.valueOf(this.scheduler.now(this.unit)), t);
            drain();
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2725s.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                Observer<? super T> a = this.actual;
                SpscLinkedArrayQueue<Object> q = this.queue;
                boolean delayError = this.delayError;
                TimeUnit unit = this.unit;
                Scheduler scheduler = this.scheduler;
                long time = this.time;
                while (!this.cancelled) {
                    boolean d = this.done;
                    Long ts = (Long) q.peek();
                    boolean empty = ts == null;
                    long now = scheduler.now(unit);
                    if (!empty && ts.longValue() > now - time) {
                        empty = true;
                    }
                    if (d) {
                        Throwable e;
                        if (!delayError) {
                            e = this.error;
                            if (e != null) {
                                this.queue.clear();
                                a.onError(e);
                                return;
                            } else if (empty) {
                                a.onComplete();
                                return;
                            }
                        } else if (empty) {
                            e = this.error;
                            if (e != null) {
                                a.onError(e);
                                return;
                            } else {
                                a.onComplete();
                                return;
                            }
                        }
                    }
                    if (empty) {
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        q.poll();
                        a.onNext(q.poll());
                    }
                }
                this.queue.clear();
            }
        }
    }

    public ObservableSkipLastTimed(ObservableSource<T> source, long time, TimeUnit unit, Scheduler scheduler, int bufferSize, boolean delayError) {
        super(source);
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new SkipLastTimedObserver(t, this.time, this.unit, this.scheduler, this.bufferSize, this.delayError));
    }
}
