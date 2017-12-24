package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.subjects.UnicastSubject;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableWindow<T> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int capacityHint;
    final long count;
    final long skip;

    static final class WindowExactObserver<T> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = -7481782523886138128L;
        final Observer<? super Observable<T>> actual;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        Disposable f2747s;
        long size;
        UnicastSubject<T> window;

        WindowExactObserver(Observer<? super Observable<T>> actual, long count, int capacityHint) {
            this.actual = actual;
            this.count = count;
            this.capacityHint = capacityHint;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2747s, s)) {
                this.f2747s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            UnicastSubject<T> w = this.window;
            if (w == null && !this.cancelled) {
                w = UnicastSubject.create(this.capacityHint, this);
                this.window = w;
                this.actual.onNext(w);
            }
            if (w != null) {
                w.onNext(t);
                long j = this.size + 1;
                this.size = j;
                if (j >= this.count) {
                    this.size = 0;
                    this.window = null;
                    w.onComplete();
                    if (this.cancelled) {
                        this.f2747s.dispose();
                    }
                }
            }
        }

        public void onError(Throwable t) {
            UnicastSubject<T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onError(t);
            }
            this.actual.onError(t);
        }

        public void onComplete() {
            UnicastSubject<T> w = this.window;
            if (w != null) {
                this.window = null;
                w.onComplete();
            }
            this.actual.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            if (this.cancelled) {
                this.f2747s.dispose();
            }
        }
    }

    static final class WindowSkipObserver<T> extends AtomicBoolean implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = 3366976432059579510L;
        final Observer<? super Observable<T>> actual;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        long firstEmission;
        long index;
        Disposable f2748s;
        final long skip;
        final ArrayDeque<UnicastSubject<T>> windows;
        final AtomicInteger wip = new AtomicInteger();

        WindowSkipObserver(Observer<? super Observable<T>> actual, long count, long skip, int capacityHint) {
            this.actual = actual;
            this.count = count;
            this.skip = skip;
            this.capacityHint = capacityHint;
            this.windows = new ArrayDeque();
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2748s, s)) {
                this.f2748s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            ArrayDeque<UnicastSubject<T>> ws = this.windows;
            long i = this.index;
            long s = this.skip;
            if (i % s == 0 && !this.cancelled) {
                this.wip.getAndIncrement();
                UnicastSubject<T> w = UnicastSubject.create(this.capacityHint, this);
                ws.offer(w);
                this.actual.onNext(w);
            }
            long c = this.firstEmission + 1;
            Iterator it = ws.iterator();
            while (it.hasNext()) {
                ((UnicastSubject) it.next()).onNext(t);
            }
            if (c >= this.count) {
                ((UnicastSubject) ws.poll()).onComplete();
                if (ws.isEmpty() && this.cancelled) {
                    this.f2748s.dispose();
                    return;
                }
                this.firstEmission = c - s;
            } else {
                this.firstEmission = c;
            }
            this.index = i + 1;
        }

        public void onError(Throwable t) {
            ArrayDeque<UnicastSubject<T>> ws = this.windows;
            while (!ws.isEmpty()) {
                ((UnicastSubject) ws.poll()).onError(t);
            }
            this.actual.onError(t);
        }

        public void onComplete() {
            ArrayDeque<UnicastSubject<T>> ws = this.windows;
            while (!ws.isEmpty()) {
                ((UnicastSubject) ws.poll()).onComplete();
            }
            this.actual.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void run() {
            if (this.wip.decrementAndGet() == 0 && this.cancelled) {
                this.f2748s.dispose();
            }
        }
    }

    public ObservableWindow(ObservableSource<T> source, long count, long skip, int capacityHint) {
        super(source);
        this.count = count;
        this.skip = skip;
        this.capacityHint = capacityHint;
    }

    public void subscribeActual(Observer<? super Observable<T>> t) {
        if (this.count == this.skip) {
            this.source.subscribe(new WindowExactObserver(t, this.count, this.capacityHint));
            return;
        }
        this.source.subscribe(new WindowSkipObserver(t, this.count, this.skip, this.capacityHint));
    }
}
